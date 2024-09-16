package com.larkes.firebasecryptoportfoliosample.features.portfolio.data

import com.larkes.firebasecryptoportfoliosample.features.portfolio.data.models.PortfolioCoinEntity
import com.larkes.firebasecryptoportfoliosample.features.portfolio.data.source.PortfolioFirebaseDataSource
import com.larkes.firebasecryptoportfoliosample.features.portfolio.data.source.PortfolioServerDataSource
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.PortfolioRepository
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.mapper.toPortfolioCoin
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models.Coin
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models.PortfolioCoin
import com.larkes.firebasecryptoportfoliosample.utils.Resource
import com.larkes.firebasecryptoportfoliosample.utils.SharedService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PortfolioRepositoryImpl(
    private val portfolioFirebaseDataSource: PortfolioFirebaseDataSource,
    private val portfolioServerDataSource: PortfolioServerDataSource
):PortfolioRepository {
    override suspend fun addCoin(coin: Coin, amount:Float) {
        portfolioFirebaseDataSource.sendPortfolioCoin(
            PortfolioCoinEntity(
                id = SharedService.generateUniqId(),
                amount = amount,
                imageSrc = coin.imageSrc,
                shortName = coin.shortName,
                title = coin.title,
                savedPrice = coin.price
            )
        )
    }

    override fun portfolioCoinsFlow(): Flow<Resource<PortfolioCoin>> = channelFlow {

        val scope = CoroutineScope(Dispatchers.IO)

        fun performCancellationTask(){
            scope.launch {
                delay(2000)
                trySend(Resource.LoadingEnd())
                channel.close()
            }
        }

        performCancellationTask()
        portfolioFirebaseDataSource.portfolioCoinsFlow().onEach { coin ->
            scope.coroutineContext.cancelChildren()
            val currentPrice = portfolioServerDataSource.fetchCurrencyPrice(coin.shortName)
            val profit = calculateProfit(currentPrice = currentPrice, savedPrice = coin.savedPrice, amount = coin.amount)
            val res = coin.toPortfolioCoin(profit = profit, currentPrice = currentPrice)
            performCancellationTask()
            trySend(Resource.Add(res))

        }.launchIn(this)
    }

    override suspend fun fetchAvailableCoins(): List<Coin> {
        return portfolioServerDataSource.fetchCoins()
    }

    private fun calculateProfit(savedPrice:Double, currentPrice:Double, amount: Float):Float{
        if(amount == 0f) return 0f
        val percentage = (currentPrice / savedPrice * 100).toFloat()
        return percentage - 100
    }

}
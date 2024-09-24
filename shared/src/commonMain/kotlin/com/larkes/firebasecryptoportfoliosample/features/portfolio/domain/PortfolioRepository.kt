package com.larkes.firebasecryptoportfoliosample.features.portfolio.domain

import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models.Coin
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models.PortfolioCoin
import com.larkes.firebasecryptoportfoliosample.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    suspend fun addCoin(coin: Coin, amount:Float)
    fun portfolioCoinsFlow(): Flow<Resource<PortfolioCoin>>
    suspend fun fetchAvailableCoins():List<Coin>
}
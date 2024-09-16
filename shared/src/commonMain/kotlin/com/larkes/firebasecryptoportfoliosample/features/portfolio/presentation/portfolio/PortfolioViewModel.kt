package com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asCommonFlow
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.PortfolioRepository
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.mapper.toPortfolioCoinUI
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models.PortfolioCoin
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioCoinUI
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioInfoUI
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioUIAction
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioUIEvent
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioUIState
import com.larkes.firebasecryptoportfoliosample.utils.Inject
import com.larkes.firebasecryptoportfoliosample.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PortfolioViewModel:ViewModel() {

    private val portfolioRepository:PortfolioRepository = Inject.di.get()

    private val _uiState = MutableStateFlow(PortfolioUIState())
    val uiState = _uiState.asCommonFlow()

    private val _uiAction:MutableStateFlow<PortfolioUIAction> = MutableStateFlow(PortfolioUIAction.None)
    val uiAction = _uiAction.asCommonFlow()

    init {
        fetchCoins()
    }

    private fun fetchCoins(){
        _uiState.value = _uiState.value.copy(isCoinsLoading = true)
        portfolioRepository.portfolioCoinsFlow().onEach {res ->
            when(res){
                is Resource.Add -> {
                    val coins = _uiState.value.coins.toMutableList()
                    coins.add(res.data!!.toPortfolioCoinUI())
                    coins.sortBy { it.portfolioAmount }
                    coins.reverse()
                    calculatePortfolioInfo(res.data)
                    _uiState.value = _uiState.value.copy(coins = coins)
                }
                is Resource.LoadingEnd -> {
                    _uiState.value = _uiState.value.copy(isCoinsLoading = false)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: PortfolioUIEvent){
        when(event){
            is PortfolioUIEvent.AddCoinClicked -> {
                obtainAddCoinClicked()
            }
            is PortfolioUIEvent.RefreshSwiped -> {
                obtainRefreshSwiped()
            }
        }
    }

    private fun obtainAddCoinClicked() {
        _uiAction.value = PortfolioUIAction.OpenAddCoinScreen
    }

    private fun obtainRefreshSwiped() {

    }

    private fun calculatePortfolioInfo(coin: PortfolioCoin){
        val portfolioInfo = _uiState.value.portfolioInfo
        val coinsSum = portfolioInfo.portfolioSum + (coin.currentPrice!! * coin.amount)
        val coinsProfit = portfolioInfo.moneyPortfolioProfit + ((coin.currentPrice - coin.savedPrice) * coin.amount)

        portfolioInfo.savedPrice += coin.savedPrice

        portfolioInfo.portfolioSum = coinsSum
        portfolioInfo.percentagePortfolioProfit = (portfolioInfo.savedPrice / coinsProfit).toFloat()
        portfolioInfo.moneyPortfolioProfit = coinsProfit

        _uiState.value = _uiState.value.copy(portfolioInfo = portfolioInfo)
    }

}
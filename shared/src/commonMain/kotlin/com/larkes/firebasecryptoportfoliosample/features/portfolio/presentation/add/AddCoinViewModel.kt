package com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asCommonFlow
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.PortfolioRepository
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.mapper.toCoinUI
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models.Coin
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.AddCoinUIAction
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.AddCoinUIEvent
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.AddCoinUIState
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.CoinUI
import com.larkes.firebasecryptoportfoliosample.utils.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddCoinViewModel:ViewModel() {

    private val portfolioRepository:PortfolioRepository = Inject.di.get()

    private val _uiState = MutableStateFlow(AddCoinUIState())
    val uiState = _uiState.asCommonFlow()

    private val _uiAction:MutableStateFlow<AddCoinUIAction> = MutableStateFlow(AddCoinUIAction.None)
    val uiAction = _uiAction.asCommonFlow()

    init {
        viewModelScope.launch {

            val coins = portfolioRepository.fetchAvailableCoins().map { it.toCoinUI() }

            _uiState.value = _uiState.value.copy(
                selectedCoin =  coins[0],
                availableCoins = coins,
                isCoinsLoading = false,
                currencyPrice = coins[0].price
            )

        }
    }

    fun onEvent(event:AddCoinUIEvent){

        when(event){
            is AddCoinUIEvent.AmountTyped -> {
                obtainAmountTyped(event.amount)
            }
            is AddCoinUIEvent.CurrencyPriceTyped -> {
                obtainCurrencyPriceTyped(event.price)
            }
            is AddCoinUIEvent.CurrencySelected -> {
                obtainCurrencySelected(event.coinUI)
            }
            is AddCoinUIEvent.DoneClicked -> {
                obtainDoneClicked()
            }
            is AddCoinUIEvent.ArrowBackClicked -> {
                obtainArrowBackClicked()
            }
        }

    }

    private fun obtainArrowBackClicked() {
        _uiAction.value = AddCoinUIAction.OpenPortfolioScreen
    }

    private fun obtainDoneClicked() {
        viewModelScope.launch {
            if(_uiState.value.selectedCoin != null && _uiState.value.currencyPrice != null && _uiState.value.amount != null){
                portfolioRepository.addCoin(
                    coin = Coin(
                        imageSrc = _uiState.value.selectedCoin!!.imageSrc,
                        price = _uiState.value.currencyPrice!!,
                        shortName = _uiState.value.selectedCoin!!.shortName,
                        title = _uiState.value.selectedCoin!!.title
                    ),
                    amount = _uiState.value.amount!!
                )
                _uiAction.value = AddCoinUIAction.OpenPortfolioScreen
            }
        }
    }

    private fun obtainCurrencyPriceTyped(price: String) {
        try {
            val num = price.toDouble()
            _uiState.value = _uiState.value.copy(currencyPrice = num)
        }catch (e:Exception){}
    }

    private fun obtainAmountTyped(amount: String) {
        try {
            val num = amount.toFloat()
            _uiState.value = _uiState.value.copy(amount = num)
        }catch (e:Exception){

        }
    }

    private fun obtainCurrencySelected(currency:CoinUI){
        _uiState.value = _uiState.value.copy(
            selectedCoin = currency,
            currencyPrice = currency.price,
            amount = 0f
        )

    }

}
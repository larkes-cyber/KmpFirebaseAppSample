package com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models

data class AddCoinUIState(
    val selectedCoin:CoinUI? = null,
    val availableCoins:List<CoinUI> = listOf(),
    val isCoinsLoading:Boolean = false,
    val amount:Float? = null,
    val currencyPrice:Double? = null
)
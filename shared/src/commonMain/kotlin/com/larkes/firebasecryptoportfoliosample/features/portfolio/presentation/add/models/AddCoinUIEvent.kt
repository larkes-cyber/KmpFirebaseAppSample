package com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models

sealed class AddCoinUIEvent {
    data class AmountTyped(val amount:String):AddCoinUIEvent()
    data class CurrencyPriceTyped(val price:String):AddCoinUIEvent()
    data class CurrencySelected(val coinUI: CoinUI):AddCoinUIEvent()
    data object DoneClicked:AddCoinUIEvent()
    data object ArrowBackClicked:AddCoinUIEvent()
}
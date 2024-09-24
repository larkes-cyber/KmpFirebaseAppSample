package com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models

sealed class AddCoinUIAction {

    data object OpenPortfolioScreen:AddCoinUIAction()
    data object None:AddCoinUIAction()

}
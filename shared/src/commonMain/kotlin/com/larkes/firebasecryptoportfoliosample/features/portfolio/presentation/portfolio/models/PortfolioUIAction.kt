package com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models

sealed class PortfolioUIAction {
    data object OpenAddCoinScreen: PortfolioUIAction()
    data object None: PortfolioUIAction()
}
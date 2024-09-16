package com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models

sealed class PortfolioUIEvent {
    data object AddCoinClicked: PortfolioUIEvent()
    data object RefreshSwiped: PortfolioUIEvent()

}
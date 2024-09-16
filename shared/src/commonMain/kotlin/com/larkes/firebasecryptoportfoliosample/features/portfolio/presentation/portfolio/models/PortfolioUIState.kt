package com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models

data class PortfolioUIState(
    val coins:List<PortfolioCoinUI> = listOf(),
    val portfolioInfo: PortfolioInfoUI = PortfolioInfoUI(
        portfolioSum = 0.0,
        moneyPortfolioProfit = 0.0,
        percentagePortfolioProfit = 0.0f,
        savedPrice = 0.0
    ),
    val isPortfolioInfoLoading:Boolean = false,
    val isCoinsLoading:Boolean = true,
    val error:String = ""
)
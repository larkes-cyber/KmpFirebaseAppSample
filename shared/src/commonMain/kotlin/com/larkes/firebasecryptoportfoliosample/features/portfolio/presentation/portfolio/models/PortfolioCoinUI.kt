package com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models

data class PortfolioCoinUI(
    val id:String,
    val title:String,
    val shortName:String,
    val imageSrc:String,
    val portfolioAmount:Float,
    val currencyPrice:Float,
    val profit:Float
)
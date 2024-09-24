package com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models

data class PortfolioCoin(
    val id:String,
    val shortName:String,
    val title:String,
    val imageSrc:String,
    val amount:Float,
    val savedPrice:Double,
    val currentPrice:Double? = null,
    val profit:Float? = null
)
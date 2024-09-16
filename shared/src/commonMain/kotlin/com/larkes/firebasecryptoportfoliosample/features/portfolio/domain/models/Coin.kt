package com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models

data class Coin(
    val shortName:String,
    val title:String,
    val imageSrc:String,
    val price:Double
)
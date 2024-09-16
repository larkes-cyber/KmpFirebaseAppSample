package com.larkes.firebasecryptoportfoliosample.features.portfolio.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PortfolioCoinEntity(
    val id:String,
    val shortName:String,
    val title:String,
    val imageSrc:String,
    val amount:Float,
    val savedPrice:Double
)
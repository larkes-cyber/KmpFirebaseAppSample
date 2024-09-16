package com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.mapper

import com.larkes.firebasecryptoportfoliosample.features.portfolio.data.models.PortfolioCoinEntity
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models.Coin
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models.PortfolioCoin
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.CoinUI
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioCoinUI

fun PortfolioCoinEntity.toPortfolioCoin(profit:Float, currentPrice:Double):PortfolioCoin{
    return PortfolioCoin(
        id = id,
        amount = amount,
        imageSrc = imageSrc,
        savedPrice = savedPrice,
        shortName = shortName,
        title = title,
        profit = profit,
        currentPrice = currentPrice
    )
}

fun PortfolioCoin.toPortfolioCoinUI():PortfolioCoinUI{
    return PortfolioCoinUI(
        id = id,
        currencyPrice = currentPrice!!.toFloat(),
        imageSrc = imageSrc,
        portfolioAmount = (amount * currentPrice).toFloat(),
        profit = profit!!,
        shortName = shortName,
        title = title
    )
}

fun Coin.toCoinUI():CoinUI{
    return CoinUI(
        title = title,
        shortName = shortName,
        imageSrc = imageSrc,
        price = price
    )
}
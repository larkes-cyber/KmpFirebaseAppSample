package com.larkes.firebasecryptoportfoliosample.features.portfolio.data.source

import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.models.Coin
import kotlinx.coroutines.delay
import kotlin.random.Random

class PortfolioServerDataSource {

    private fun generatePrice(start:Float, end:Float):Float{
        return start + Random.nextFloat() * (end - start)
    }

    private val coins = listOf(
        Coin(
            shortName = "BTC",
            title = "Bitcoin",
            price = generatePrice(50000f, 70000f).toDouble(),
            imageSrc = "https://cryptologos.cc/logos/bitcoin-btc-logo.png"
        ),
        Coin(
            shortName = "ETH",
            title = "Ethereum",
            price = generatePrice(2000f, 3000f).toDouble(),
            imageSrc = "https://cryptologos.cc/logos/ethereum-eth-logo.png"
        ),
        Coin(
            shortName = "SOL",
            title = "Solana",
            imageSrc = "https://cryptologos.cc/logos/solana-sol-logo.png",
            price = generatePrice(100f, 130f).toDouble()
        ),
        Coin(
            shortName = "XRP",
            title = "XRP",
            price = generatePrice(0.35f, 1.00f).toDouble(),
            imageSrc = "https://cryptologos.cc/logos/xrp-xrp-logo.png?v=002"
        ), Coin(
            shortName = "LTC",
            title = "Litecoin",
            price = generatePrice(60f, 100f).toDouble(),
            imageSrc = "https://cryptologos.cc/logos/litecoin-ltc-logo.png?v=002"
        ), Coin(
            shortName = "BNB",
            title = "Binance coin",
            price = generatePrice(150f, 300f).toDouble(),
            imageSrc = "https://cryptologos.cc/logos/binance-coin-bnb-logo.png?v=002"
        ),
        Coin(
            shortName = "ATOM",
            title = "Cosmos Atom",
            price = generatePrice(4.5f, 10f).toDouble(),
            imageSrc = "https://cryptologos.cc/logos/cosmos-atom-logo.png?v=002"
        )
    )


    suspend fun fetchCurrencyPrice(shortName:String):Double{
        delay(200)
        val coin = coins.find { it.shortName == shortName }
        return coin!!.price
    }

    suspend fun fetchCoins():List<Coin>{
        delay(500)
        return coins
    }

}
package com.cryptos.prices

import com.crypto.prices.models.{Exchange, ExchangePrice, LastPrice, Price}

object TestModels {

  val bitstampXrpUsdExchangePrice = ExchangePrice(
    Exchange.Bitstamp,
    Price(LastPrice("0.56"))
  )

}

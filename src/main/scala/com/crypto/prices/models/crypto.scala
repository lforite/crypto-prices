package com.crypto.prices.models

import io.circe.Encoder

sealed trait Symbol
case class CryptoSymbol(value: String) extends Symbol
case class FiatSymbol(value: String) extends Symbol


case class LastPrice(value: String) extends AnyVal
case class Price(
  last: LastPrice
)
object Price {
  implicit val PriceEncoder: Encoder[Price] = Encoder.forProduct1("last")(p => p.last.value)
}

sealed trait Exchange
object Exchange {
  case object Bitstamp extends Exchange
}

case class ExchangePrice(
  exchange: Exchange,
  price: Price
)

object ExchangePrice {
  implicit val ExchangePriceEncoder: Encoder[ExchangePrice] = Encoder.forProduct2("exchange", "price")(ep => (ep.exchange.toString, ep.price))
}
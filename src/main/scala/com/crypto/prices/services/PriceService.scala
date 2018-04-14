package com.crypto.prices.services

import cats.effect.IO
import com.crypto.prices.models.{CryptoSymbol, ExchangePrice, FiatSymbol}
import cats.implicits._
import com.crypto.prices.clients.bitstamp.BitstampClient

trait PriceService {
  def getPrices(cryptoSymbol: CryptoSymbol, fiatSymbol: FiatSymbol): IO[List[ExchangePrice]]
}

case class PriceServiceImpl(bitstampClient: BitstampClient) extends PriceService {

  override def getPrices(cryptoSymbol: CryptoSymbol, fiatSymbol: FiatSymbol): IO[List[ExchangePrice]] = {
    val bitstampPrice = bitstampClient.getPrice(cryptoSymbol, fiatSymbol)

    List(bitstampPrice).sequence
  }
}
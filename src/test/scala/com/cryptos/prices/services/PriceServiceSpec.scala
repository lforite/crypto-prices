package com.cryptos.prices.services

import cats.effect.IO
import com.crypto.prices.clients.bitstamp.BitstampClient
import com.crypto.prices.models._
import com.crypto.prices.services.PriceServiceImpl
import org.specs2._
import org.specs2.ScalaCheck

class PriceServiceSpec extends Specification with ScalaCheck { def is = s2"""
   PriceService should return bitstamp price $returnBitstampPrice"""

  def returnBitstampPrice = {
    val exchangePrice = ExchangePrice(Exchange.Bitstamp, Price(LastPrice("10")))
    val service = PriceServiceImpl(mockBitstampClient(exchangePrice))
    service.getPrices(CryptoSymbol("BTC"), FiatSymbol("EUR")).unsafeRunSync() must_== List(exchangePrice)
  }

  private def mockBitstampClient(exchangePrice: ExchangePrice) = new BitstampClient {
    override def getPrice(cryptoSymbol: CryptoSymbol, fiatSymbol: FiatSymbol): IO[ExchangePrice] = IO.pure(exchangePrice)
  }
}
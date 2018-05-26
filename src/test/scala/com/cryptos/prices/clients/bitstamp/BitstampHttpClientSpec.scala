package com.cryptos.prices.clients.bitstamp

import com.crypto.prices.clients.bitstamp.BitstampHttpClient
import com.crypto.prices.configuration.BitstampConfiguration
import com.crypto.prices.models.{CryptoSymbol, FiatSymbol}
import com.cryptos.prices.clients.{HttpMock, HttpMocks, MockHttpServer}
import org.specs2.Specification

class BitstampHttpClientSpec extends Specification { def is = s2"""
    getPrice should return the latest price when Bitstamp returns 200 $latestPrice
    """

  def latestPrice = MockHttpServer.mockServer(
    HttpMock(HttpMocks.Bitstamp.getPrice("0.56"), "/") :: Nil
  ) { (port) =>
    val configuration = BitstampConfiguration(s"http://localhost:$port/")
    val client = BitstampHttpClient(configuration)

    client.getPrice(CryptoSymbol("xrp"), FiatSymbol("usd"))
      .unsafeRunSync().price.last.value should_== "0.56"
  }
}

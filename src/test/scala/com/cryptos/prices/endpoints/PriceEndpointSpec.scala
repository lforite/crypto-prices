package com.cryptos.prices.endpoints

import cats.effect.IO
import com.crypto.prices.endpoints.PriceEndpoint
import com.crypto.prices.models.{CryptoSymbol, ExchangePrice, FiatSymbol}
import com.crypto.prices.services.PriceService
import com.cryptos.prices.TestModels.bitstampXrpUsdExchangePrice
import org.http4s.dsl.io._
import org.http4s.{Method, Request, Uri, _}
import org.specs2.{ScalaCheck, Specification}

class PriceEndpointSpec extends Specification with ScalaCheck { def is = s2"""
  GET /crypto/{crypto-id}/prices/{fiat-symbol} should return 200 and the list of prices $getOk
  """

  def getOk = {
    val endpoint = PriceEndpoint(mockPriceService(List(bitstampXrpUsdExchangePrice)))
    val request = Request[IO](Method.GET, Uri.uri("/crypto/xrp/prices/usd"))

    endpoint
      .orNotFound(request)
      .unsafeRunSync()
      .status should_== Status.Ok
  }

  private def mockPriceService(exchangePrices: List[ExchangePrice]) = new PriceService {
    override def getPrices(cryptoSymbol: CryptoSymbol, fiatSymbol: FiatSymbol): IO[List[ExchangePrice]] = IO.pure(exchangePrices)
  }
}

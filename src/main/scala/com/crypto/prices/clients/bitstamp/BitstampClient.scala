package com.crypto.prices.clients.bitstamp

import cats.effect.IO
import com.crypto.prices.configuration.BitstampConfiguration
import com.crypto.prices.models._
import org.http4s.client.blaze.Http1Client

trait BitstampClient {
  def getPrice(cryptoSymbol: CryptoSymbol, fiatSymbol: FiatSymbol): IO[ExchangePrice]
}

case class BitstampHttpClient(
  config: BitstampConfiguration
) extends BitstampClient {

  override def getPrice(cryptoSymbol: CryptoSymbol, fiatSymbol: FiatSymbol): IO[ExchangePrice] = {
    for {
      httpClient      <- Http1Client[IO]()
      bitstampPrice   <- httpClient.expect[BitstampPrice](s"${config.url}/api/v2/ticker/${cryptoSymbol.value.toLowerCase}${fiatSymbol.value.toLowerCase}")
    } yield ExchangePrice(
      Exchange.Bitstamp,
      Price(
        LastPrice(bitstampPrice.last)
      )
    )
  }
}

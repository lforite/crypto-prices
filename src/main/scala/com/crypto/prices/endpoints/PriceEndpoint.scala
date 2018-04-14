package com.crypto.prices.endpoints

import cats.effect.IO
import com.crypto.prices.models.{CryptoSymbol, FiatSymbol}
import com.crypto.prices.services.PriceService
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.io.{->, /, GET, Ok, Root, _}
import io.circe.syntax._

case object PriceEndpoint {

  def apply(priceService: PriceService): HttpService[IO] = HttpService[IO] {
    case GET -> Root / "crypto" / cryptoSymbol / "prices" / fiatSymbol =>
      for {
        prices      <- priceService.getPrices(CryptoSymbol(cryptoSymbol), FiatSymbol(fiatSymbol))
        response    <- Ok(prices.asJson)
      } yield response
  }
}

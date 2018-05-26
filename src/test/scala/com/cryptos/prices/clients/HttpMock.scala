package com.cryptos.prices.clients

import cats.effect.IO
import io.circe.literal._
import org.http4s.dsl.io.{->, /, GET, Ok, Root, _}
import org.http4s.{HttpService, Request, Response}
import org.http4s.circe._

case class HttpMock(mock: HttpService[IO], route: String)
object HttpMock {
  def apply(pf: PartialFunction[Request[IO], IO[Response[IO]]], route: String): HttpMock = HttpMock(HttpService[IO] {pf}, route)
}


object HttpMocks {
  object Bitstamp {
    def getPrice(lastPrice: String): PartialFunction[Request[IO], IO[Response[IO]]] = {
      case GET -> Root / "api"/ "v2" / "ticker" / _ => Ok(json"""{ "last" : $lastPrice }""")
    }
  }
}

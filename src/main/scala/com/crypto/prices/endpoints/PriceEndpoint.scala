package com.crypto.prices.endpoints

import cats.effect.IO
import org.http4s.HttpService
import org.http4s.dsl.io.{->, /, GET, Ok, Root, _}

case object PriceEndpoint {

  val priceEndpoint: HttpService[IO] = HttpService[IO] {
    case GET -> Root / currency / "price" => Ok("The price is 10$ !")
  }
}

package com.crypto.prices.endpoints

import cats.effect.IO
import org.http4s.HttpService
import org.http4s.dsl.io.{->, /, GET, Ok, Root, _}

object FeedEndpoint {

  val feedEndpoint: HttpService[IO] = HttpService[IO] {
    case GET -> Root / currency / "feed" => Ok(s"This is $currency.")
  }
}

package com.crypto.prices

import cats.effect._
import com.crypto.prices.endpoints.{FeedEndpoint, PriceEndpoint}
import org.http4s._
import org.http4s.dsl.io._

import scala.concurrent.ExecutionContext.Implicits.global
import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode
import org.http4s.server.blaze._

object CryptoPricesApplication extends StreamApp[IO] {

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(8080, "localhost")
      .mountService(FeedEndpoint.feedEndpoint, "/")
      .mountService(PriceEndpoint.priceEndpoint, "/")
      .serve
}

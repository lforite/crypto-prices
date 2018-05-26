package com.crypto.prices

import cats.effect._
import com.crypto.prices.clients.bitstamp.BitstampHttpClient
import com.crypto.prices.configuration.BitstampConfiguration
import com.crypto.prices.endpoints.PriceEndpoint
import com.crypto.prices.services.PriceServiceImpl
import fs2.StreamApp.ExitCode
import fs2.{Stream, StreamApp}
import org.http4s.server.blaze._

import scala.concurrent.ExecutionContext.Implicits.global

object CryptoPricesApplication extends StreamApp[IO] {

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(8080, "localhost")
      .mountService(PriceEndpoint.apply(PriceServiceImpl(BitstampHttpClient(BitstampConfiguration()))), "/")
      .serve
}

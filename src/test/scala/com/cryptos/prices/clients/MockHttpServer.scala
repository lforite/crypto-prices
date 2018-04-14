package com.cryptos.prices.clients

import cats.effect.IO
import org.http4s.server.blaze.BlazeBuilder
import org.specs2.matcher.{MatchResult, StandardMatchResults}

import scala.util.Try

object MockHttpServer extends StandardMatchResults {

  def mockServer(mocks: List[HttpMock])(test: (Int) => MatchResult[Any]): MatchResult[Any] = {
    val mockBuilder = mocks.foldLeft(BlazeBuilder[IO].bindHttp(0, "0.0.0.0")) { (builder, mock) =>
      builder.mountService(mock.mock, mock.route)
    }

    (for {
      mockServer  <- mockBuilder.start
      port        = mockServer.address.getPort
      result      = Try(test(port)).fold(e => ko(e.getMessage), identity)
      _           <- mockServer.shutdown
    } yield result).unsafeRunSync()
  }
}

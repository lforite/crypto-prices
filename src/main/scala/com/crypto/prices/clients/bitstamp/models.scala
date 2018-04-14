package com.crypto.prices.clients.bitstamp

import cats.effect.IO
import io.circe.Decoder
import org.http4s.EntityDecoder
import org.http4s.circe.jsonOf

case class BitstampPrice(
  last: String
)
object BitstampPrice {
  implicit val jsonDecoder : Decoder[BitstampPrice] = Decoder.forProduct1("last")(BitstampPrice.apply)
  implicit val entityDecoder: EntityDecoder[IO, BitstampPrice] = jsonOf[IO, BitstampPrice]
}

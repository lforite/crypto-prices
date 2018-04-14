name := "crypto-prices"

version := "0.1"

scalaVersion := "2.12.4"

val http4sVersion = "0.18.8"
val circeVersion = "0.9.3"
val specs2Version = "4.0.2"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,

  "org.http4s" %% "http4s-circe" % http4sVersion,
  "io.circe" %% "circe-literal" % circeVersion,

  "org.specs2" %% "specs2-core" % specs2Version % "test",
  "org.specs2" %% "specs2-scalacheck" % specs2Version % "test"

)

scalacOptions ++= Seq("-Ypartial-unification")
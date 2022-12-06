import sbt._
import Keys._

object Dependencies {
  object Version {
    val http4s = "0.23.11"
    val scalaTest = "3.2.14"
    val pureConfig = "0.17.1"
    val finch = "0.33"
    val circe = "0.14.3"
    val doobie = "1.0.0-RC1"
    val combinators = "2.1.1"
    val munit = "0.7.29"
  }

  // Libraries
  val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest
  val http4sDsl = "org.http4s" %% "http4s-dsl" % Version.http4s
  val http4sEmberServer = "org.http4s" %% "http4s-ember-server" % Version.http4s
  val http4sEmberClient = "org.http4s" %% "http4s-ember-client" % Version.http4s
  val http4sCirce = "org.http4s" %% "http4s-circe" % Version.http4s
  val circeGeneric = "io.circe" %% "circe-generic" % Version.circe
  val circeLiteral = "io.circe" %% "circe-literal" % Version.circe
  val odin = "com.github.valskalla" %% "odin-core" % "0.13.0"
  val finchCore = "com.github.finagle" %% "finch-core" % Version.finch
  val slick = "com.typesafe.slick" %% "slick" % "3.3.3"
  val slf4jNop = "org.slf4j" % "slf4j-nop" % "1.6.4"
  val slickHikaricp = "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3"
  val reactiveMongo =
    "org.reactivemongo" %% "reactivemongo" % "1.1.0-noshaded-RC4"

  val doobieCore = "org.tpolecat" %% "doobie-core" % Version.doobie
  val doobiePostgres = "org.tpolecat" %% "doobie-postgres" % Version.doobie
  val doobieHikari = "org.tpolecat" %% "doobie-hikari"    % Version.doobie
  val doobieCirce = "org.tpolecat" %% "doobie-postgres-circe" % Version.doobie
  val pureConfig = "com.github.pureconfig" %% "pureconfig-core" % Version.pureConfig
  val config = "com.typesafe" % "config" % "1.4.2"

  val quill = "io.getquill" %% "quill-jdbc-zio" % "3.12.0"
  val quillProtoPostgres = "io.getquill" %% "quill-jasync-postgres" % "3.12.0.Beta1.7"
  val zioMagic = "io.github.kitlangton" %% "zio-magic" % "0.3.11"
  val postgres = "org.postgresql" % "postgresql" % "42.5.0"
  val parserCombinators = "org.scala-lang.modules" %% "scala-parser-combinators" % Version.combinators
  val munit = "org.scalameta" %% "munit" % Version.munit

  /*
  "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC1" % "test"  // ScalaTest support for typechecking statements.
   */

  val doobieDeps = Seq(doobieCore, doobiePostgres, doobieHikari, doobieCirce)
  val slickDeps = Seq(slick, slf4jNop, slickHikaricp)
  val quillDeps = Seq(quillProtoPostgres)
  val shapeless = "org.typelevel" %% "shapeless3-deriving" % "3.0.1"

  // Projects
  val fimppDependencies: Seq[ModuleID] = Seq(parserCombinators, munit % Test)
}

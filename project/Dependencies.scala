import sbt._
import Keys._

object Dependencies {
  object Version {
    val combinators = "2.1.1"
    val munit = "0.7.29"
  }

  val parserCombinators = "org.scala-lang.modules" %% "scala-parser-combinators" % Version.combinators
  val munit = "org.scalameta" %% "munit" % Version.munit

  val fimppDependencies: Seq[ModuleID] = Seq(parserCombinators, munit % Test)
}

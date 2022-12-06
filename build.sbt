import Dependencies._

val Main = "stasiak.karol.fimpp.Main"
organization := "stasiak.karol"

name := "fimpp"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.13.10"

libraryDependencies ++= fimppDependencies

scalacOptions ++= Seq(
  "-deprecation",
  "-Ywarn-unused",
  "-Xlint:unused"
)

Compile / run / mainClass := Some(Main)

// scalaVersion := "2.13.10"
// semanticdbEnabled := true
// semanticdbVersion := scalafixSemanticdb.revision

assembly / mainClass := Some(Main)
assembly / assemblyOutputPath := file("bin/fimpp.jar")
assembly / assemblyJarName := "fimpp.jar"
// enablePlugins(ScalaNativePlugin)

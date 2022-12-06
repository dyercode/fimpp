import Dependencies._

val Main = "stasiak.karol.fimpp.Main"
organization := "stasiak.karol"

name := "fimpp"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.13.10"

libraryDependencies ++= fimppDependencies

Compile / run / mainClass := Some(Main)

assembly / mainClass := Some(Main)
assembly / assemblyOutputPath := file("bin/fimpp.jar")
assembly / assemblyJarName := "fimpp.jar"

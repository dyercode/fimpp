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

/*
attempt to make a task to output the jar location so I could publish based on that instead of naming every jar the smae

lazy val gutName = taskKey[Unit]("An example task that will return no value.")

gutName := {
  val s: TaskStreams = streams.value
  val thing = assembly / assemblyJarName
  s.log.info(thing)
}
*/

name := "aegisit-api"

version := "1.0-SNAPSHOT"

autoScalaLibrary := false

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.nulab-inc" %% "play2-oauth2-provider" % "0.7.1"
)

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"

//play.Project.playJavaSettings
lazy val root = (project in file(".")).enablePlugins(PlayScala)

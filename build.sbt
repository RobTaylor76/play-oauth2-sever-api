name := "aegisit-api"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.nulab-inc" %% "play2-oauth2-provider" % "0.7.1"
)     

play.Project.playJavaSettings

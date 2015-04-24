name := "instaplay"

version := "0.0.1"

lazy val `instaplay` = (project in file(".")).enablePlugins(PlayScala, PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws )

libraryDependencies ++= Seq(
  "com.sachinhandiekar" % "jInstagram" % "1.0.10",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "mysql" % "mysql-connector-java" % "5.1.6"
)

unmanagedSourceDirectories in Compile += baseDirectory.value / "web"

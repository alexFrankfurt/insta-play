name := "instaplay"

version := "1.0"

lazy val `instaplay` = (project in file(".")).enablePlugins(PlayScala, PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws )

libraryDependencies ++= Seq(
  "com.sachinhandiekar" % "jInstagram" % "1.0.10"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  
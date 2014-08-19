
name := "FPS_book"

version := "1.0"

scalaVersion := "2.10.1"

resolvers ++= Seq(
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.10" % "2.2.1",
    "org.scalacheck" %% "scalacheck" % "1.11.5" % "test",
    "junit" % "junit" % "4.11"
)

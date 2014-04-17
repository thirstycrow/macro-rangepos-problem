import sbt._
import Keys._

object SimpleBuild extends Build {

  val root = Project("scoverage-problem", file("."))
    .aggregate(macro, client)
    .settings(commonSettings: _*)

  lazy val macro = Project("macro", file("macro"))
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        "org.scala-lang" % "scala-reflect" % Dependencies.V.Scala
      )
    )

  lazy val client = Project("client", file("client"))
    .dependsOn(macro)
    .settings(commonSettings: _*)
    .settings(
      scalacOptions ++= Seq(
        "-Yrangepos"
      )
    )

  lazy val commonSettings = Seq(
    scalaVersion := Dependencies.V.Scala
  )

  object Dependencies {
    object V {
      val Scala = "2.10.4"
    }
  }
}

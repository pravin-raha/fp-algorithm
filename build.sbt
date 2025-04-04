name := "fp_algorithm"

version := "0.1"

scalaVersion := "2.13.16"

javacOptions ++= Seq("-source", "21", "-target", "24")

libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")

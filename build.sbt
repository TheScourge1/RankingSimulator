name := "RankingSimulator"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.6"
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.3.0"
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0"
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
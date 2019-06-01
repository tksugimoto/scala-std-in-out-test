lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      scalaVersion := "2.11.7",
    )),
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.23",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      scalaVersion := "2.11.7",
    )),
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )

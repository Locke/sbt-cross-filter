
lazy val customTask = taskKey[Unit]("does some custom things")


lazy val root = (project in file("."))
  .settings(
    crossScalaVersions := Nil,
    scalaVersion := "2.12.18",
    customTask := {
      val projectName = name.value
      val scalaVersionValue = scalaVersion.value
      println(s"doing some custom things for $projectName with Scala $scalaVersionValue")
    },
  )
  .aggregate(foo, bar)

lazy val foo = (project in file("foo"))
  .settings(
    crossScalaVersions := Seq("2.12.18"),
    scalaVersion := "2.12.18",
    customTask := {
      val projectName = name.value
      val scalaVersionValue = scalaVersion.value
      println(s"doing some custom things for $projectName with Scala $scalaVersionValue")
    },
  )

lazy val bar = (project in file("bar"))
  .settings(
    crossScalaVersions := Seq("2.12.18", "2.13.11"),
    scalaVersion := "2.12.18",
    customTask := {
      val projectName = name.value
      val scalaVersionValue = scalaVersion.value
      println(s"doing some custom things for $projectName with Scala $scalaVersionValue")
    },
  )

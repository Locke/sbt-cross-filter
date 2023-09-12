import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val customTask = taskKey[Unit]("does some custom things")

lazy val customTaskAll = taskKey[Unit]("does the custom things of all projects")

val aggregatedProjects = ScopeFilter(inAggregates(ThisProject, includeRoot = false))

customTaskAll := {
  println("doing the custom task on all aggregated projects...")

  customTask.all(aggregatedProjects).value
}



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
  .aggregate(fooJVM, bar)

lazy val foo = (crossProject(JVMPlatform).crossType(CrossType.Pure) in file("foo"))
  .settings(
    crossScalaVersions := Seq("2.12.18"),
    scalaVersion := "2.12.18",
    customTask := {
      val projectName = name.value
      val scalaVersionValue = scalaVersion.value
      println(s"doing some custom things for $projectName with Scala $scalaVersionValue")
    },
    libraryDependencies ++= Seq(
      "org.http4s" %%% "http4s-core"         % "0.23.23",
      "org.http4s" %%% "http4s-client"       % "0.23.23",
      "org.http4s" %%% "http4s-ember-client" % "0.23.23",
    ),
  )

lazy val fooJVM = foo.jvm

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

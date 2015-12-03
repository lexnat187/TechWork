name := "TechWork"

version := "1.0.0"

organization      := "com.awalker"

scalaVersion      := "2.11.7"

publishMavenStyle := true

//crossPaths       := false

//autoScalaLibrary := false

mainClass in Compile := Some("")

scalacOptions ++= Seq(
  "-target:jvm-1.8",
  "-unchecked",
  "-Xcheckinit",
  "-encoding", "UTF-8",
  "-Xmax-classfile-name", "200")

javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-encoding", "UTF-8",
  "-XDignore.symbol.file")

libraryDependencies ++= {
  Seq(
    "org.apache.commons" % "commons-csv" % "1.2",
    "org.apache.commons" % "commons-io" % "1.3.2",
    "joda-time" % "joda-time" % "2.9",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test->*" excludeAll (ExclusionRule(organization="org.junit", name="junit"),ExclusionRule(organization="org.seleniumhq.selenium", name="selenium-java")),
    "org.scalacheck" %% "scalacheck" % "1.12.4" % "test",
    "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
  )
}

parallelExecution in Test := false

javaOptions ++= Seq("-enableassertions", "-XX:+CMSClassUnloadingEnabled", "-XX:PermSize=128M", "-XX:MaxPermSize=2048M")

// Enable SBT 13.2 incremental compiler optimisations.
incOptions := incOptions.value.withNameHashing(true)

// skip scala tests during assembly
test in assembly := {}
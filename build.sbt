name := """wikipedia2text"""

version := "1.0"

scalaVersion := "2.11.7"

mainClass in (Compile,run) := Some("jp.mwsoft.wikipedia.totext.Main")

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

libraryDependencies ++= Seq(
  "commons-cli" % "commons-cli" % "1.2",
  "joda-time" % "joda-time" % "2.9.3",
  "org.atilika.kuromoji" % "kuromoji" % "0.7.7",
  "org.joda" % "joda-convert" % "1.5",
  "org.apache.commons" % "commons-compress" % "1.11",
  "junit" % "junit" % "4.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource



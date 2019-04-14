name := "spark-scala"
organization := "com.brevitaz.samples"
version := "1.0"

val sparkVersion = "2.3.1"
scalaVersion := "2.11.11"
crossPaths := false
scalacOptions += "-target:jvm-1.8"

resolvers += Resolver.mavenCentral

libraryDependencies += {
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"
}

dependencyOverrides += "com.google.guava" % "guava" % "12.0.1"



name := "kafka-demo"

version := "0.1.17-SNAPSHOT"

scalaVersion := "2.9.2"

libraryDependencies ++=  Seq(("org.apache.kafka" % "kafka_2.9.2" % "0.8.1.1" % "compile")
  .exclude("com.sun.jmx", "jmxri")
  .exclude("javax.jms", "jms")
  .exclude("com.sun.jdmk", "jmxtools"))
//libraryDependencies += "com.sun.jdmk" % "jmxtools-1.2" % "1.2"

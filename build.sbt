name := "aws"

version := "1.0"
organization      := "com.scala.aws"
scalaVersion := "2.10.4"

//resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

//set main class for sbt-run
//mainClass in Compile := Some("aws")
//mainClass in (Compile, run) := Some("service.PersistTransactionsToAws")

//set main class for packaging main in jar
mainClass in (Compile, packageBin) := Some("service.PersistTransactionsToAws")

//sources in Compile <<= (sources in Compile).map(_ filter(_.name == "JsonParserDemo.scala"))

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.1"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.2.1"

libraryDependencies += "org.json" % "json" % "20141113"

libraryDependencies += "com.propensive" % "rapture-io" % "0.7.2"

libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.2.1"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.4"

libraryDependencies += "org.apache.spark" % "spark-sql_2.10" % "1.2.1"

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

libraryDependencies += "net.java.dev.jets3t" % "jets3t" % "0.9.3"

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.10.10"

libraryDependencies += "org.apache.commons" % "commons-dbcp2" % "2.0.1"

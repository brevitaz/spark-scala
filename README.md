
# Spark Scala Sample

This sample is developed using Spark 2.3.1 version and Scala 2.11 library. It is tested with Hadoop version 2.7.

## Prerequisites
- Install sbt https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Linux.html (instead of this, you can use build in `sbt shell` to Intellij Idea)
- To run the demo without `-local` option, ensure Hadoop version 2.7 is setup locally
- Execute the following command from the project root directory
  ```ln -s "$(pwd)/src/main/resources/crime-sample-in" /tmp/crime-sample-in```

## Running from development environment

- Import the project as `SBT project` in IntelliJ Idea
- Check the option to `Enable auto-import`
- Setup run configuration with program argument ```-local```. Instead of creating output files to HDFS, this option will create output file to local FS for testing purpose even if Hadoop isn't setup locally.
- Select `Include dependencies with "Provided" scope` in the run configuration window
- Verify generated output files under ```/tmp/crime-sample-out``` in local filesystem.

## Build Jar

``` sbt clean package```

## Run spark job jar to populate in HDFS

For each example you can add `-local` at the end to pass it as a parameter to the application.

### Import files to HDFS using RDD

```spark-submit --master local[*] --class com.brevitaz.samples.sparkscala.ImportFilesToHdfsUsingRdd target/spark-scala-1.0.jar```

### Import files to HDFS using Dataset

```spark-submit --master local[*] --class com.brevitaz.samples.sparkscala.ImportFilesToHdfsUsingDataset target/spark-scala-1.0.jar```

### Import files to HDFS as parquet format

```spark-submit --master local[*] --class com.brevitaz.samples.sparkscala.ImportFilesToHdfsAsParquet target/spark-scala-1.0.jar```

### Filter and group by demo

```spark-submit --master local[*] --class com.brevitaz.samples.sparkscala.FilterAndGroupBy target/spark-scala-1.0.jar```


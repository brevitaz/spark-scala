
# Spark Scala Sample

This sample is developed using Spark 2.3.1 version and Scala 2.11 library. It is tested with Hadoop version 2.7.

## Prerequisites

- To run the demo without `-local` option, ensure Hadoop version 2.7 is setup locally
- Execute the following command
  ```ln -s ./src/main/resources/crime-sample-in /tmp/crime-sample-in```

## Running from development environment

- Import the project as "Gradle project" in IntelliJ Idea
- Check the option to "Enable auto-import"
- Setup run configuration with program argument ```-local```. Instead of creating output files to HDFS, this option will create output file to local FS for testing purpose even if Hadoop isn't setup locally.
- Verify generated output files under ```/tmp/crime-sample-out``` in local filesystem.

## Build Jar

``` ./gradlew clean jar```

## Run spark job jar to populate in HDFS

### Import files to HDFS using RDD

```spark-submit --master local[*] --class com.brevitaz.samples.sparkscala.ImportFilesToHdfsUsingRdd build/libs/spark-scala-1.0.jar```

### Import files to HDFS using Dataset

```spark-submit --master local[*] --class com.brevitaz.samples.sparkscala.ImportFilesToHdfsUsingDataset build/libs/spark-scala-1.0.jar```

### Import files to HDFS as parquet format

```spark-submit --master local[*] --class com.brevitaz.samples.sparkscala.ImportFilesToHdfsAsParquet build/libs/spark-scala-1.0.jar```

### Filter and group by demo

```spark-submit --master local[*] --class com.brevitaz.samples.sparkscala.FilterAndGroupBy build/libs/spark-scala-1.0.jar```


package com.brevitaz.samples.sparkscala

import java.sql.Date

import org.apache.spark.sql.SaveMode

object ImportFilesToHdfsUsingDataset extends SparkSessionWrapper{

  case class CrimeData(id: Long, caseNumber: String, date: Date, block: String, iucr: String, primaryType: String,
                       description: String, locationDescription: String, arrest:Boolean,
                       domestic: Boolean, beat: Integer, district: Integer, ward: String,
                       communityArea: Integer, fbiCode: String, xCoordinate: Long, yCoordinate: Long,
                       year: Integer, updatedOn: Date, lat: Double, lon: Double, location:String)

  def main(args: Array[String]) {
    val file = "file:///tmp/crime-sample-in/Crimes.csv.*"

    val isLocal = args.contains("-local")

    val outFs = if (isLocal) "file:///tmp/crime-sample-out" else "hdfs:///tmp/crime-sample-out"

    val outFile = s"$outFs/CrimesUsingDataset.csv"

    val spark = sparkSession(isLocal)

    // import methods to convert common scala objects to DF
    import spark.implicits._

    val ds = spark.read.option("header","true")
      .option("inferSchema", "true")
      .csv(file)
      .as[CrimeData]

    ds.show()

    ds.write.mode(SaveMode.Overwrite).csv(outFile)

    spark.stop()
  }


}

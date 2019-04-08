package com.brevitaz.samples.sparkscala

import java.sql.Date

object ImportFilesToHdfsUsingRdd extends SparkSessionWrapper{

  case class CrimeData(id: Long, caseNumber: String, date: Date, block: String, iucr: String, primaryType: String,
                       description: String, locationDescription: String, arrest:Boolean,
                       domestic: Boolean, beat: Integer, district: Integer, ward: String,
                       communityArea: Integer, fbiCode: String, xCoordinate: Long, yCoordinate: Long,
                       year: Integer, updatedOn: Date, lat: Double, lon: Double, location:String)

  def main(args: Array[String]) {
    val file = "file:///tmp/crime-sample-in/Crimes.csv.*"

    val isLocal = args.contains("-local")

    val outFs = if(isLocal) "file:///tmp/crime-sample-out" else "hdfs:///tmp/crime-sample-out"

    val outFile = s"$outFs/CrimesUsingRdd.csv"

    val spark = sparkSession(isLocal)

    // read the text file and create DataSet[String]
    val rdd = spark.sparkContext.textFile(file)

    rdd.saveAsTextFile(outFile)

    spark.stop()
  }


}

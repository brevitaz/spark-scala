package com.brevitaz.samples.sparkscala

import java.sql.Date

import org.apache.spark.sql.SaveMode

object FilterAndGroupBy extends SparkSessionWrapper{

  case class CrimeData(id: Long, caseNumber: String, date: Date, block: String, iucr: String, primaryType: String,
                       description: String, locationDescription: String, arrest:Boolean,
                       domestic: Boolean, beat: Integer, district: Integer, ward: String,
                       communityArea: Integer, fbiCode: String, xCoordinate: Long, yCoordinate: Long,
                       year: Integer, updatedOn: Date, lat: Double, lon: Double, location:String)

  def main(args: Array[String]) {
    val file = "file:///tmp/crime-sample-in/Crimes.csv.*"

    val isLocal = args.contains("-local")

    val outFs = if (isLocal) "file:///tmp/crime-sample-out" else "hdfs:///tmp/crime-sample-out"

    val outFile = s"$outFs/CrimesByLocation.csv"

    val spark = sparkSession(isLocal)

    // import methods to convert common scala objects to DF
    import spark.implicits._

    // read the csv and create DataSet[CrimeData]
    val crimes = spark.read.option("header","true")
      .option("inferSchema", "true")
      .csv(file).as[CrimeData]

    val domesticViolenceByLocation = crimes
      .filter("domestic")             // only domestic violence
      .groupBy(crimes("locationDescription"))      // group by location
      .count()

    domesticViolenceByLocation.show()

    // for ease of viewing output, lets repartition to 1 to see output as a single file
    val domesticViolenceByLocationOut = domesticViolenceByLocation.repartition(1)

    // write the output
    domesticViolenceByLocationOut.write.mode(SaveMode.Overwrite).option("header","true").csv(outFile)

    spark.stop()
  }


}

package com.brevitaz.samples.sparkscala

import org.apache.spark.sql.SparkSession
trait SparkSessionWrapper {

  def sparkSession(local: Boolean): SparkSession = {
    lazy val spark = {
      val builder = SparkSession
        .builder()
        .config("spark.hadoop.validateOutputSpecs", "false")

      if (local) {
        builder.master("local[*]")
      }

      builder.getOrCreate()
    }

    spark
  }
}

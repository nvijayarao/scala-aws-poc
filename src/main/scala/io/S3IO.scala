package io

import play.api.libs.json.JsValue
import org.apache.hadoop.conf.Configuration
import properties.ConfigProperties
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client

import scala.collection.JavaConversions._


/**
 * @author nvijayar
 */
class S3IO {
  
  val basicCredentials = new BasicAWSCredentials(ConfigProperties.getProperty("s3.accessKey"), ConfigProperties.getProperty("s3.secretKey")) 
  val client = new AmazonS3Client(basicCredentials)
  
  def upload(transactions:org.apache.spark.rdd.RDD[(JsValue)], path: String) = {
    transactions.saveAsTextFile("s3://"+path)
  }
  
}
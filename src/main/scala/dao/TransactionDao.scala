package dao

import com.datastax.spark.connector._
import play.api.libs.json._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import com.datastax.spark.connector.CassandraRow
import org.apache.hadoop.security.UserGroupInformation.HadoopConfiguration
import org.apache.hadoop.conf.Configuration
import properties.ConfigProperties
import io.S3IO
import context.Context

/**
 * @author nvijayar
 */
class TransactionDao {
  
  val s3IO:S3IO = new S3IO
   
  def getTransactionsByFileId(fileId: String) : CassandraTableScanRDD[CassandraRow] = {
    
    val txtData = Context.getSparkContext().cassandraTable("keyspace", "tablename").where("file_id = ? and hashkey in(?,?,?,?)")
    txtData
  }
  
  def uploadToS3(transactions:org.apache.spark.rdd.RDD[(JsValue)], fileId: String)  = {
    
      val hadoopConf = Context.getSparkContext().hadoopConfiguration
      hadoopConf.set("fs.s3.impl", "org.apache.hadoop.fs.s3native.NativeS3FileSystem")
      hadoopConf.set("fs.s3.awsAccessKeyId", ConfigProperties.getProperty("s3.accessKey"))
      hadoopConf.set("fs.s3.awsSecretAccessKey", ConfigProperties.getProperty("s3.secretKey"))
      
      s3IO.upload(transactions, ConfigProperties.getProperty("s3.bucket")+"/transactions/"+fileId)
  }
  
}
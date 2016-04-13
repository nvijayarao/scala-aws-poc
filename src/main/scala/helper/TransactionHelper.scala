package helper

import play.api.libs.json.Json
import play.api.libs.json.JsValue
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import com.datastax.spark.connector.CassandraRow
import play.api.libs.json.JsObject

/**
 * @author nvijayar
 */
class TransactionHelper extends AwsHelper{
  
  def convertToJson(transRDD : CassandraTableScanRDD[CassandraRow] , fileId: String): org.apache.spark.rdd.RDD[(JsValue)] = {
    val jsonVal = transRDD.map { x => {
              val trxJsVal = Json.parse(x.getString("json_string")).\("Tx")
              val receivedDtTime = x.getStringOption("received_datetime")
              val sequence = x.getStringOption("sequence")
              val is_valid = x.getStringOption("is_valid")
              val rpthdr_rptgpty_agt = x.getStringOption("rpthdr_rptgpty_agt")
              val rpthdr_submitgpty_agt = x.getStringOption("rpthdr_submitgpty_agt")
              val addFiledsJsonObj = Json.obj("receivedDtTime" -> Json.toJson(receivedDtTime), "sequence" -> Json.toJson(sequence), 
                  "is_valid" -> Json.toJson(is_valid), "rpthdr_rptgpty_agt" -> Json.toJson(rpthdr_rptgpty_agt)
                  , "rpthdr_submitgpty_agt" -> Json.toJson(rpthdr_submitgpty_agt))
              val returnJson: JsObject = trxJsVal.as[JsObject] ++ addFiledsJsonObj
              returnJson.as[JsValue]
              }
              
            }
    jsonVal
  }
  
  
}
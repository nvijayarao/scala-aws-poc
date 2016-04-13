package helper

import play.api.libs.json.JsValue
import play.api.libs.json.Json
import java.text.SimpleDateFormat

/**
 * @author nvijayar
 */

trait AwsHelper {
  
  val sdfTime = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
  val sdf = new SimpleDateFormat("YYYY-MM-dd")
  
  def checkJsNullVals(jsVal: JsValue): String = {
   val str = Json.stringify(jsVal).replaceAll("\"", "")
   if(str.equalsIgnoreCase("null")) "|" else str+"|"
  }
  
  def checkDateTimeJsNullVals(jsVal: JsValue): String = {
   val str = Json.stringify(jsVal).replaceAll("\"", "")
   if(str.equalsIgnoreCase("null")) "|" else sdfTime.format(sdfTime.parse(str))+"|"
  }
  
  def checkDateJsNullVals(jsVal: JsValue): String = {
   val str = Json.stringify(jsVal).replaceAll("\"", "")
   if(str.equalsIgnoreCase("null")) "|" else sdf.format(sdf.parse(str))+"|"
  }
  
  def checkNullVals(strVal: String): String = {
   if(strVal != null || !strVal.equalsIgnoreCase("")) strVal else ""
  }
  
  def checkDateTimeNullVals(strVal: String): String = {
    if(strVal != null || !strVal.equalsIgnoreCase("")) sdfTime.format(sdfTime.parse(strVal)) else ""
  }
  
  def checkDateNullVals(strVal: String): String = {
   if(strVal != null || !strVal.equalsIgnoreCase("")) sdf.format(sdf.parse(strVal)) else ""   
  }
  
  def getUUID(str:String):String ={    
    val uuid = java.util.UUID.nameUUIDFromBytes(str.getBytes).toString()
    uuid+"|"
  }
  
  def getJsUUID(str:String):String ={    
    val uuid = java.util.UUID.nameUUIDFromBytes(str.getBytes).toString()
    uuid
  }
}
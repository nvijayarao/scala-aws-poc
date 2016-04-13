package properties

import com.typesafe.config.ConfigFactory
import java.io.File

/**
 * @author nvijayar
 */
object ConfigProperties {
  
  val appProperties = ConfigFactory.load()  
  val sysProperties = ConfigFactory.parseFile(new File(appProperties.getString("system-config")))
  
  
  def getApplicationProperty(key: String) : String = {
    var value = ""
    try{
      value = appProperties.getString(key)
    }
    catch {
      case t: Throwable => t.printStackTrace()
    }
    value
  }
  
  def getSystemProperty(key: String) : String = {
    var value = ""
    try{
      value = sysProperties.getString(key)
    }
    catch {
      case t: Throwable => t.printStackTrace()
    }
    value
  }
  
  def getProperty(key: String) : String = {
    var value = getApplicationProperty(key)
    if("".equals(value))
      value = getSystemProperty(key)

    value
  }
}
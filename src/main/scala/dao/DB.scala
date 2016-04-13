package dao

import org.apache.commons.dbcp2.BasicDataSource
import properties.ConfigProperties
import java.sql.Connection
import java.sql.Statement
import java.sql.ResultSet



/**
 * @author nvijayar
 */

object DB{
  
  val connectionPool = new BasicDataSource()
  connectionPool.setDriverClassName(ConfigProperties.getProperty("db.default.driver"))
  connectionPool.setUsername(ConfigProperties.getProperty("db.default.user"))
  connectionPool.setPassword(ConfigProperties.getProperty("db.default.password"))
  connectionPool.setUrl(ConfigProperties.getProperty("db.default.url"))
  connectionPool.setInitialSize(ConfigProperties.getProperty("db.default.poolInitialSize").toInt)
  connectionPool.setMaxConnLifetimeMillis(150000)
  
  def withConnection[T]( ds : BasicDataSource )( op : (Connection) => T) : T = {
    var con : Connection = null;
  
    try {
      con = ds.getConnection();
      op(con);
    } finally { 
      closeConnection( con ); 
    }
  }

  def withStatement[T]( conn : Connection )( op : (Statement) => T) : T = {
    var statement : Statement = null;
  
    try {
      statement = conn.createStatement();
      op(statement);
    } finally { 
      closeStatement( statement ); 
    }
  }
  
  def withResultSet[T]( statement : Statement, query : String )( op : (ResultSet) => T) : T = {
    var rs : ResultSet = null;
  
    try {
      rs = statement.executeQuery(query);
      op(rs);
    } finally { 
      closeResultSet( rs ); 
    }
  }
  
  def closeConnection( conn : Connection ) {
    if ( conn != null ) {
      try { if ( conn != null ) conn.close() } catch {
        case e : Exception => e.printStackTrace() // better logging would be nice
      }
    }
  }
  
  def closeStatement( statement : Statement ) {
    if ( statement != null ) {
      try { if ( statement != null ) statement.close() } catch {
        case e : Exception => e.printStackTrace() // better logging would be nice
      }
    }
  }
  
  def closeResultSet( rs : ResultSet ) {
    if ( rs != null ) {
      try { if ( rs != null ) rs.close() } catch {
        case e : Exception => e.printStackTrace() // better logging would be nice
      }
    }
  }
}
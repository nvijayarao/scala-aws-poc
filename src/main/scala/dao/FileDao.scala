package dao

import scala.collection.mutable.ListBuffer

/**
 * @author nvijayar
 * 
 */
class FileDao {
  
  /*
   * Retrieving the list of file_id's from the DB
   */
  def getFilesToBeProcessed(query : String): ListBuffer[String] = {
    var fileList = new ListBuffer[String]()
    DB.withConnection(DB.connectionPool){
      conn => {
        DB.withStatement(conn){
          stmt => {
            DB.withResultSet(stmt, query){
              rs => { 
                while(rs.next()){
                  fileList += rs.getString("file_id") 
                }
              }
            }
          }
        }
      }
    }
    fileList
  }
  
  /*
   * Updating the file status
   */
  def updateFilesAsProcessed(fileId:String) = {
     
      DB.withConnection(DB.connectionPool){
      conn => {
        DB.withStatement(conn){
          stmt => {
            stmt.executeUpdate("INSERT INTO file_event VALUES ('"+fileId+"','TESTED','','')") 
          }
        }
      }
    }
  }
}
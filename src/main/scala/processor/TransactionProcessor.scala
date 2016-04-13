package processor

import dao.TransactionDao
import dao.FileDao
import scala.collection.mutable.ListBuffer
import helper.TransactionHelper

/**
 * @author nvijayar
 */
class TransactionProcessor {
  
  val selectSql = "Select f.file_id from file f inner join file_event fe1 on f.file_id = fe1.file_id and fe1.event_code = 'SAVED' "
  
  val transactionDao:TransactionDao = new TransactionDao
  val fileDao:FileDao = new FileDao
  val transactionHelper:TransactionHelper = new TransactionHelper
  
  def getTransactionFilesToBeProcessed() : ListBuffer[String] = {    
    fileDao.getFilesToBeProcessed(selectSql)
  }
  
  def updateTransactionFilesAsProcessed(fileId:String) = {    
    fileDao.updateFilesAsProcessed(fileId)
  }
  
  def processTransactionsForFileId(fileId : String){
    val transRows = transactionDao.getTransactionsByFileId(fileId)
    if(transRows.count() > 0){
      val transactions = transactionHelper.convertToJson(transRows, fileId)
      transactionDao.uploadToS3(transactions, fileId)
    }
  }
  
}
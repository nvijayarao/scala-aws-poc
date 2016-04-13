package service

import processor.TransactionProcessor

/**
 * @author nvijayar
 */
object PersistTransactionsToAws {

		val transactionProcessor:TransactionProcessor = new TransactionProcessor
		
		def main(args: Array[String]){
    
  		println(" ******* Persist to AWS main method start ********** : ")
      val fileIdList = transactionProcessor.getTransactionFilesToBeProcessed()
      
  		println(" ******* Process transactions for each file id - start **********"+fileIdList.length)
  		for(fileId <- fileIdList){
  			try{
          
          transactionProcessor.processTransactionsForFileId(fileId)
          
  				transactionProcessor.updateTransactionFilesAsProcessed(fileId)
  
  			}
  			catch {
  			  case t: Throwable => println // TODO: handle error
  			}
  		}      

	  }

}
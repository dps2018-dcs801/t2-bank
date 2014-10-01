package banksystem;

import java.io.Serializable;
import java.util.Date;

import database.Database;

public class Transaction  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String prefix = "L";

	public String id;

	static Database database = Database.getInstance();

	public TransactionData data = new TransactionData();

	public Transaction() {
	}

	public Transaction(String accountOwnerId, String accountId, String transactionAmount, String transactionType, String transactionId)  {
		data.accountOwnerId = accountOwnerId;
		data.accountId = accountId;
		data.transactionAmount = transactionAmount;
		data.transactionType = transactionType;
		data.transactionId = transactionId;
		data.transDate = new Date();  // today 		
	}
	public String recordTransaction() {
		// TODO:
		return ("String recordTransaction()");
	}
}

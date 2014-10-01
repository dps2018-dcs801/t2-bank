package banksystem;

import java.io.Serializable;

import database.Database;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String prefix = "L";

	public String id;

	static Database database = Database.getInstance();

	public TransactionData data = new TransactionData();

	public Transaction() {
	}

	public Transaction(String accountOwnerId, String accountId, String transactionAmount, String transactionType,
			String transactionId, String transactionDate) {
		data.accountOwnerId = accountOwnerId;
		data.accountId = accountId;
		data.transactionAmount = transactionAmount;
		data.transactionType = transactionType;
		data.transactionId = transactionId;
		data.transactionDate = transactionDate;
	}

	public String getTransactionDate() {
		return data.transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		data.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return data.transactionType;
	}

	public void setTransactionType(String transactionType) {
		data.transactionType = transactionType;
	}

	public String getTransactionId() {
		return data.transactionId;
	}

	public void setTransactionId(String transactionId) {
		data.transactionId = transactionId;
	}

	public String getTransactionAmount() {
		return data.transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		data.transactionAmount = transactionAmount;
	}

	public String getAccountOwnerId() {
		return data.accountOwnerId;
	}

	public void setAccountOwnerId(String accountOwnerId) {
		data.accountOwnerId = accountOwnerId;
	}

	public String getAccountId() {
		return data.accountId;
	}

	public void setAccountId(String accountId) {
		data.accountId = accountId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static String getNextId() {
		return (Database.getNextIdString(prefix));
	}

	public Integer getIdAsInt() {
		return (Database.getNextIdInt(prefix));
	}

	public void put() {
		setId(Database.getNextIdString(prefix));
		database.put(Database.getNextIdString(prefix), data);
	}

	public static Transaction get(String transactionId) {
		TransactionData transactionData = (TransactionData) Database.get(transactionId);
		if (transactionData == null) {
			return (null);
		}
		Transaction transaction = new Transaction();
		transaction.data = transactionData;
		transaction.setId(transactionId);
		return (transaction);
	}

	public String recordTransaction() {
		// TODO:
		return ("String recordTransaction()");
	}
}

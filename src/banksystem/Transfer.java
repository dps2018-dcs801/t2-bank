package banksystem;

import java.io.Serializable;

import database.Database;


public class Transfer implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String prefix = "T";

	public String id;

	static Database database = Database.getInstance();

	public TransferData data = new TransferData();

	public Transfer(String ownerId, String fromAccountId,  String toAccountId, String transferAmount) {
		this.data.ownerId = ownerId;
		this.data.fromAccountId = fromAccountId;
		this.data.toAccountId = toAccountId;
		this.data.transferAmount = transferAmount;
	}

	public Transfer() {
	}

	
	public String getOwnerId() {
		return data.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.data.ownerId = ownerId;
	}

	public String getFromAccountId() {
		return data.fromAccountId;
	}

	public String getToAccountId() {
		return data.toAccountId;
	}
	
	public void setFromAccountId(String fromAccountId) {
		data.fromAccountId = fromAccountId;
	}

	public void setToAccountId(String toAccountId) {
		data.toAccountId = toAccountId;
	}
	
	public String getTransferAmount() {
		return data.transferAmount;
	}

	public void setTransferAmount(String transferAmount) {
		data.transferAmount = transferAmount;
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

	public static Transfer get(String transferId) {
		TransferData transferData = (TransferData) Database.get(transferId);
		if (transferData == null) {
			return (null);
		}
		Transfer transfer = new Transfer();
		transfer.data = transferData;
		transfer.setId(transferId);
		return (transfer);
	}
	public String validateTransferAmount(String transferAmount) {
		Account newAccount = Account.get(this.data.fromAccountId);
	 	long centsTransferAmount = 0;
		long centsFromBalance = 0;
		centsTransferAmount = Utilities.toCents(transferAmount);
		centsFromBalance = Utilities.toCents(newAccount.getBalance());
		if (centsTransferAmount > centsFromBalance)
			return ("Transfer amount cannot be greater than the From Account balance");
		if (Utilities.isNegative(transferAmount))
			return ("Transfer amount cannot negative");
		if (!Utilities.isNumeric(transferAmount)) 
		 	return ("Transfer amount must be numeric");
		if (!Utilities.isMoney(transferAmount)) 
			return ("Transfer amount must be dollars and cents");
		
		
		return ("valid");
	}

	public String validate(String password) {
		AccountOwner newAccountOwner = AccountOwner.get(this.data.ownerId);
		System.out.println ( this.data.ownerId + " " +  newAccountOwner );
		if (AccountOwner.validateOwnerId(this.data.ownerId).equals("valid"))
			if (PasswordManager.authenticate(password, newAccountOwner.getPassword()) == "valid")
			{
				return ("valid");
			}
			else
				return (AccountOwner.authenticate(password, this.data.ownerId));
		else
			return (AccountOwner.validateOwnerId(this.data.ownerId));
	}

	public static String validate(String ownerId, String toAccountId, String fromAccountId, String password, String transferAmount) {
		if (Account.get(toAccountId) == null)
			return ("Transfer to account does not exist");
		if (Account.get(fromAccountId) == null)
			return ("Transfer from account does not exist");
		if (toAccountId == fromAccountId)
			return ("Transfer to and from accounts must be different");
		return ("valid");
	}

	public String transfer(String password) {
		AccountOwner newAccountOwner = AccountOwner.get(this.data.ownerId);
		System.out.println ( this.data.ownerId + " " +  newAccountOwner );
		if (AccountOwner.validateOwnerId(this.data.ownerId).equals("valid"))
			if (PasswordManager.authenticate(password, newAccountOwner.getPassword()) == "valid")
			{
				Account newAccount1 = Account.get(this.data.toAccountId);
			    Account newAccount2 = Account.get(this.data.fromAccountId);
			    newAccount1.add(this.data.transferAmount);
			    newAccount2.subtract(this.data.transferAmount);
			   	return ("valid");
			}
			else
				return (AccountOwner.authenticate(password, this.data.ownerId));
		else
			return (AccountOwner.validateOwnerId(this.data.ownerId));
	}
	
}

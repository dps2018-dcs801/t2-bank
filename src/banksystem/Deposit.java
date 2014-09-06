package banksystem;

import java.io.Serializable;

import database.Database;

public class Deposit implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String prefix = "D";

	public String id;

	static Database database = Database.getInstance();

	public DepositData data = new DepositData();

	public Deposit(String ownerId, String accountId, String depositAmount) {
		this.data.ownerId = ownerId;
		this.data.accountId = accountId;
		this.data.depositAmount = depositAmount;
	}

	public Deposit() {
	}

	
	public static String validateDepositAmount(String depositAmount) {
		//TODO: Return appropriate error messages like "Deposit amount cannot be space(s)"
		return ("String validateDepositAmount(String depositAmount) ");
		
	}



	public String updateBalance(String password) {
		//TODO: Update the Balance if the 
		return ("apply(String password)");

	}
	
	public String getOwnerId() {
		return data.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.data.ownerId = ownerId;
	}

	public String getAccountId() {
		return data.accountId;
	}

	public void setAccountId(String accountId) {
		this.data.accountId = accountId;
	}

	public String getDepositAmount() {
		return data.depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.data.depositAmount = depositAmount;
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

	public static Deposit get(String depositId) {
		DepositData depositData = (DepositData) Database.get(depositId);
		if (depositData == null) {
			return (null);
		}
		Deposit deposit = new Deposit();
		deposit.data = depositData;
		deposit.setId(depositId);
		return (deposit);
	}

	public String validate(String password) {
		return (validate(getOwnerId(), getAccountId(), password,
				getDepositAmount()));
	}

	public static String validate(String ownerId, String accountId,
			String password, String depositAmount) {
		return ( "validate(String ownerId, String accountId,String password, String depositAmount) ");

	}


}

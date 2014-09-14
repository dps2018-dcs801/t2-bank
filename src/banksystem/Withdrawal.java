package banksystem;

import database.Database;

import java.io.Serializable;

public class Withdrawal implements Serializable {

	private static final long serialVersionUID = 1L;

	static Database database = Database.getInstance();

	public static final String prefix = "W";

	String id;

	public WithdrawalData data = new WithdrawalData();

	public static Object validateWithdrawalId(String withdrawalId) {
		if (Database.exists(withdrawalId) ) {
			return ("valid");
		} else {
			return ("Invalid Withdrawal ID");
		}

}

	public void put() {
		setId(Database.getNextIdString(prefix));
		database.put(Database.getNextIdString(prefix), data);
		
	}

	public Object getId() {
		return (id);
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public static String validateWithdrawalAmount(String withdrawalAmount) {
		//Testing withdrawal amounts cannot exceed balance. USING STUB UNTIL DEPOSIT IS FINISHED.
		long balance = 1500;
		
		if (Utilities.toCents (withdrawalAmount) > balance) 
			return "invalid";
		else 
			return "valid";
	}
	
	
	}// end of Withdrawal class

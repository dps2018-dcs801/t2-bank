package banksystem;

import java.io.Serializable;

import database.Database;

public class Withdrawal extends WithdrawalInterface implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String prefix = "W";

	public String id;

	static Database database = Database.getInstance();

	public WithdrawalData data = new WithdrawalData();

	public Withdrawal(String ownerId, String accountId, String withdrawalAmount) {
		this.data.ownerId = ownerId;
		this.data.accountId = accountId;
		this.data.withdrawalAmount = withdrawalAmount;
	}

	public Withdrawal() { }
	

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

	public String getWithdrawalAmount() {
		return data.withdrawalAmount;
	}

	public void setWithdrawalAmount(String withdrawalAmount) {
		this.data.withdrawalAmount = withdrawalAmount;
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

	public static Withdrawal get(String withdrawalId) {
		WithdrawalData withdrawalData = (WithdrawalData) Database.get(withdrawalId);
		if (withdrawalData == null) {
			return (null);
		}
		Withdrawal withdrawal = new Withdrawal();
		withdrawal.data = withdrawalData;
		withdrawal.setId(withdrawalId);
		return (withdrawal);
	}
	
}

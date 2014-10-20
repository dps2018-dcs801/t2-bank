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

	
	public String validateDepositAmount(String depositAmount) {
		if (depositAmount.equals(""))
			return ("Deposit amount cannot be empty");
		
		if ((depositAmount.charAt(0)) == (' '))
			return ("Deposit amount cannot be space(s)");	
		
		if (Utilities.isNegative(depositAmount))
			return ("Deposit amount cannot be negative");
		
		long cents = 0;
		cents = Utilities.toCents(depositAmount);
		
		if (!Utilities.isNumeric(depositAmount)) {
		 	return ("Deposit amount must be numeric");
		}
		
		if ((cents) == 0){
			return ("Deposit amount cannot be zero");
		}
		
		if (!Utilities.isMoney(depositAmount)) {
			return ("Amount must be dollars and cents");
		}
		
		return ("valid");
			
	}



	public String updateBalance(String password) {
		AccountOwner newAccountOwner = AccountOwner.get(this.data.ownerId);
		System.out.println ( this.data.ownerId + " " +  newAccountOwner );
		if (AccountOwner.validateOwnerId(this.data.ownerId).equals("valid"))
			if (PasswordManager.authenticate(password, newAccountOwner.getPassword()) == "valid")
			{
				if (Account.validateAccountExists(this.data.accountId).equals("valid"))
				{
					
					Account updateAccount = Account.get(this.data.accountId);
					if (updateAccount.getOwnerId().equals(this.data.ownerId))
					{
						if (validateDepositAmount(this.data.depositAmount).equals("valid"))
						{
							updateAccount.add (this.data.depositAmount);
							return ("valid");
						}
						else
							return (validateDepositAmount(this.data.depositAmount));
					}
					else
						return ("Invalid Account ID");
				}
				else
					return (Account.validateAccountExists(this.data.accountId));
			}
			else
				return ("Invalid Password");
		else
			return (AccountOwner.validateOwnerId(this.data.ownerId));
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
		
		return (AccountOwner.validateOwnerId(ownerId));

	}


}

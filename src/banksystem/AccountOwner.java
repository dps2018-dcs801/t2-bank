package banksystem;

import java.io.Serializable;

import database.Database;

public class AccountOwner implements Serializable {

	private static final long serialVersionUID = 1L;

	static Database database = Database.getInstance();

	public static final String prefix = "O";

	String id;

	public  AccountOwnerData data = new AccountOwnerData();

	public String getId() {
		return (id);
	}

	public String  validate() {
		return ( validate(getName(),getPassword()) );
	}
	
	public static String validate(String name, String password) {
		String result = validateName(name);
		if (result.equals("valid")) {
			return (validatePassword(password));
		}
		return (result);
	}

	public String  validateName() {
		return ( validateName(getName()) );
	}

	public static String validateName(String name) {
		if (name.length() == 0) {
			return ("Name cannot be empty");
		} else if (name.length() > 30) {
			return ("Name must be less than 30 characters");
		} else if (name.length() < 2) {
			return ("Name must be greater than 1 character");
		} else if (name.contains("  ")) {
			return ("Name must not contain two consecutive spaces");			
		} else if (name.startsWith(" ")) {
			return ("Name must not start with a space");			
		} else if (name.endsWith(" ")) {
			return ("Name must not end with a space");	
		} else if (name.contains("9")) {
			return ("Name must not contain a number");	
		} else {
			return ("valid");
		}
	}

	public static String validateOwnerId(String ownerId) {
		if (Database.exists(ownerId) ) {
			return ("valid");
		} else {
			return ("Invalid Account Owner ID");
		}
	}

	public static String validatePassword(String password) {
		return (PasswordManager.validate(password));
	}

	public AccountOwner(String name, String password) {
		this.data.password = password;
		this.data.name = name;
	}

	public AccountOwner() {
	}
	
	public String authenticate(String password) {
		String result = PasswordManager.authenticate(password, getPassword());

		if (data.failedAuthenticationAttempts >= 2) {
			result = "Contact bank for password reset";
		} 
		else if (result == "Invalid Password") {
			data.failedAuthenticationAttempts++;
			if (data.failedAuthenticationAttempts == 2) {
				result = "Two failed login attempts - contact bank for password reset";
			}				
		}			
		
		return result;
	}

	public static String authenticate(String password, String id) {
		AccountOwner owner = get(id);
		return (owner.authenticate(password));
	}

	public void put() {
		setId(Database.getNextIdString(prefix));
		database.put(Database.getNextIdString(prefix), data);
	}

	public static AccountOwner get(String accountOwnerId) {
		AccountOwnerData accountOwnerData = (AccountOwnerData) Database
				.get(accountOwnerId);
		if (accountOwnerData == null) {
			return (null);
		}
		AccountOwner accountOwner = new AccountOwner();
		accountOwner.data = accountOwnerData;
		accountOwner.setId(accountOwnerId);
		return (accountOwner);
	}

	
	public static String getNextId() {
		return (Database.getNextIdString(prefix));
	}
	
	public static int getNextIdInt() {
		return (Database.getNextIdInt(prefix));
	}

	public  String getName() {
		return data.name;
	}

	public String getPassword() {
		return data.password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.data.name = name;
	}

	public void setPassword(String password) {
		this.data.password = password;
	}
	
		//locks current account
	public void lockAccount()
	{
		data.failedAuthenticationAttempts=2;
	}
	
	//unlocks current account based on all parameters passed being true / correct
	public String unlockAccount(
				String ownerName, 
				String accountOwnerId,
				String accountType,
				String accountId, 
				String accountBalance)
	{
		
		AccountData accountData = (AccountData) Database.get(accountId);
		
		if(ownerName.equalsIgnoreCase(data.name) &&
		   accountOwnerId.equalsIgnoreCase(accountData.ownerId) &&	
		   accountType.equalsIgnoreCase(accountData.accountType) &&
		   accountBalance.equalsIgnoreCase(accountData.balance)
		  )
		{
			data.failedAuthenticationAttempts=0;
			
			return "Unlock successful";
			
		}
		else
		{
			return "Information provided is not correct.  Game Over, thank you for playing! ;-)";
			
		}	
			
	}
	
}

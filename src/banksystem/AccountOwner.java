package banksystem;

import java.io.Serializable;

import database.Database;

public class AccountOwner implements Serializable {

	private static final long serialVersionUID = 1L;

	static Database database = Database.getInstance();

	public static final String prefix = "O";

	String id;

	public static AccountOwnerData data = new AccountOwnerData();

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
		} else {
			return ("valid");
		}
	}

	public String validateOwnerId(String ownerId) {
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

	public  static String authenticate(String password, AccountOwner owner) {
		return (PasswordManager.authenticate(password, owner.getPassword()));
	}

	public static String authenticate(String password, String id) {
		AccountOwner owner = get(id);
		return (authenticate(password, owner));
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

	public static String getName() {
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
}

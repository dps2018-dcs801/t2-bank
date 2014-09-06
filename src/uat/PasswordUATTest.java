package uat;

import org.concordion.integration.junit3.ConcordionTestCase;

import banksystem.AccountOwner;
import database.Database;



public class PasswordUATTest extends ConcordionTestCase {

	Database database = Database.getInstance();

	public void setUp() throws Exception {
		database.setFileName("test.dat");
		database.eraseFile();
		database.load();
	}

	public AccountOwner addOwner(String name, String password,
			String accountOwnerIdStart) {

		int startingIndex = Integer.parseInt(accountOwnerIdStart.substring(1));
		ownerManager.setStartingIndex(startingIndex + 1);
		AccountOwner owner = new AccountOwner(name, password);
		String result = ownerManager.validate(name, password);
		if (result.equals("valid")) {
			ownerManager.put(owner);
		} else {
			owner.setId(accountOwnerIdStart );
			owner.setStatus(result);
		}
		return (owner);
	}

}

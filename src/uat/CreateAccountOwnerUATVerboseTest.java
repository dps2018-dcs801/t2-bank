package uat;

import org.concordion.integration.junit3.ConcordionTestCase;

import banksystem.PasswordManager;
import database.Database;

public class CreateAccountOwnerUATVerboseTest extends ConcordionTestCase {

	Database database = Database.getInstance();

	public AccountOwnerWithStatus addOwner(String name, String password,
			String accountOwnerIdStart) {
		Database.setFileName("test.dat");
		database.eraseFile();
		database.load();
		int startingIndex = Integer.parseInt(accountOwnerIdStart.substring(1));
		String index = "O" + String.valueOf(startingIndex + 1);
		database.setIndex(index);
		AccountOwnerWithStatus owner = new AccountOwnerWithStatus(name, password);
		String result = PasswordManager.validate(password);
		owner.setStatus(result);
		owner.put();
		return (owner);
	}

	public AccountWithStatus addAccount(String ownerId, String password,
			String accountType, String balance, String accountIdStart)
			throws Exception {

		database.setIndex(accountIdStart);
		AccountWithStatus account = new AccountWithStatus(ownerId, accountType,
				balance);
		account.put();
		return (account);
	}

}

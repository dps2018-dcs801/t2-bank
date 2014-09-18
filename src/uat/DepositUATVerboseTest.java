package uat;

import org.concordion.integration.junit3.ConcordionTestCase;

import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.Deposit;
import banksystem.PasswordManager;
import database.Database;

public class DepositUATVerboseTest extends ConcordionTestCase {

	Database database = Database.getInstance();

	public void setUp() throws Exception {
		database.setFileName("test.dat");
		database.eraseFile();
		database.load();
	}

	public Composit makeDeposit(String password, String balance, String depositAmount, String givenOwnerId,
			String givenAccountId) {
		return (makeDeposit(password, password, balance, depositAmount, givenOwnerId, givenAccountId));
	}

	public Composit makeDeposit(String password, String tryPassword, String balance, String depositAmount,
			String givenOwnerId, String givenAccountId) {

		String passwordValidation = PasswordManager.validate(password);
		if (!passwordValidation.equals("valid")) {
			return (new Composit(passwordValidation));
		}
		String passwordResult = PasswordManager.authenticate(password, tryPassword);
		if (!passwordResult.equals("valid")) {
			return (new Composit(passwordResult));
		}

		database.setIndex(givenOwnerId);

		AccountOwner owner = new AccountOwner("firstName lastName", password);
		owner.put();

		database.setIndex(givenAccountId);
		Account account = new Account(givenOwnerId, "Checking", balance);
		account.put();

		Deposit deposit = new Deposit(givenOwnerId, givenAccountId, depositAmount);

		String result = deposit.updateBalance(tryPassword);

		Composit composit = new Composit();

		composit.ownerId = Composit.checkForNull(deposit.getOwnerId());
		composit.accountId = Composit.checkForNull(deposit.getAccountId());
		composit.accountType = Composit.checkForNull(account.getAccountType(), "Unknown");
		composit.balance = Composit.checkForNull(account.getBalance(), "0");

		composit.status = result;
		return (composit);

	}

	public String validate(String givenOwnerId, String givenAccountId, String password, String depositAmount) {
		database.setFileName("test.dat");
		database.eraseFile();
		database.load();

		String passwordValidation = PasswordManager.validate(password);
		if (!passwordValidation.equals("valid")) {
			return (passwordValidation);
		}

		Deposit deposit = new Deposit(givenOwnerId, givenAccountId, depositAmount);

		return (deposit.validate(password));

	}
}

package uat;

import org.concordion.integration.junit3.ConcordionTestCase;

import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.Withdrawal;
import banksystem.PasswordManager;
import database.Database;

public class WithdrawalUATVerboseTest extends ConcordionTestCase {

	Database database = Database.getInstance();

	public void setUp() throws Exception {
		Database.setFileName("test.dat");
		database.eraseFile();
		database.load();
	}

	public WithdrawalWithStatus makeWithdrawal(String password, String balance, String withdrawalAmount, String givenOwnerId,
			String givenAccountId) {
		return (makeWithdrawal(password, password, balance, withdrawalAmount, givenOwnerId, givenAccountId));
	}

	public WithdrawalWithStatus makeWithdrawal(String password, String tryPassword, String balance, String withdrawalAmount,
			String givenOwnerId, String givenAccountId) {

		String passwordValidation = PasswordManager.validate(password);
		if (!passwordValidation.equals("valid")) {
			return (new WithdrawalWithStatus(passwordValidation));
		}
		String passwordResult = PasswordManager.authenticate(password, tryPassword);
		if (!passwordResult.equals("valid")) {
			return (new WithdrawalWithStatus(passwordResult));
		}

		database.setIndex(givenOwnerId);

		AccountOwner owner = new AccountOwner("firstName lastName", password);
		owner.put();

		database.setIndex(givenAccountId);
		Account account = new Account(givenOwnerId, "Checking", balance);
		account.put();

		WithdrawalWithStatus withdrawal = new WithdrawalWithStatus(givenOwnerId, givenAccountId, withdrawalAmount);

		String result = withdrawal.updateBalance(tryPassword);
		withdrawal.setStatus(result);

		return (withdrawal);

	}

	public String validate(String givenOwnerId, String givenAccountId, String password, String withdrawalAmount) {
		Database.setFileName("test.dat");
		database.eraseFile();
		database.load();

		String passwordValidation = PasswordManager.validate(password);
		if (!passwordValidation.equals("valid")) {
			return (passwordValidation);
		}

		Withdrawal withdrawal = new Withdrawal(givenOwnerId, givenAccountId, withdrawalAmount);

		return (withdrawal.validate(password));

	}
}

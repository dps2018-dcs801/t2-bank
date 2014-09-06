package uat;

import org.concordion.integration.junit3.ConcordionTestCase;

import banksystem.AccountOwner;
import banksystem.Deposit;
import banksystem.PasswordManager;
import database.Database;




public class DepositUATVerboseTest extends ConcordionTestCase {

	Database database = Database.getInstance();


	public void setUp() throws Exception {
		
		Database.setFileName("test.dat");
		database.eraseFile();
		database.load();
	}

	public Composit makeDeposit(String password, String balance,
			String depositAmount, String givenOwnerId, String givenAccountId) {
		return (makeDeposit(password, password, balance, depositAmount,
				givenOwnerId, givenAccountId));
	}

	public Composit makeDeposit(String password, String tryPassword,
			String balance, String depositAmount, String givenOwnerId,
			String givenAccountId) {
		String passwordValidation = PasswordManager.validate(password);
		if ( !passwordValidation.equals("valid") ) {
			return ( new Composit(passwordValidation));
		}
		String passwordResult = PasswordManager.authenticate(password, tryPassword);
		if ( !passwordResult.equals("valid") ) {
			return ( new Composit(passwordResult));
		}
		int startingIndex = Integer.parseInt(givenOwnerId.substring(1));
		
		ownerManager.setStartingIndex(startingIndex);
		
		AccountOwner owner = new AccountOwner("firstName lastName", password);

		ownerManager.put(owner);

		startingIndex = Integer.parseInt(givenAccountId.substring(1));
		accountManager.setStartingIndex(startingIndex);
		AccountWithStatus account = new AccountWithStatus(givenOwnerId, "Checking", balance);
		accountManager.put(account);
		

		Deposit deposit = null;
		try {
			deposit = DepositManager.getInstance().apply(givenOwnerId, givenAccountId, password, depositAmount);
		} catch (Exception e) {
			deposit.setStatus("Transaction Failed");
		}
		String result = deposit.getStatus();

		Composit composit = new Composit();

		composit.ownerId = Composit.checkForNull(deposit.getOwnerId());
		composit.accountId = Composit.checkForNull(deposit.getAccountId());
		composit.accountType = Composit.checkForNull(account.getAccountType(),
				"Unknown");
		composit.balance = Composit.checkForNull(account.getBalance(),
				"0");

		composit.status = deposit.getStatus();
		return (composit);

	}

	public Deposit validate(String givenOwnerId, String givenAccountId, String password, String depositAmount) {
		database.setFileName("test.dat");
		database.eraseFile();
		database.load();
		Deposit deposit = null;
		try {
			deposit = DepositManager.getInstance().apply(givenOwnerId, givenAccountId, password, depositAmount);
		} catch (Exception e) {
			deposit.setStatus("Transaction Failed");
		}
		return ( deposit);


	}
}

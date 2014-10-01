package uat;

import org.concordion.integration.junit3.ConcordionTestCase;

import database.Database;
import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.Transfer;


public class TransferUATVerboseTest extends ConcordionTestCase {

	Database database = Database.getInstance();


	public void setUp() throws Exception {
		database.setFileName("test.dat");
		database.eraseFile();
		database.load();
	}

	public TransferUAT makeTransfer(String password, String fromBalance, String toBalance, String transferAmount, String givenOwnerId,
			String fromAccountId, String toAccountId) {
		return (makeTransfer(password, password, fromBalance, toBalance, transferAmount, givenOwnerId, fromAccountId, toAccountId));
	}

	public AccountOwner makeOwner(String givenOwnerId, String name, String password) {
		database.setIndex(givenOwnerId);

		AccountOwner accountOwner = new AccountOwner(name, password);
		accountOwner.put();
		return ( accountOwner );
	}

	public Account makeAccount(String ownerId, String requestedId, String accountType, String balance) {
		database.setIndex(requestedId);
		Account account = new Account(ownerId, accountType, balance);
		account.put();
		return (account);
	}

	public TransferUAT makeTransfer(String password, String tryPassword, String fromBalance, String toBalance, String transferAmount,
			String givenOwnerId, String fromAccountId, String toAccountId) {

		Transfer transfer = new Transfer(givenOwnerId, fromAccountId, toAccountId, transferAmount);
		String result = transfer.validate(tryPassword);
		TransferUAT transferUAT = new TransferUAT();
		if ( result.equals("valid") ) {

		
			transferUAT.fromOwnerId = givenOwnerId;
			transferUAT.toAccountId = transfer.getToAccountId();
			transferUAT.toAccountBalance = transfer.getTransferAmount();
			transferUAT.toOwnerId = givenOwnerId;
			transferUAT.setStatus(result);
		}
		return (transferUAT);

	}

	public TransferUAT validate(String password, String tryPassword, String fromBalance, String toBalance, String transferAmount, String givenOwnerId,
			String fromAccountId, String toAccountId) {
		Transfer transfer = new Transfer(givenOwnerId, fromAccountId, toAccountId, transferAmount);
		String result = transfer.validate(tryPassword);
		return ( new TransferUAT(result));

	}

	public void clearDatabase() {
		database.resetInMemory();
	}
}
package uat;

import org.concordion.integration.junit3.ConcordionTestCase;

import database.Database;
import banksystem.Account;
import banksystem.AccountData;
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
		System.out.println ("AccountOwner:" +  accountOwner );

		return ( accountOwner );
	}

	public Account makeAccount(String ownerId, String requestedId, String accountType, String balance) {
		database.setIndex(requestedId);
		Account account = new Account(ownerId, accountType, balance);
		account.put();
		System.out.println ("Account:" +  account );
		return (account);
	}

	public TransferUAT makeTransfer(String password, String tryPassword, String fromBalance, String toBalance, String transferAmount,
			String givenOwnerId, String fromAccountId, String toAccountId) {
		database.setIndex(givenOwnerId);		

		Transfer transfer = new Transfer(givenOwnerId, fromAccountId, toAccountId, transferAmount);
		System.out.println ( "givenOwnerId" + givenOwnerId );
		System.out.println ( "fromAccountId" +  fromAccountId );
		System.out.println ( "toAccountId" +  toAccountId );
		System.out.println ( "transferAmount" +  transferAmount );
		
		String result = transfer.transfer(tryPassword);
		System.out.println ( "Result of transfer:" + result );
		TransferUAT transferUAT = new TransferUAT();
		
		if ( result.equals("valid") ) {
			result = transfer.transfer(tryPassword);
			System.out.println ( "Result of transfer:" + transfer );
			transfer.put();
			transferUAT.fromOwnerId = givenOwnerId;
			transferUAT.toAccountId = toAccountId;
			transferUAT.fromAccountId = fromAccountId;
			transferUAT.toOwnerId = givenOwnerId;

			AccountData toAccountData = (AccountData)Database.get(toAccountId);
			transferUAT.toAccountBalance = toAccountData.balance;

			AccountData fromAccountData = (AccountData)Database.get(fromAccountId);
			transferUAT.fromAccountBalance = fromAccountData.balance;

			transferUAT.transferAmount = transferAmount;
		}
		transferUAT.setStatus(result);
		System.out.println ( transferUAT );
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
package junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import banksystem.Account;
import banksystem.AccountData;
import banksystem.AccountOwner;
import banksystem.AccountOwnerData;
import banksystem.Deposit;
import banksystem.Query;
import database.Database;

public class QueryTest {
	Database database = Database.getInstance();

	@Before
	public void setUp() throws Exception {
		Database.setFileName("test.dat");
		database.eraseFile();
		database.load();
	}

	@Test
	public void sortDataBase() {
		AccountOwner owner = new AccountOwner("First Last", "P$2222");
		owner.put(); 		

		Account account = new Account(owner.getId(), "Checking", "50.00");
		account.put (); 

		Deposit deposit = new Deposit(owner.getId(), account.getId(), "100");
		deposit.put();

		assertEquals("valid", deposit.updateBalance(owner.getPassword()));
		assertEquals("150.00",account.getBalance());

		System.out.println("All Owners:");
		for (Entry<String, AccountOwnerData> entry : Query.getAllOwners().entrySet()) {
			Object obj = entry.getKey();
			System.out.println(obj + " = " + entry.getValue());
		}

		System.out.println("All Accounts:");
		for (Entry<String, AccountData> entry : Query.getAllAccounts().entrySet()) {
			Object obj = entry.getKey();
			System.out.println(obj + " = " + entry.getValue());
		}
		System.out.println("All Transactions:");
		for (Entry<String, Object> entry : Query.getAllTransactions().entrySet()) {
			Object obj = entry.getKey();
			System.out.println(obj + " = " + entry.getValue());
		}
		System.out.println("All Transactions by Account:");
		for (Entry<String, ArrayList<Object>> entry : Query.getAllTransactionsByAccount().entrySet()) {
			Object obj = entry.getKey();
			System.out.println(obj + " = " + entry.getValue());
		}
	}

}

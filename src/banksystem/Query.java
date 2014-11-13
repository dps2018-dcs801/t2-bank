package banksystem;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import database.Database;

public class Query {

	static Database database = Database.getInstance();

	static public LinkedHashMap<String, AccountOwnerData> getAllOwners() {
		LinkedHashMap<String, AccountOwnerData> list = new LinkedHashMap<String, AccountOwnerData>();
		for (Entry<String, Object> entry : database.data.entrySet()) {
			String key = entry.getKey();
			Object obj = entry.getValue();
			if (obj instanceof AccountOwnerData) {
				AccountOwnerData recordOwner = (AccountOwnerData) obj;
				list.put(key, recordOwner);
			}
		}
		return (list);
	}

	static public LinkedHashMap<String, AccountData> getAllAccounts() {
		LinkedHashMap<String, AccountData> list = new LinkedHashMap<String, AccountData>();
		for (Entry<String, Object> entry : database.data.entrySet()) {
			Object obj = entry.getValue();
			String key = entry.getKey();
			if (obj instanceof AccountData) {
				AccountData recordAccount = (AccountData) obj;
				list.put(key, recordAccount);
			}
		}
		return (list);
	}

	static public LinkedHashMap<String, ArrayList<Object>> getAllTransactionsByAccount() {

		LinkedHashMap<String, ArrayList<Object>> list = new LinkedHashMap<String, ArrayList<Object>>();

		for (Entry<String, Object> entry : database.data.entrySet()) {
			Object obj = entry.getValue();
			if ((obj instanceof String) || (obj instanceof AccountOwnerData) || (obj instanceof AccountData)) {
				continue;
			}

			if (obj instanceof DepositData) {
				DepositData deposit = (DepositData) obj;
				ArrayList<Object> members = (list.get(deposit.accountId) == null ? new ArrayList<Object>() : list.get(deposit.accountId));
				members.add(deposit);
				list.put(deposit.accountId, members);
			}

			if (obj instanceof WithdrawalData) {
				WithdrawalData withdraw = (WithdrawalData) obj;
				ArrayList<Object> members = (list.get(withdraw.accountId) == null ? new ArrayList<Object>() : list.get(withdraw.accountId));
				members.add(withdraw);
				list.put(withdraw.accountId, members);
			}

			if (obj instanceof TransferData) {
				TransferData transfer = (TransferData) obj;

				ArrayList<Object> members = (list.get(transfer.fromAccountId) == null ? new ArrayList<Object>() : list
						.get(transfer.fromAccountId));
				members.add(transfer);
				list.put(transfer.fromAccountId, members);

				members = (list.get(transfer.toAccountId) == null ? new ArrayList<Object>() : list.get(transfer.toAccountId));
				members.add(transfer);
				list.put(transfer.toAccountId, members);
			}
		}
		return (list);
	}

	static public LinkedHashMap<String, Object> getAllTransactions() {

		LinkedHashMap<String, Object> list = new LinkedHashMap<String, Object>();

		for (Entry<String, Object> entry : database.data.entrySet()) {
			Object obj = entry.getValue();
			String key = entry.getKey();
			if ((obj instanceof String) || (obj instanceof AccountOwnerData) || (obj instanceof AccountData)) {
				continue;
			}
			list.put(key, obj);

		}
		return (list);
	}

	public void getAll() {

		System.out.println("All Owners:");
		for (Entry<String, AccountOwnerData> entry : getAllOwners().entrySet()) {
			Object obj = entry.getKey();
			System.out.println(obj + " = " + entry.getValue());
		}

		System.out.println("All Accounts:");
		for (Entry<String, AccountData> entry : getAllAccounts().entrySet()) {
			Object obj = entry.getKey();
			System.out.println(obj + " = " + entry.getValue());
		}
		System.out.println("All Transactions by Account:");
		for (Entry<String, ArrayList<Object>> entry : getAllTransactionsByAccount().entrySet()) {
			Object obj = entry.getKey();
			System.out.println(obj + " = " + entry.getValue());
		}

	}
}

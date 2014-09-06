package uat;

import banksystem.Account;


public class AccountWithStatus extends Account {
	public AccountWithStatus(String id, String accountType, String balance) {
		super(id,accountType,balance);
	}

	public AccountWithStatus() {
	}

	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

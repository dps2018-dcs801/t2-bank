package uat;

import banksystem.AccountOwner;


public class AccountOwnerWithStatus extends AccountOwner {
	public AccountOwnerWithStatus(String name, String password) {
		super(name,password);
	}

	public AccountOwnerWithStatus() {
	}

	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

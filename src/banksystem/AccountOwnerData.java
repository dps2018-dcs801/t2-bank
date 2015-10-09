package banksystem;

import java.io.Serializable;

public class AccountOwnerData implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String password;
	public int failedAuthenticationAttempts;

	public AccountOwnerData() {
	}
	
	@Override
	public String toString() {
		return "AccountOwnerData [name=" + name + ", password=" + password + "]";
	}
}
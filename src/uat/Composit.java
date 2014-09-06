package uat;

public class Composit {

	public String ownerId;
	public String accountId;
	public String balance;
	public String accountType;
	public String status;

	public Composit() {
	}

	public Composit(String status) {
		this.status = status;
	}

	public static String checkForNull(String stringToCheck) {
		return ((stringToCheck != null ? stringToCheck : null));
	}

	public static String checkForNull(String stringToCheck, String replaceValue) {
		return ((stringToCheck != null ? stringToCheck : replaceValue));
	}

	@Override
	public String toString() {
		return "Composit [ownerId=" + ownerId + ", accountId=" + accountId
				+ ", balance=" + balance + ", accountType=" + accountType
				+ ", status=" + status + "]";
	}

}

package uat;

public class TransferUAT {


	public String fromOwnerId;
	public String toOwnerId;
	public String fromAccountBalance;
	public String toAccountBalance;
	public String toAccountType;
	public String fromAccountType;
	public String status;
	public String fromAccountId;
	public String toAccountId;
	public String transferAmount;

	public TransferUAT() {
	}

	public TransferUAT(String status) {
		this.status = status;
	}
	

	public String getFromOwnerId() {
		return fromOwnerId;
	}

	public void setFromOwnerId(String fromOwnerId) {
		this.fromOwnerId = fromOwnerId;
	}

	public String getToOwnerId() {
		return toOwnerId;
	}

	public void setToOwnerId(String toOwnerId) {
		this.toOwnerId = toOwnerId;
	}

	public String getFromAccountBalance() {
		return fromAccountBalance;
	}

	public void setFromAccountBalance(String fromAccountBalance) {
		this.fromAccountBalance = fromAccountBalance;
	}

	public String getToAccountBalance() {
		return toAccountBalance;
	}

	public void setToAccountBalance(String toAccountBalance) {
		this.toAccountBalance = toAccountBalance;
	}

	public String getToAccountType() {
		return toAccountType;
	}

	public void setToAccountType(String toAccountType) {
		this.toAccountType = toAccountType;
	}

	public String getFromAccountType() {
		return fromAccountType;
	}

	public void setFromAccountType(String fromAccountType) {
		this.fromAccountType = fromAccountType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public String getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(String transferAmount) {
		this.transferAmount = transferAmount;
	}
	
	
	@Override
	public String toString() {
		return "TransferUAT [fromOwnerId=" + fromOwnerId + ", toOwnerId=" + toOwnerId + ", fromAccountBalance=" + fromAccountBalance
				+ ", toAccountBalance=" + toAccountBalance + ", toAccountType=" + toAccountType + ", fromAccountType=" + fromAccountType
				+ ", status=" + status + ", fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId + ", transferAmount=" + transferAmount
				+ "]";
	}
}
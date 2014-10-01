package banksystem;

import java.io.Serializable;

import database.Database;

public class Transfer implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String prefix = "T";

	public String id;

	static Database database = Database.getInstance();

	public TransferData data = new TransferData();

	public Transfer(String ownerId, String fromAccountId,  String toAccountId, String transferAmount) {
		this.data.ownerId = ownerId;
		this.data.fromAccountId = fromAccountId;
		this.data.toAccountId = toAccountId;
		this.data.transferAmount = transferAmount;
	}

	public Transfer() {
	}

	
	public String getOwnerId() {
		return data.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.data.ownerId = ownerId;
	}

	public String getFromAccountId() {
		return data.fromAccountId;
	}

	public String getToAccountId() {
		return data.toAccountId;
	}
	
	public void setFromAccountId(String fromAccountId) {
		data.fromAccountId = fromAccountId;
	}

	public void setToAccountId(String toAccountId) {
		data.toAccountId = toAccountId;
	}
	
	public String getTransferAmount() {
		return data.transferAmount;
	}

	public void setTransferAmount(String transferAmount) {
		data.transferAmount = transferAmount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static String getNextId() {
		return (Database.getNextIdString(prefix));
	}

	public Integer getIdAsInt() {
		return (Database.getNextIdInt(prefix));
	}

	public void put() {
		setId(Database.getNextIdString(prefix));
		database.put(Database.getNextIdString(prefix), data);
	}

	public static Transfer get(String transferId) {
		TransferData transferData = (TransferData) Database.get(transferId);
		if (transferData == null) {
			return (null);
		}
		Transfer transfer = new Transfer();
		transfer.data = transferData;
		transfer.setId(transferId);
		return (transfer);
	}
	public String validateTransferAmount(String transferAmount) {
		// TODO:
		return ("String validateTransferAmount(String transferAmount) {");
	}

	public String validate(String password) {
		// TODO:
		return ("String validate(String password)");
	}

	public static String validate(String ownerId, String toAccountId, String fromAccountId, String password, String transferAmount) {
		// TODO:
		return ("String validate(String ownerId, String toAccountId, String fromAccountId, String password, String transferAmount) ");
	}

	public String transfer(String password) {
		// TODO:
		return ("String transfer(String password)");
	}
}

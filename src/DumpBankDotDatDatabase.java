import database.Database;

public class DumpBankDotDatDatabase {

	public static void main(String[] args) {
		Database database = Database.getInstance();
		database.load();
		Database.dump("bank.dat");
	}

}

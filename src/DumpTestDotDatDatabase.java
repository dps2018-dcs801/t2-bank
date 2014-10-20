import database.Database;

public class DumpTestDotDatDatabase {

	public static void main(String[] args) {
		Database database = Database.getInstance();
		Database.setFileName("test.dat");
		database.load();
		Database.dump("test.dat");
	}

}

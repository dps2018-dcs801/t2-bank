package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Database {

	private static int DEFAULT_STARTING_ID = 1001;
	private static Database instance = null;

	public static LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();

	public static LinkedHashMap<String, Object> getAll() {

		return (data);
	}

	public static synchronized Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return (instance);
	}

	/**
	 * The default file name to save data to. A transaction log of all changes
	 * to the file is kept in a file called bank.dat.log. When the system starts
	 * up that file is used. A proper shutdown of the app should copy the log
	 * file over to the original file.
	 */
	private static String fileName = "bank.dat";

	public static void setFileName(String fileName) {
		Database.fileName = fileName;
	}

	public static Object get(String key) {
		//System.out.println("get key:" + key);
		return (data.get(key));
	}

	public static Object get(String key, Object object) {

		@SuppressWarnings("unchecked")
		HashMap<String, Object> objectFromDatabase = (HashMap<String, Object>) data
				.get(key);

		return (objectFromDatabase);
	}

	public void setIndex(String index) {
		data.put(index.substring(0, 1), index);
	}

	public static Integer getNextIdInteger(String prefix) {
		return (new Integer(getNextIdInt(prefix)));
	}

	public static String formatIndex(String prefix, Integer index) {
		return (String.format("%s%04d", prefix, index.intValue()));
	}

	public static String formatIndex(String prefix, int index) {
		return (formatIndex(prefix, new Integer(index)));
	}

	public static String getNextIdString(String prefix) {
		return (String.format("%s%04d", prefix, getNextIdInt(prefix)));
	}

	public static int getNextIdInt(String prefix) {
		String index = (String) get(prefix);
		if (index == null) {
			return (DEFAULT_STARTING_ID);
		} else {
			return (Integer.valueOf(index.substring(1)));
		}
	}

	protected void updateIndex(String key) {
		String prefix = key.substring(0, 1);
		//System.out.println("prefix:" + prefix);
		int index = Integer.parseInt(key.substring(1));
		String nextIndex = formatIndex(prefix, ++index);
		setIndex(nextIndex);
	}

	public void put(String index, Object object) {

		data.put(index, object);
		//System.out.println("Update Index:" + index);
		updateIndex(index);

		checkPoint();
		dump("State of database:");
	}

	public static boolean exists(String id) {
		Object object = get(id);
		//System.out.println("look up:" + id + " result is:" + object);
		if (object == null) {
			return (false);
		} else {
			return (true);
		}
	}

	/**
	 * Load.
	 */
	public void load() {
		load(fileName);
	}

	@SuppressWarnings("unchecked")
	public void load(String fileName) {
		ObjectInputStream objectInputStream = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			objectInputStream = new ObjectInputStream(fileInputStream);

			data = (LinkedHashMap<String, Object>) objectInputStream
					.readObject();

			fileInputStream.close();
			objectInputStream.close();
		} catch (FileNotFoundException fileNotFoundException) {
			// System.out.println(fileNotFoundException.getMessage());
			resetInMemory();
		} catch (IOException iOException) {
			iOException.printStackTrace();
			System.exit(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// dump("load");
	}

	public synchronized void store() {
		store(fileName);
	}

	public synchronized void eraseFile() {
		File file = new File(fileName);
		file.delete();
	}

	public synchronized void resetInMemory() {
		data = new LinkedHashMap<String, Object>();
	}

	public static synchronized void checkPoint() {
		store(fileName);
	}

	public static synchronized void store(String fileName) {
		// dump("Store");

		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new FileOutputStream(fileName));

			objectOutputStream.writeObject(data);

			objectOutputStream.close();
		} catch (IOException e) {
			System.out.println("Failed writing database. " + e);
		}
	}

	public static void dump() {
		dump("");
	}

	public static void dump(String title) {
		System.out.println("--------------" + title + "--------------");
		System.out.println("File:" + fileName);
		System.out.println("Entry Set:" + data.entrySet());

		for (Map.Entry<String, Object> entry : data.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(key + " = " + value);
		}
	}

	public static void dump(String file, String title) {
		setFileName(file);
		dump(title);
	}

	public static void main(String[] args) {

		Database database = Database.getInstance();
		database.load();
		Database.dump("Dump from Database.");

	}

}

package banksystem;

import database.Database;

public class PasswordManager {
	
	Database database = Database.getInstance();

	public static String authenticate(String password, String expectedPassword) {
		if (password.equals(expectedPassword)) {
			return ("valid");
		} else {
			return ("Invalid Password");
		}
	}
	


	public static String validate(String password) {
		
		if (password.length() < 2) {
			return ("Password minimum 2 characters");
		}
		if (password.length() > 6) {
			return ("Password maximum 6 characters");
		}
		if (password.contains(" ")) {
			return ("Password cannot contain space(s)");
		}
		int numberNonAlphaNumerics = 0;
		int numberAlphaNumerics = 0;
		for (Character ch : password.toCharArray()) {
			if (!Character.isLetterOrDigit(ch)) {
				numberNonAlphaNumerics++;
			}
			if (Character.isLetterOrDigit(ch)) {
				numberAlphaNumerics++;
			}
		}
		if (numberNonAlphaNumerics == 0) {
			return ("Password must contain at least 1 non-alphanumeric character");

		} else if (numberAlphaNumerics == 0) {

			return ("Password must contain at least 1 alphanumeric character");

		} else {
			return ("valid");
		}
	}
}

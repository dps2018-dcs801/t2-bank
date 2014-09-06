package banksystem;

import java.text.DecimalFormat;

public class Utilities {

	public static String VALID = "valid";
	public static String INVALID = "invalid";

	public static boolean isMoney(String money) {
		money = money.trim();

		if (money.startsWith("$")) {
			return ( false );
		}

		if (money.length() > 0
				&& money.trim().matches("^(\\d*(\\.\\d\\d?)?|\\d+)$")) {
			return (true);
		} else {
			return (false);
		}
	}
	

	
	public static boolean isNumeric(String str) {
		// return str.matches("-?\\d+(\\.\\d+)?"); //match a number with
		// optional '-' and decimal.
		for (Character ch : str.toCharArray()) {
			if (Character.isDigit(ch)) {
				continue;
			}
			if (ch == '.') {
				continue;
			}
			return (false);
		}

		return (true);
	}

	public static boolean isNegative(String str) {
		for (Character ch : str.toCharArray()) {

			if (ch == '-') {
				return (true);
			}

		}
		return (false);

	}

	public static long toCents(String money) {
		if (!isMoney(money)) {
			return (-1);
		}
		money = money.trim().replaceAll("[,\\$]", "");

		long dollars = 0;
		long cents = 0;
		int point = money.indexOf('.');
		if (point != -1) {
			String dollarsAsString = money.substring(0, point);
			dollars = (dollarsAsString.equals("") ? 0 : Long.parseLong(money
					.substring(0, point)));
			cents = Long.parseLong(money.substring(point + 1));
		} else {
			dollars = Integer.parseInt(money);
		}

		return (dollars * 100 + cents);
	}

	public static String toMoney(long amount) {
		DecimalFormat df = new DecimalFormat("00");
		long bigPart = amount / 100;
		long smallPart = amount % 100;
		String result = String.format("%d.%s", bigPart, df.format(smallPart));
		return result;
	}

}

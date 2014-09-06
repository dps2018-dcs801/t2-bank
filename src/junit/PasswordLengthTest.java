package junit;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import banksystem.PasswordManager;

public class PasswordLengthTest {

	@Test
	public void checkLessMinLength() {
		String password = "A";
		Assert.assertEquals("Password minimum 2 characters", PasswordManager.validate(password));
	}
	
	@Test
	public void checkZeroLength() {
		String password = "";
		Assert.assertEquals("Password minimum 2 characters", PasswordManager.validate(password));
	}
	
	@Test
	public void checkMinLength() {
		String password = "A$";
		Assert.assertEquals("valid", PasswordManager.validate(password));
	}
	
	@Test
	public void checkGreaterMaxLength() {
		String password = "1234567";
		Assert.assertEquals("Password maximum 6 characters", PasswordManager.validate(password));
	}
	@Test
	public void checkMaxLength() {
		String password = "12345$";
		Assert.assertEquals("valid", PasswordManager.validate(password));
	}
	@Test
	public void checkValidLength() {
		String password = "123$";
		Assert.assertEquals("valid", PasswordManager.validate(password));
	}
	
	@Test
	public void checkOneSpace() {
		String password = " $";
		Assert.assertEquals("Password cannot contain space(s)", PasswordManager.validate(password));
	}
	
	@Test
	public void checkSpaceInPassword() {
		String password = "1 $";
		Assert.assertEquals("Password cannot contain space(s)", PasswordManager.validate(password));
	}

}

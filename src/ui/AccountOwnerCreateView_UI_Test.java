package ui;

import static org.junit.Assert.assertNotNull;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;

import banksystem.AccountOwnerCreateView;
import database.Database;

public class AccountOwnerCreateView_UI_Test {

	private JFrame jFrame;
	
	
	Database database = Database.getInstance();

	
	@Before
	public void setUp() throws Exception {

		jFrame = new JFrame();
		AccountOwnerCreateView newContentPane = new AccountOwnerCreateView();
		newContentPane.setPreferredSize(new Dimension(600, 400));
		newContentPane.setOpaque(true);
		jFrame.setContentPane(newContentPane);
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);
		
		database.setFileName("test.dat");
		database.eraseFile();
		database.load();
	}

	@Test
	public void testFindAccountOwnerNameTextField() {

		jFrame.setVisible(true);

		JTextField accountOwnerNameTextField = JTextFieldOperator
				.findJTextField(jFrame, new NameComponentChooser(
						"accountOwnerNameTextField"));

		assertNotNull(accountOwnerNameTextField);

	}

	@Test
	public void testFindPasswordTextField() {

		jFrame.setVisible(true);

		JTextField passwordTextField = JTextFieldOperator.findJTextField(
				jFrame, new NameComponentChooser("passwordTextField"));

		assertNotNull(passwordTextField);

	}

	@Test
	public void testFindOwnerIdLabel() {

		jFrame.setVisible(true);

		JLabel ownerIdLabel = JLabelOperator.findJLabel(jFrame,
				new NameComponentChooser("ownerIdLabel"));

		assertNotNull(ownerIdLabel);
	}

	@Test
	public void testFindErrorMessageLabel() {

		jFrame.setVisible(true);

		JLabel ownerIdLabel = JLabelOperator.findJLabel(jFrame,
				new NameComponentChooser("ownerIdLabel"));
		System.out.println ( ownerIdLabel.getText() );
		Assert.assertEquals("O1001", ownerIdLabel.getText() );
	}

}

package tests;

import static org.junit.Assert.*;
import model.Account;
import model.AccountCollection;

import org.junit.Test;

/**
 * Test the AccountCollection class.
 * 
 * @author 	George Barnum
 * 		 	Lu Ye
 *
 */
public class AccountCollectionTest {

	@Test
	public void test() {
		AccountCollection one = new AccountCollection();
		String user1 = "u1";
		String user2 = "notmatch";
		String pass2 = "notmatchpass";
		String pass1 = "pass";
		Account testAcct1 = new Account(user1, pass1);
		one.add(testAcct1);
		assertEquals(testAcct1, one.login(user1, pass1));
		assertEquals(null, one.login(user2, pass1));
		assertEquals(null, one.login(user1, pass2));

	}

}

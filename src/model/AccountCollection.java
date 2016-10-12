package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to store all the available Accounts and allow the retrieval of an
 * Account given it's username and password.
 * 
 * @author George Barnum Lu Ye
 *
 */
public class AccountCollection implements Serializable {

	private List<Account> accounts;

	public AccountCollection() {

		accounts = new ArrayList<Account>();
	}

	public void add(Account account) {
		accounts.add(account);

	}

	public Account login(String username, String password) {
		for (Account account : accounts) {
			if (account.canLogin(username, password)) {
				return account;
			}
		}
		return null;
	}

}

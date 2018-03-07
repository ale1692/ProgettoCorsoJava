package it.ariadne.Controller;

import it.ariadne.dao.Dao;
import it.ariadne.users.User;

public class UserController extends Controller<String, User>{

	
	public UserController(Dao<String, User> userDao) {
		super(userDao);
	}

	public User performLogin(String username, String password) {

		User u = getRecord(username);

		if (u != null) {
			if (u.getPassword().equals(password)) {

				System.out.println("Login effettuato con successo");
				return u;
			} else {
				System.out.println("Password errata, riprovare");
				return null;
			}
		} else {

			System.out.println("Login non riuscito, riprovare");
		}

		return null;
	}

}

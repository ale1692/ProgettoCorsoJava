package it.ariadne.controller;

import it.ariadne.dao.Dao;
import it.ariadne.model.user.User;

public class UserController<T extends User> extends Controller<String, T>{

	
	public UserController(Dao<String, T> userDao) {
		super(userDao);
	}

	public T performLogin(String username, String password) {

		T u = getRecord(username);

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

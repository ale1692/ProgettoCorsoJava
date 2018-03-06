package it.ariadne.Controller;

import java.util.List;

import it.ariadne.dao.Dao;
import it.ariadne.users.User;

public class UserController<T extends User> {

	private Dao<String, T> userDao;

	public UserController(Dao<String, T> userDao) {
		this.userDao = userDao;
	}

	public void addRecord(T t) {

		userDao.addRecord(t);

	}

	public List<T> getAllRecords() {

		return userDao.getAllRecords();

	}

	public T getRecord(String code) {

		return userDao.getRecord(code);
	}

	public void deleteRecord(T t) {

		userDao.deleteRecord(t);

	}

	public void updateRecord(T t) {

		userDao.updateRecord(t);

	}

	public T performLogin(String username, String password) {

		T u = userDao.getRecord(username);

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

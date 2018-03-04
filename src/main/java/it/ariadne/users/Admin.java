package it.ariadne.users;

import it.ariadne.test.Role;

public class Admin extends User {

	public Admin(String name, String surname, String fiscalCode, String password, String userName, Role role) {
		super(name, surname, fiscalCode, password, userName, role);
		setAdmin(true);
	}

}

package it.ariadne.users;

public class Admin extends User {

	public Admin(String name, String surname, String fiscalCode, String password, String userName, Role role) {
		super(name, surname, fiscalCode, password, userName, role);
		setAdmin(true);
	}

}

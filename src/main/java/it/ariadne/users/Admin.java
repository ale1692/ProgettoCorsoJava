package it.ariadne.users;

public class Admin extends User {

	public Admin(String name, String surname, String password, String userName, Role role) {
		super(name, surname, password, userName, role);
		setAdmin(true);
	}

}

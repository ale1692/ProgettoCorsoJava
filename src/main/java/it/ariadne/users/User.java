package it.ariadne.users;

public class User {

	private String name;
	private String surname;
	private String fiscalCode;
	private String password;
	private String userName;
	private boolean isAdmin;
	private Role role;

	public User(String name, String surname, String fiscalCode, String password, String userName, Role role) {

		this.name = name;
		this.surname = surname;
		this.fiscalCode = fiscalCode;
		this.password = password;
		this.userName = userName;
		this.role = role;
		setAdmin(false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}

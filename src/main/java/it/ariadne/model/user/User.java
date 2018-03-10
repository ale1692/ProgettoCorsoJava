package it.ariadne.model.user;

public class User {

	private String name;
	private String surname;
	private String password;
	private String userName;
	private boolean isAdmin;
	private Role role;

	public User(String name, String surname, String password, String userName, Role role) {

		this.name = name;
		this.surname = surname;
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

	@Override
	public String toString() {

		String typeUser = "User";
		if (isAdmin) {
			typeUser = "Admin";
		}

		return "User: " + this.getUserName() + " Firstname: " + this.getUserName() + " Surname: " + this.getSurname()
				+ " Type: " + typeUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}

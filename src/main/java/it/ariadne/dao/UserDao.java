package it.ariadne.dao;

import java.util.Map;

import it.ariadne.users.User;

public interface UserDao {

	public void addUser(User utente);

	public Map<String, User> getAllUSers();

	public User getUser(String codiceFiscale);

	public void deleteUser(User utente);
	
	public void updateUser(User utente);

}

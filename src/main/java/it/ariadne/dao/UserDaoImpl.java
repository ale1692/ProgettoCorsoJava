package it.ariadne.dao;

import java.util.TreeMap;

import it.ariadne.users.User;

public class UserDaoImpl implements UserDao {

	// mappa con chiave il codice fiscale al posto del database
	TreeMap<String, User> utenti;

	public UserDaoImpl() {
		utenti = new TreeMap<>();
	}

	@Override
	public void addUser(User utente) {

		if(!utenti.containsKey(utente.getFiscalCode())) {
			
			utenti.put(utente.getFiscalCode(), utente);
		}else {
			System.out.println("Utente: " + utente.getFiscalCode() + ", already existed in database");
		}
			
	}

	@Override
	public TreeMap<String, User> getAllUSers() {
		return utenti;
	}

	@Override
	public User getUser(String codiceFiscale) {
		return utenti.get(codiceFiscale);
	}

	@Override
	public void deleteUser(User utente) {
		utenti.remove(utente.getFiscalCode());
		System.out.println("Utente: " + utente.getFiscalCode() + ", deleted from database");

	}
	
	@Override
	public void updateUser(User utente) {
		
		if(!utenti.containsKey(utente.getFiscalCode())) {
			
			System.out.println("Utente does not existed in database");
		}else {
			utenti.put(utente.getFiscalCode(), utente);
		}
			
	}

}

package it.ariadne.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import it.ariadne.users.User;

public class UserDaoImpl implements Dao<String, User> {

	// mappa con chiave il codice fiscale
	TreeMap<String, User> mappaUtenti;

	public UserDaoImpl() {
		mappaUtenti = new TreeMap<>();
	}

	@Override
	public void addRecord(User utente) {
		if (!mappaUtenti.containsKey(utente.getFiscalCode())) {

			mappaUtenti.put(utente.getFiscalCode(), utente);
			System.out.println("Utente: " + utente.getFiscalCode() + ", added in the database");

		} else {
			System.out.println("Utente: " + utente.getFiscalCode() + ", already existed in the database");
		}

	}

	@Override
	public List<User> getAllRecords() {

		List<User> listaUser = new ArrayList<>();

		for (Iterator<String> iterator = mappaUtenti.keySet().iterator(); iterator.hasNext();) {
			String persona = (String) iterator.next();
			listaUser.add(mappaUtenti.get(persona));
		}

		return listaUser;
	}

	@Override
	public User getRecord(String fiscalCode) {

		if (mappaUtenti.containsKey(fiscalCode)) {
			return mappaUtenti.get(fiscalCode);
		} else {
			System.out.println("Utente: " + fiscalCode + ", not present in the database");
			return null;
		}
	}

	@Override
	public void deleteRecord(User utente) {

		if (mappaUtenti.containsKey(utente.getFiscalCode())) {

			mappaUtenti.remove(utente.getFiscalCode());
			System.out.println("Utente: " + utente.getFiscalCode() + ", deleted from the database");

		} else {
			System.out.println("Utente: " + utente.getFiscalCode() + ", not present in the database");
		}

	}

	@Override
	public void updateRecord(User utente) {
		if (!mappaUtenti.containsKey(utente.getFiscalCode())) {

			System.out.println("Utente does not exist in database");
		} else {
			mappaUtenti.put(utente.getFiscalCode(), utente);
			System.out.println("Utente: " + utente.getFiscalCode() + ", updated in the database");
		}

	}

}

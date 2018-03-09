package it.ariadne.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import it.ariadne.user.User;

public class UserDaoImpl <T extends User >implements Dao<String, T> {

	// mappa con chiave il codice fiscale
	TreeMap<String, T> mappaUtenti;

	public UserDaoImpl() {
		mappaUtenti = new TreeMap<>();
	}

	@Override
	public void addRecord(T utente) {
		if (!mappaUtenti.containsKey(utente.getUserName())) {

			mappaUtenti.put(utente.getUserName(), utente);
			System.out.println("Utente: " + utente.getUserName() + ", added in the database");

		} else {
			System.out.println("Utente: " + utente.getUserName() + ", already existed in the database");
		}

	}

	@Override
	public List<T> getAllRecords() {

		List<T> listaUser = new ArrayList<>();

		for (Iterator<String> iterator = mappaUtenti.keySet().iterator(); iterator.hasNext();) {
			String persona = (String) iterator.next();
			listaUser.add(mappaUtenti.get(persona));
		}

		return listaUser;
	}

	@Override
	public T getRecord(String userName) {

		if (mappaUtenti.containsKey(userName)) {
			return mappaUtenti.get(userName);
		} else {
			System.out.println("Utente: " + userName + ", not present in the database");
			return null;
		}
	}

	@Override
	public void deleteRecord(T utente) {

		if (mappaUtenti.containsKey(utente.getUserName())) {

			mappaUtenti.remove(utente.getUserName());
			System.out.println("Utente: " + utente.getUserName() + ", deleted from the database");

		} else {
			System.out.println("Utente: " + utente.getUserName() + ", not present in the database");
		}

	}

	@Override
	public void updateRecord(T utente) {
		if (!mappaUtenti.containsKey(utente.getUserName())) {

			System.out.println("Utente does not exist in database");
		} else {
			mappaUtenti.put(utente.getUserName(), utente);
			System.out.println("Utente: " + utente.getUserName() + ", updated in the database");
		}

	}

}

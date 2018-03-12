package it.ariadne.controller;

import java.util.ArrayList;
import java.util.List;

import it.ariadne.dao.UserDaoImpl;
import it.ariadne.model.user.User;

public class UserController<T extends User> extends Controller<String, T> {

	public UserController(UserDaoImpl<T> userDao) {
		super(userDao);
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return T
	 * 
	 *         Esempio di controllo di Login
	 */

	public T performLogin(String username, String password) {

		T u = getRecord(username);

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

	/**
	 * 
	 * @param penalty
	 * @param u
	 *            Metodo che aggiorna i delay dell'utente, se è superiore alle 240 ore totali
	 *            l'utente viene penalizzato e non può più effetuare una
	 *            prenotazione
	 */
	public void updatePenaltyUser(int penalty, T u) {

		System.out.println("Ore trascorse dalla consegna alla fine della prenotazione: " + penalty);
		u.setDelay(penalty);
		if (u.getDelay() > 240) {
			u.setPenality(true);
			super.updateRecord(u);

		}

	}

	/**
	 * Il metodo restituisce tutti gli user con penalità=true (hanno un ritardo
	 * totale > 240 ore)
	 */
	public List<T> getAllUserPenalty() {

		List<T> listResult = new ArrayList<>();
		for (T lista : super.getAllRecords()) {

			if (lista.isPenality()) {
				listResult.add(lista);
			}
		}
		;
		return listResult;
	}

}

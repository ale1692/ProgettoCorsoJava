package it.ariadne.booking;

import org.joda.time.DateTime;

import it.ariadne.resources.Resource;
import it.ariadne.users.User;

public class Booking<T extends Resource> {

	private User utente;
	private T risorsa;
	private DateTime startRisorsa;
	private DateTime endRisorsa;
	private int id;
	private boolean active;

	public Booking(int id, User utente, T risorsa, DateTime startRisorsa, DateTime endRisorsa) {
		this.utente = utente;
		this.risorsa = risorsa;
		this.startRisorsa = startRisorsa;
		this.endRisorsa = endRisorsa;
		this.id = id;
		setActive();
	}

	
	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public Resource getRisorsa() {
		return risorsa;
	}

	public void setRisorsa(T risorsa) {
		this.risorsa = risorsa;
	}

	public DateTime getStartRisorsa() {
		return startRisorsa;
	}

	public void setStartRisorsa(DateTime startRisorsa) {
		this.startRisorsa = startRisorsa;
	}

	public DateTime getEndRisorsa() {
		return endRisorsa;
	}

	public void setEndRisorsa(DateTime endRisorsa) {
		this.endRisorsa = endRisorsa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive() {

		if (endRisorsa.isAfterNow()) {
			this.active = false;
			return;
		}

		this.active = true;
	}

}

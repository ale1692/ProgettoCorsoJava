package it.ariadne.model.booking;

import org.joda.time.DateTime;

import it.ariadne.model.resource.Resource;
import it.ariadne.model.user.User;

public class Booking<T extends Resource, U extends User> {

	private U utente;
	private T risorsa;
	private DateTime startRisorsa;
	private DateTime endRisorsa;
	private int id;
	private boolean active;

	public Booking() {
	}

	public Booking(int id, U utente, T risorsa, DateTime startRisorsa, DateTime endRisorsa) {
		this.utente = utente;
		this.risorsa = risorsa;
		this.startRisorsa = startRisorsa;
		this.endRisorsa = endRisorsa;
		this.id = id;
		setActive();
	}

	public U getUtente() {
		return utente;
	}

	public void setUtente(U utente) {
		this.utente = utente;
	}

	public T getRisorsa() {
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

		if (endRisorsa.isBeforeNow()) {
			this.active = false;
			return;
		}

		this.active = true;
	}

}

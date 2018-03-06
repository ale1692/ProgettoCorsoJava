package it.ariadne.booking;

import java.util.Date;

import it.ariadne.resources.Resource;
import it.ariadne.users.User;

public class Booking <T extends Resource> {

	private User utente;
	private T risorsa;
	private Date startRisorsa;
	private Date endRisorsa;
	private int id;

	public Booking(User utente, T risorsa, Date startRisorsa, Date endRisorsa, int id) {
		super();
		this.utente = utente;
		this.risorsa = risorsa;
		this.startRisorsa = startRisorsa;
		this.endRisorsa = endRisorsa;
		this.id = id;
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

	public Date getStartRisorsa() {
		return startRisorsa;
	}

	public void setStartRisorsa(Date startRisorsa) {
		this.startRisorsa = startRisorsa;
	}

	public Date getEndRisorsa() {
		return endRisorsa;
	}

	public void setEndRisorsa(Date endRisorsa) {
		this.endRisorsa = endRisorsa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

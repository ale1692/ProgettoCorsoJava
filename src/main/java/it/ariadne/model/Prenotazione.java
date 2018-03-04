package it.ariadne.model;

import java.util.Date;

import it.ariadne.resources.Resource;
import it.ariadne.users.User;

public class Prenotazione {

	private User utente;
	private Resource risorsa;
	private Date startRisorsa;
	private Date endRisorsa;
	private int identificativo;

	public Prenotazione(User utente, Resource risorsa, Date startRisorsa, Date endRisorsa, int identificativo) {
		super();
		this.utente = utente;
		this.risorsa = risorsa;
		this.startRisorsa = startRisorsa;
		this.endRisorsa = endRisorsa;
		this.identificativo = identificativo;
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

	public void setRisorsa(Resource risorsa) {
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

	public int getIdentificativo() {
		return identificativo;
	}

	public void setIdentificativo(int identificativo) {
		this.identificativo = identificativo;
	}

}

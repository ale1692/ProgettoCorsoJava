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
	private DateTime resourceDelivery;

	public Booking(int id, U utente, T risorsa, DateTime startRisorsa, DateTime endRisorsa) {
		this.utente = utente;
		this.risorsa = risorsa;
		this.startRisorsa = startRisorsa;
		this.endRisorsa = endRisorsa;
		this.id = id;
		setActive();
		this.resourceDelivery=this.endRisorsa;
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
	
	
	public DateTime getResourceDelivery() {
		return resourceDelivery;
	}

	public void setResourceDelivery(DateTime resourceDelivery) {
		this.resourceDelivery = resourceDelivery;
	}

	@Override
	public String toString() {

		return "Booking: " + this.id + " User: " + getUtente().getUserName() + " Resource: ("
				+ this.getRisorsa().getCode() + ", Type: " + getRisorsa().getClass().getSimpleName() + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Booking<T, U> other = (Booking<T, U>) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

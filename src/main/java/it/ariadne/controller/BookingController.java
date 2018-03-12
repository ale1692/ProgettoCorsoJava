package it.ariadne.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Minutes;

import it.ariadne.dao.BookingDaoImpl;
import it.ariadne.model.booking.Booking;
import it.ariadne.model.resource.Resource;
import it.ariadne.model.user.User;

public class BookingController<T extends Resource, U extends User> extends Controller<Integer, Booking<T, U>> {

	public BookingController(BookingDaoImpl<T, U> bookingDao) {

		super(bookingDao);
	}

	/**
	 * Aggiungo una nuova prenotazione, l'azione non va a buon fine se la risorsa
	 * non è disponibile o l'utente ha una penalità e quindi non può fare altre
	 * prenotazioni
	 */
	@Override
	public void addRecord(Booking<T, U> t) {
		List<Booking<T, U>> lista = setActiveBookings();
		Interval intervalList;
		Interval intervalInput;

		if ((t.getRisorsa().isAvailable() == true) || (t.getUtente().isPenality())) {

			if (lista.size() == 0) {
				super.addRecord(t);
				System.out.println(t.getRisorsa().getCode());
				return;
			}

			else {
				for (Booking<T, U> booking : lista) {

					intervalList = new Interval(booking.getStartRisorsa(), booking.getEndRisorsa());
					intervalInput = new Interval(t.getStartRisorsa(), t.getEndRisorsa());

					if (booking.getId() == t.getId()) {
						System.out.println("Booking: " + t.getId() + " Id già esiste");
						return;
					}

					else if ((intervalList.overlaps(intervalInput))
							&& (booking.getRisorsa().getCode() == t.getRisorsa().getCode())) {

						System.out.println("Booking: " + t.getId() + " Risorsa: " + t.getRisorsa().getCode()
								+ " Già prenotata per quel periodo temporale");
						return;

					}

					else {

						super.addRecord(t);
						return;
					}

				}
			}
		} else {

			System.out.println("Non è possibile creare una nuova prenotazione per quella risorsa o per quell'utente");
			return;
		}
	}

	/**
	 * 
	 * @param b
	 * @param deliveryDate
	 * @return int, Metodo che calcola le ore che passano dalla data di consegna
	 *         alla data pattuita al momento della registrazione della prenotazione
	 */

	public int setPenalty(Booking<T, U> b, DateTime deliveryDate) {

		if ((!b.isActive()) && (deliveryDate.isAfter(b.getEndRisorsa()))) {
			DateTime endDate = b.getEndRisorsa();
			Minutes minutes = Minutes.minutesBetween(endDate, deliveryDate);
			int penality = (minutes.getMinutes() / 60);

			return penality;
		}

		return 0;

	}

	/**
	 * 
	 * @return List<Booking<T, U>>, Restituisce tutte le prenotazioni attive
	 */
	public List<Booking<T, U>> getActiveBooking() {

		List<Booking<T, U>> listAllBookings = setActiveBookings();
		List<Booking<T, U>> listActiveBookings = new ArrayList<>();

		for (Booking<T, U> booking : listAllBookings) {
			if (booking.isActive()) {
				listActiveBookings.add(booking);
			}
		}

		return listActiveBookings;
	}

	/**
	 * 
	 * @return List<Booking<T, U>>, Restituisce tutte le prenotazioni e setta se sono
	 *         ancore attive in base alla data di fine.
	 */

	private List<Booking<T, U>> setActiveBookings() {

		List<Booking<T, U>> listAllBookings = super.getAllRecords();
		boolean activeBooking;
		boolean statusBooking;

		for (Booking<T, U> booking : listAllBookings) {

			activeBooking = booking.isActive();
			booking.setActive();
			statusBooking = booking.isActive();

			// Faccio l'update sole delle prenotazioni con status modificato (da active=true
			// a active=false)
			if (activeBooking != statusBooking) {
				super.updateRecord(booking);
				System.out.println("Booking: " + booking.getId() + " Update status booking");
			}

		}

		return listAllBookings;
	}

	/**
	 * 
	 * @param T
	 *            r
	 * 
	 * @return List<Booking<T, U>>, Cerca le prenotazioni attive per una tipologia di
	 *         risorsa
	 */

	public List<Booking<T, U>> findByTypeResource(String type) {

		List<Booking<T, U>> lista = getActiveBooking();
		List<Booking<T, U>> listaActive = new ArrayList<>();

		for (Booking<T, U> booking : lista) {

			if (booking.getRisorsa().getClass().getSimpleName().equals(type)) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	/**
	 * 
	 * @param T
	 *            r
	 * 
	 * @return List<Booking<T, U>>, Cerca le prenotazioni attive per una determinata
	 *         risorsa
	 */
	public List<Booking<T, U>> findByResource(T r) {

		List<Booking<T, U>> lista = getActiveBooking();
		List<Booking<T, U>> listaActive = new ArrayList<>();

		for (Booking<T, U> booking : lista) {

			if (booking.getRisorsa().equals(r)) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	/**
	 * 
	 * @param U
	 *            u
	 * 
	 * @return List<Booking<T, U>>, Cerca le prenotazioni attive per un determinato
	 *         utente
	 */

	public List<Booking<T, U>> findActiveByUser(U u) {

		List<Booking<T, U>> lista = getActiveBooking();
		List<Booking<T, U>> listaActive = new ArrayList<>();

		for (Booking<T, U> booking : lista) {

			if (booking.getUtente().equals(u)) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	/**
	 * 
	 * @param U
	 *            u
	 * 
	 * @return List<Booking<T, U>>, Storico delle prenotazioni(quindi non più attive)
	 *         fatte da un utente
	 */
	public List<Booking<T, U>> findPastByUser(U u) {

		List<Booking<T, U>> lista = getActiveBooking();
		List<Booking<T, U>> listaActive = new ArrayList<>();

		for (Booking<T, U> booking : lista) {

			if (booking.getUtente().equals(u)) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	/**
	 * 
	 * @param r
	 * @param beginSeachDate
	 * @param endSeachDate
	 * @param hours
	 * @param minutes
	 * @return DateTime, Cerca la prima disponibilità utile di una data risorsa per
	 *         quell'intervallo di tempo (beginSeachDate, endSeachDate) e quella
	 *         durata (hours and minutes)
	 */

	public DateTime findFirstResourceAvailability(T r, DateTime beginSeachDate, DateTime endSeachDate, int hours,
			int minutes) {

		List<Booking<T, U>> resourceActiveBookings = findByResource(r);
		System.out.println("Resource active: " + resourceActiveBookings.size());
		DateTime partialEndDate;
		Interval partialinterval;
		Interval storedInterval;
		boolean busyinterval = false;

		Minutes computeMinutes = Minutes.minutesBetween(beginSeachDate, endSeachDate);
		int confrontMinutes = (hours * 60) + minutes;

		if (computeMinutes.getMinutes() >= confrontMinutes) {
			while (beginSeachDate.isBefore(endSeachDate)) {

				partialEndDate = beginSeachDate.plusHours(hours).plusMinutes(minutes);

				partialinterval = new Interval(beginSeachDate, partialEndDate);

				for (Booking<T, U> booking : resourceActiveBookings) {

					storedInterval = new Interval(booking.getStartRisorsa(), booking.getEndRisorsa());

					if ((partialinterval.overlaps(storedInterval))) {
						busyinterval = true;
						break;
					} else
						busyinterval = false;

				}

				if (busyinterval == false)
					return beginSeachDate;

				beginSeachDate = beginSeachDate.plusMinutes(30);
			}

		}

		else {
			System.out.println("Durata non valida per quel periodo temporale");
			return null;
		}

		return null;
	}

	/**
	 * 
	 * @param resourceType
	 * @param beginSeachDate
	 * @param endSeachDate
	 * @param hours
	 * @param minutes
	 * @param minimumConstraint
	 * @return Resource
	 * 
	 *         Cerca la prima risorsa(esempio Car) disponibile per quell'intervallo
	 *         di tempo (beginSeachDate, endSeachDate) e quella durata (hours and
	 *         minutes) con la condizione minimumConstraint
	 */
	public Resource findResourceAvailabilityByConstraint(String resourceType, DateTime beginSeachDate,
			DateTime endSeachDate, int hours, int minutes, int minimumConstraint) {

		List<Booking<T, U>> resourceActiveBookings = findByTypeResource(resourceType);
		System.out.println("Resource active: " + resourceActiveBookings.size());

		DateTime partialEndDate;
		Interval partialinterval;
		Interval storedInterval;
		Minutes computeMinutes = Minutes.minutesBetween(beginSeachDate, endSeachDate);
		int comparisonMinutes = (hours * 60) + minutes;

		if (computeMinutes.getMinutes() >= comparisonMinutes) {
			while (beginSeachDate.isBefore(endSeachDate)) {

				partialEndDate = beginSeachDate.plusHours(hours).plusMinutes(minutes);

				partialinterval = new Interval(beginSeachDate, partialEndDate);

				for (Booking<T, U> booking : resourceActiveBookings) {

					if (booking.getRisorsa().searchByConstraint(minimumConstraint)) {

						storedInterval = new Interval(booking.getStartRisorsa(), booking.getEndRisorsa());

						if ((!partialinterval.overlaps(storedInterval))) {

							System.out.println("Resource: " + booking.getRisorsa().getCode());
							return booking.getRisorsa();
						}
					}
				}

				beginSeachDate = beginSeachDate.plusMinutes(30);
			}
			return null;

		}

		else {
			System.out.println("Durata non valida per quel periodo temporale");
			return null;
		}
	}

}

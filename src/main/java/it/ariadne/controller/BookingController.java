package it.ariadne.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import it.ariadne.dao.Dao;
import it.ariadne.model.booking.Booking;
import it.ariadne.model.resource.Resource;
import it.ariadne.model.user.User;

public class BookingController<T extends Resource, U extends User> extends Controller<Integer, Booking<T, U>> {

	public BookingController(Dao<Integer, Booking<T, U>> bookingDao) {

		super(bookingDao);
	}

	@Override
	public void addRecord(Booking<T, U> t) {
		List<Booking<T, U>> lista = super.getAllRecords();
		Interval intervalList;
		Interval intervalInput;

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
					System.out.println(t.getRisorsa().getCode());
					return;
				}

			}
		}

	}

	
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

	private List<Booking<T, U>> setActiveBookings() {

		List<Booking<T, U>> listAllBookings = super.getAllRecords();
		boolean activeBooking;
		boolean statusBooking;

		for (Booking<T, U> booking : listAllBookings) {

			activeBooking = booking.isActive();
			booking.setActive();
			statusBooking = booking.isActive();

			if (activeBooking != statusBooking) {
				updateRecord(booking);
				System.out.println("Booking: " + booking.getId() + " Update status booking");
			}

		}

		return listAllBookings;
	}

	public List<Booking<T, U>> findByTypeActiveResource(T r) {

		List<Booking<T, U>> lista = getActiveBooking();
		List<Booking<T, U>> listaActive = new ArrayList<>();

		for (Booking<T, U> booking : lista) {

			if (booking.getRisorsa().getClass() == r.getClass()) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	public List<Booking<T,U>> findByActiveResource(T r) {

		List<Booking<T,U>> lista = getActiveBooking();
		List<Booking<T,U>> listaActive = new ArrayList<>();

		for (Booking<T,U> booking : lista) {

			if (booking.getRisorsa().equals(r)) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	public List<Booking<T,U>> findByActiveUser(U u) {

		List<Booking<T,U>> lista = getActiveBooking();
		List<Booking<T,U>> listaActive = new ArrayList<>();

		for (Booking<T,U> booking : lista) {

			if (booking.getUtente().equals(u)) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	public DateTime findFirstResourceAvailability(T r, DateTime beginSeachDate, DateTime endSeachDate, int hours,
			int minutes) {

		List<Booking<T,U>> resourceActiveBookings = findByActiveResource(r);
		DateTime partialEndDate;
		Interval partialinterval;
		Interval storedInterval;

		boolean busyinterval = false;

		while (beginSeachDate.isBefore(endSeachDate)) {

			partialEndDate = beginSeachDate.plusHours(hours).plusMinutes(minutes);

			partialinterval = new Interval(beginSeachDate, partialEndDate);

			for (Booking<T,U> booking : resourceActiveBookings) {

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

		return null;
	}

	public Resource findResourceAvailabilityByContraint(T resourceType, int hours, int minutes, int minimumConstraint) {

		DateTime beginSeachDate = new DateTime();
		Interval storedInterval;

		List<Booking<T,U>> resourceActiveBookings = findByActiveResource(resourceType);
		T r;
		Booking<T,U> candidateReservation = null;

		while (candidateReservation == null) {

			DateTime endSearchDate = beginSeachDate.plusHours(hours).plusMinutes(minutes);
			Interval searchInterval = new Interval(beginSeachDate, endSearchDate);

			for (Booking<T,U> booking : resourceActiveBookings) {

				r = ((T) booking.getRisorsa());

				if (r.getClass().getSimpleName().equals(resourceType.getClass().getSimpleName())) {

					if (r.searchByConstraint(minimumConstraint)) {

						storedInterval = new Interval(booking.getStartRisorsa(), booking.getEndRisorsa());

						if ((!searchInterval.overlaps(storedInterval))) {

							candidateReservation = booking;
							break;

						}

					}
				}
			}

			beginSeachDate = beginSeachDate.plusHours(30);

		}

		return candidateReservation.getRisorsa();

	}

}

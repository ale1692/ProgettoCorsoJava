package it.ariadne.Controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import it.ariadne.booking.Booking;
import it.ariadne.dao.Dao;
import it.ariadne.resources.Resource;
import it.ariadne.users.User;

public class BookingController extends Controller<Integer, Booking<? extends Resource>> {

	public BookingController(Dao<Integer, Booking<?>> bookingDao) {

		super(bookingDao);
	}

	@Override
	public void addRecord(Booking<?> t) {
		List<Booking<?>> lista = getAllRecords();
		Interval intervalList;
		Interval intervalInput;

		if (lista.size() == 0) {
			super.addRecord(t);
			System.out.println(t.getRisorsa().getCode());
			return;
		}

		else {
			for (Booking<?> booking : lista) {

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

	@Override
	public List<Booking<?>> getAllRecords() {
		//return setActiveBookings();
		return super.getAllRecords();
	}

	public List<Booking<?>> getActiveBooking() {

		List<Booking<?>> listAllBookings = setActiveBookings();
		List<Booking<?>> listActiveBookings = new ArrayList<>();

		for (Booking<?> booking : listAllBookings) {
			if (booking.isActive()) {
				listActiveBookings.add(booking);
			}
		}

		return listActiveBookings;
	}

	private List<Booking<?>> setActiveBookings() {

		List<Booking<?>> listAllBookings = getAllRecords();
		boolean activeBooking;
		boolean statusBooking;

		for (Booking<?> booking : listAllBookings) {

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

	public List<Booking<?>> findByTypeActiveResource(Resource r) {

		List<Booking<?>> lista = getActiveBooking();
		List<Booking<?>> listaActive = new ArrayList<>();

		for (Booking<?> booking : lista) {

			if (booking.getRisorsa().getClass() == r.getClass()) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	public List<Booking<?>> findByActiveResource(Resource r) {

		List<Booking<?>> lista = getActiveBooking();
		List<Booking<?>> listaActive = new ArrayList<>();

		for (Booking<?> booking : lista) {

			if (booking.getRisorsa().equals(r)) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	public List<Booking<?>> findByActiveUser(User u) {

		List<Booking<?>> lista = getActiveBooking();
		List<Booking<?>> listaActive = new ArrayList<>();

		for (Booking<?> booking : lista) {

			if (booking.getUtente().equals(u)) {
				listaActive.add(booking);
			}
		}
		return listaActive;

	}

	public DateTime findFirstResourceAvailability(Resource r, DateTime beginSeachDate, DateTime endSeachDate, int hours,
			int minutes) {

		List<Booking<?>> resourceActiveBookings = findByActiveResource(r);

		DateTime partialEndDate;
		Interval partialinterval;
		Interval storedInterval;

		boolean busyinterval = false;

		while (beginSeachDate.isBefore(endSeachDate)) {

			partialEndDate = beginSeachDate.plusHours(hours).plusMinutes(minutes);

			partialinterval = new Interval(beginSeachDate, partialEndDate);

			for (Booking<?> booking : resourceActiveBookings) {

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

	public Resource findResourceAvailabilityByContraint(Resource resourceType, int hours, int minutes,
			int minimumConstraint) {

		DateTime beginSeachDate = new DateTime();
		Interval storedInterval;

		List<Booking<?>> resourceActiveBookings = findByActiveResource(resourceType);
		Resource r;
		Booking<?> candidateReservation = null;

		while (candidateReservation == null) {

			DateTime endSearchDate = beginSeachDate.plusHours(hours).plusMinutes(minutes);
			Interval searchInterval = new Interval(beginSeachDate, endSearchDate);

			for (Booking<?> booking : resourceActiveBookings) {

				r = booking.getRisorsa();

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

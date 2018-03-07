package it.ariadne.Controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;

import it.ariadne.booking.Booking;
import it.ariadne.dao.Dao;

public class BookingController<T extends Booking<?>> {

	private Dao<Integer, Booking<?>> bookingDao;

	public BookingController(Dao<Integer, Booking<?>> prova) {

		this.bookingDao = prova;
	}

	public void addBooking(T t) {

		List<Booking<?>> lista = getAllBookings();
		Interval intervalList;
		Interval intervalInput;
		int i=0;

		if (lista.size() == 0) {
			bookingDao.addRecord(t);
			System.out.println(t.getRisorsa().getCode());
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

					bookingDao.addRecord(t);
					System.out.println(t.getRisorsa().getCode());
				}

			}
		}
	}

	public List<Booking<?>> getAllBookings() {

		return setActiveBookings();

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

		List<Booking<?>> listAllBookings = bookingDao.getAllRecords();
		boolean activeBooking;
		boolean statusBooking;

		for (Booking<?> booking : listAllBookings) {

			activeBooking = booking.isActive();
			booking.setActive();
			statusBooking = booking.isActive();

			if (activeBooking != statusBooking) {
				updateBooking(booking);
				System.out.println("Booking: " + booking.getId() + " Update status booking");
			}

		}
		
		System.out.println("Numero di prenotazionde del db: " +listAllBookings.size());
		
		return listAllBookings;
	}

	
	public Booking<?> getBooking(int id) {

		return bookingDao.getRecord(id);
	}

	public void deleteBooking(T t) {

		bookingDao.deleteRecord(t);

	}

	public void updateBooking(Booking<?> booking) {

		bookingDao.updateRecord(booking);

	}

}

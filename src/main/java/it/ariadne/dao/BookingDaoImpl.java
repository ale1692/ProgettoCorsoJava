package it.ariadne.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import it.ariadne.booking.Booking;

public class BookingDaoImpl implements Dao<Integer, Booking<?>> {

	// mappa con chiave id
	TreeMap<Integer, Booking<?>> mappaPrenotazioni;

	public BookingDaoImpl() {
		mappaPrenotazioni = new TreeMap<>();
	}

	@Override
	public void addRecord(Booking<?> b) {
		if (!mappaPrenotazioni.containsKey(b.getId())) {

			mappaPrenotazioni.put(b.getId(), b);
			System.out.println("Booking: " + b.getId() + ", added in the database");

		} else {
			System.out.println("Booking: " + b.getId() + ", already existed in the database");
		}

	}

	@Override
	public List<Booking<?>> getAllRecords() {

		List<Booking<?>> listaBooking = new ArrayList<>();

		for (Iterator<Integer> iterator = mappaPrenotazioni.keySet().iterator(); iterator.hasNext();) {
			int prenotazione = iterator.next();
			listaBooking.add(mappaPrenotazioni.get(prenotazione));
		}

		return listaBooking;
	}

	@Override
	public Booking<?> getRecord(Integer id) {

		if (mappaPrenotazioni.containsKey(id)) {

			return mappaPrenotazioni.get(id);
		} else {
			System.out.println("Booking: " + id + ", not present in the database");
			return null;
		}

	}

	@Override
	public void deleteRecord(Booking<?> b) {

		if (mappaPrenotazioni.containsKey(b.getId())) {
			mappaPrenotazioni.remove(b.getId());
			System.out.println("Booking: " + b.getId() + ", deleted from database");
		} else {
			System.out.println("Booking: " + b.getId() + ", not present in the database");
		}

	}

	@Override
	public void updateRecord(Booking<?> b) {
		if (!mappaPrenotazioni.containsKey(b.getId())) {

			System.out.println("Booking does not exist in the database");
		} else {
			mappaPrenotazioni.put(b.getId(), b);
			System.out.println("Booking: " + b.getId() + ", updated in the database");
		}
	}

}

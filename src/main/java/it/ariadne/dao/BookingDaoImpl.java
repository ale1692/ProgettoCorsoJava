package it.ariadne.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import it.ariadne.booking.Booking;
import it.ariadne.resource.Resource;
import it.ariadne.user.User;

public class BookingDaoImpl <T extends Resource, U extends User> implements Dao<Integer, Booking<T, U>> {

	// mappa con chiave id
	TreeMap<Integer, Booking<T,U>> mappaPrenotazioni;

	public BookingDaoImpl() {
		mappaPrenotazioni = new TreeMap<>();
	}

	@Override
	public void addRecord(Booking<T, U> b) {
		if (!mappaPrenotazioni.containsKey(b.getId())) {

			mappaPrenotazioni.put(b.getId(), b);
			System.out.println("Booking: " + b.getId() + ", added in the database");

		} else {
			System.out.println("Booking: " + b.getId() + ", already existed in the database");
		}

	}

	@Override
	public List<Booking<T, U>> getAllRecords() {

		List<Booking<T, U>> listaBooking = new ArrayList<>();
		if (mappaPrenotazioni.size() != 0) {

			for (Iterator<Integer> iterator = mappaPrenotazioni.keySet().iterator(); iterator.hasNext();) {
				int prenotazione = iterator.next();
				listaBooking.add(mappaPrenotazioni.get(prenotazione));
			}

		}
		return listaBooking;
	}

	@Override
	public Booking<T, U> getRecord(Integer id) {

		if (mappaPrenotazioni.containsKey(id)) {

			return  mappaPrenotazioni.get(id);
		} else {
			System.out.println("Booking: " + id + ", not present in the database");
			return null;
		}

	}

	@Override
	public void deleteRecord(Booking<T, U> b) {

		if (mappaPrenotazioni.containsKey(b.getId())) {
			mappaPrenotazioni.remove(b.getId());
			System.out.println("Booking: " + b.getId() + ", deleted from database");
		} else {
			System.out.println("Booking: " + b.getId() + ", not present in the database");
		}

	}

	@Override
	public void updateRecord(Booking<T, U> b) {
		if (!mappaPrenotazioni.containsKey(b.getId())) {

			System.out.println("Booking does not exist in the database");
		} else {
			mappaPrenotazioni.put(b.getId(), b);
			System.out.println("Booking: " + b.getId() + ", updated in the database");
		}
	}

}

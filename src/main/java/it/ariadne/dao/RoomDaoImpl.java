package it.ariadne.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import it.ariadne.resources.Car;
import it.ariadne.resources.Room;

public class RoomDaoImpl implements Dao<String, Room> {

	// mappa con chiave il codice della risorsa
	TreeMap<String, Room> mappaRisorse;

	public RoomDaoImpl() {
		mappaRisorse = new TreeMap<>();
	}

	@Override
	public void addRecord(Room r) {

		if (!mappaRisorse.containsKey(r.getCode())) {

			mappaRisorse.put(r.getCode(), r);
			System.out.println("Resource: " + r.getCode() + ", added in the database");

		} else {
			System.out.println("Resource: " + r.getCode() + ", already existed in the database");
		}

	}

	@Override
	public List<Room> getAllRecords() {

		List<Room> listaResource = new ArrayList<>();

		for (Iterator<String> iterator = mappaRisorse.keySet().iterator(); iterator.hasNext();) {
			String risorsa = (String) iterator.next();
			listaResource.add(mappaRisorse.get(risorsa));
		}

		return listaResource;

	}

	@Override
	public Room getRecord(String code) {

		if (mappaRisorse.containsKey(code)) {

			return mappaRisorse.get(code);
		} else {
			System.out.println("Resource: " + code + ", not present in the database");
			return null;
		}
	}

	@Override
	public void deleteRecord(Room r) {

		if (mappaRisorse.containsKey(r.getCode())) {
			mappaRisorse.remove(r.getCode());
			System.out.println("Resource: " + r.getCode() + ", deleted from database");
		} else {
			System.out.println("Resource: " + r.getCode() + ", not present in the database");
		}

	}

	@Override
	public void updateRecord(Room r) {

		if (!mappaRisorse.containsKey(r.getCode())) {

			System.out.println("Resource does not exist in the database");
		} else {
			mappaRisorse.put(r.getCode(), r);
			System.out.println("Resource: " + r.getCode() + ", updated in the database");
		}
	}
}

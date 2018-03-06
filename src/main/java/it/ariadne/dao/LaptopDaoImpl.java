package it.ariadne.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import it.ariadne.resources.Laptop;
import it.ariadne.resources.Room;

public class LaptopDaoImpl implements Dao<String, Laptop> {

	// mappa con chiave il codice della risorsa
	TreeMap<String, Laptop> mappaRisorse;

	public LaptopDaoImpl() {
			mappaRisorse = new TreeMap<>();
		}

	@Override
	public void addRecord(Laptop r) {

		if (!mappaRisorse.containsKey(r.getCode())) {

			mappaRisorse.put(r.getCode(), r);
			System.out.println("Resource: " + r.getCode() + ", added in the database");

		} else {
			System.out.println("Resource: " + r.getCode() + ", already existed in the database");
		}

	}

	@Override
	public List<Laptop> getAllRecords() {

		List<Laptop> listaResource = new ArrayList<>();

		for (Iterator<String> iterator = mappaRisorse.keySet().iterator(); iterator.hasNext();) {
			String risorsa = (String) iterator.next();
			listaResource.add(mappaRisorse.get(risorsa));
		}

		return listaResource;

	}

	@Override
	public Laptop getRecord(String code) {

		if (mappaRisorse.containsKey(code)) {

			return mappaRisorse.get(code);
		} else {
			System.out.println("Resource: " + code + ", not present in the database");
			return null;
		}
	}

	@Override
	public void deleteRecord(Laptop r) {

		if (mappaRisorse.containsKey(r.getCode())) {
			mappaRisorse.remove(r.getCode());
			System.out.println("Resource: " + r.getCode() + ", deleted from database");
		} else {
			System.out.println("Resource: " + r.getCode() + ", not present in the database");
		}

	}

	@Override
	public void updateRecord(Laptop r) {

		if (!mappaRisorse.containsKey(r.getCode())) {

			System.out.println("Resource does not exist in the database");
		} else {
			mappaRisorse.put(r.getCode(), r);
			System.out.println("Resource: " + r.getCode() + ", updated in the database");
		}
	}

}

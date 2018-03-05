package it.ariadne.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import it.ariadne.resources.Resource;
import it.ariadne.users.User;

public class ResourceDaoImpl implements Dao<String, Resource> {

	// mappa con chiave il codice della risorsa
	TreeMap<String, Resource> mappaRisorse;

	public ResourceDaoImpl() {
		mappaRisorse = new TreeMap<>();
	}

	@Override
	public void addRecord(Resource r) {

		if (!mappaRisorse.containsKey(r.getCode())) {

			mappaRisorse.put(r.getCode(), r);
			System.out.println("Resource: " + r.getCode() + ", added in the database");

		} else {
			System.out.println("Resource: " + r.getCode() + ", already existed in the database");
		}

	}

	@Override
	public List<Resource> getAllRecords() {

		List<Resource> listaResource = new ArrayList<>();

		for (Iterator<String> iterator = mappaRisorse.keySet().iterator(); iterator.hasNext();) {
			String risorsa = (String) iterator.next();
			listaResource.add(mappaRisorse.get(risorsa));
		}

		return listaResource;

	}

	@Override
	public Resource getRecord(String code) {

		if (mappaRisorse.containsKey(code)) {

			return mappaRisorse.get(code);
		} else {
			System.out.println("Resource: " + code + ", not present in the database");
			return null;
		}
	}

	@Override
	public void deleteRecord(Resource r) {

		if (mappaRisorse.containsKey(r.getCode())) {
			mappaRisorse.remove(r.getCode());
			System.out.println("Resource: " + r.getCode() + ", deleted from database");
		} else {
			System.out.println("Resource: " +  r.getCode() + ", not present in the database");
		}

	}

	@Override
	public void updateRecord(Resource r) {

		if (!mappaRisorse.containsKey(r.getCode())) {

			System.out.println("Resource does not exist in the database");
		} else {
			mappaRisorse.put(r.getCode(), r);
			System.out.println("Resource: " + r.getCode() + ", updated in the database");
		}

	}

}

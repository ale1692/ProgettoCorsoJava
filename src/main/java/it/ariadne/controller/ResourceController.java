package it.ariadne.controller;

import java.util.ArrayList;
import java.util.List;

import it.ariadne.dao.ResourceDaoImpl;
import it.ariadne.model.resource.Resource;

public class ResourceController<T extends Resource> extends Controller<String, T> {

	public ResourceController(ResourceDaoImpl<T> resourceDao) {
		super(resourceDao);
	}

	/**
	 * 
	 * @param minimum
	 * @return List<T>
	 * 
	 *  Restituisce tutte le risorse che hanno quella determinata condizione
	 */
	public List<T> getResourceFiltered(int minimum) {

		List<T> resources = super.getAllRecords();
		List<T> resourcesFiltered = new ArrayList<>();

		for (T r : resources) {
			if (r.searchByConstraint(minimum)) {
				resourcesFiltered.add(r);
			}
		}

		return resourcesFiltered;
	}

	/**
	 * 
	 * @return List<T>	 
	 * 
	 * Restituisce tutte le risorse disponibili
	 * 
	 * */
	
	public List<T> getResourcesAvaible() {

		List<T> resources = super.getAllRecords();
		List<T> resourcesFiltered = new ArrayList<>();

		for (T r : resources) {
			if (r.isAvailable()) {
				resourcesFiltered.add(r);
			}
		}

		return resourcesFiltered;
	}

}

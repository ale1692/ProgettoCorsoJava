package it.ariadne.controller;

import java.util.ArrayList;
import java.util.List;

import it.ariadne.dao.Dao;
import it.ariadne.resource.Resource;

public class ResourceController <T extends Resource> extends Controller<String,T> {


	public ResourceController(Dao<String, T> resourceDao) {
		super(resourceDao);
	}

	
	
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

}

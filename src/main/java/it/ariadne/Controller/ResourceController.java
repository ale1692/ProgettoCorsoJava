package it.ariadne.Controller;

import java.util.ArrayList;
import java.util.List;

import it.ariadne.dao.Dao;
import it.ariadne.resources.Resource;

public class ResourceController<T extends Resource> {

	private Dao<String, T> resourceDao;

	public ResourceController(Dao<String, T> resourceDao) {

		this.resourceDao = resourceDao;
	}

	public void addRecord(T t) {

		resourceDao.addRecord(t);

	}

	public List<T> getAllRecords() {

		return resourceDao.getAllRecords();

	}

	public T getRecord(String code) {

		return resourceDao.getRecord(code);
	}

	public void deleteRecord(T t) {

		resourceDao.deleteRecord(t);

	}

	public void updateRecord(T t) {

		resourceDao.updateRecord(t);

	}

	public List<T> getResourceFiltered(int minimum) {

		List<T> resources = resourceDao.getAllRecords();
		List<T> resourcesFiltered = new ArrayList<>();

		for (T r : resources) {
			if (r.searchByConstraint(minimum)) {
				resourcesFiltered.add(r);
			}
		}

		return resourcesFiltered;
	}

}

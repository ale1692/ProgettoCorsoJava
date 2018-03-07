package it.ariadne.Controller;

import java.util.List;

import it.ariadne.dao.Dao;

public abstract class Controller<K, T> {

	private Dao<K, T> dao;

	public Controller(Dao<K, T> dao) {

		this.dao = dao;
	}

	public void addRecord(T t) {

		dao.addRecord(t);

	}

	public List<T> getAllRecords() {

		return dao.getAllRecords();

	}

	public T getRecord(K k) {

		return dao.getRecord(k);
	}

	public void deleteRecord(T t) {

		dao.deleteRecord(t);

	}

	public void updateRecord(T t) {

		dao.updateRecord(t);

	}
	
	


}

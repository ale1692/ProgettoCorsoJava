package it.ariadne.dao;

import java.util.List;

public interface Dao<K, T> {

	public void addRecord(T t);

	public List<T> getAllRecords();

	public T getRecord(K k);

	public void deleteRecord(T t);

	public void updateRecord(T t);

}

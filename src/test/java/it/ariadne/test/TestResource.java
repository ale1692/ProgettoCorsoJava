package it.ariadne.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.dao.ResourceDaoImpl;
import it.ariadne.resources.BrandCar;
import it.ariadne.resources.BrandPc;
import it.ariadne.resources.Car;
import it.ariadne.resources.Laptop;
import it.ariadne.resources.Resource;
import it.ariadne.resources.Room;

public class TestResource {

	private ResourceDaoImpl resourceImpl;
	private List<Resource> lista;
	private Car r1;
	private Laptop r2;
	private Room r3;

	@Before
	public void setup() {

		resourceImpl = new ResourceDaoImpl();
		lista = resourceImpl.getAllRecords();
		r1 = new Car("CAR01", true, "ABFDER", 4, BrandCar.FIAT);
		r2 = new Laptop("PC001", true, 8, 4, BrandPc.LENOVO);
		r3 = new Room("RA1", true, 20, "Sala A1");
		resourceImpl.addRecord(r1);
	}

	@Test
	public void testAddResource() {

		resourceImpl.addRecord(r2);
		resourceImpl.addRecord(r3);
		lista = resourceImpl.getAllRecords();
		Assert.assertEquals("Utenti memorizzati nel DB", lista.size(), 3);
	}

	@Test
	public void testUpdateResource() {
		
		resourceImpl.addRecord(r3);
		r3.setCapacity(40);
		lista = resourceImpl.getAllRecords();
		Assert.assertEquals("Utente modificato", lista.size(), 2);
		
		Room roomTest= (Room) resourceImpl.getRecord(r3.getCode());
		Assert.assertEquals("Utente modificato", roomTest.getCapacity(), 40);
	}

	@Test
	public void testDeleteResource() {

		resourceImpl.deleteRecord(r1);
		resourceImpl.deleteRecord(r2);
		lista = resourceImpl.getAllRecords();

		Assert.assertEquals("Utente eliminato", lista.size(), 0);
	}

}

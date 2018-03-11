package it.ariadne.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.controller.ResourceController;
import it.ariadne.dao.ResourceDaoImpl;
import it.ariadne.model.resource.BrandCar;
import it.ariadne.model.resource.BrandPc;
import it.ariadne.model.resource.Car;
import it.ariadne.model.resource.Laptop;
import it.ariadne.model.resource.Room;

public class TestResource {

	private ResourceController<Car> carController;
	private ResourceController<Room> roomController;
	private ResourceController<Laptop> laptopController;
	private List<Car> listaCar;
	private List<Room> listaRoom;
	private List<Laptop> listaLaptop;
	private Car car1;
	private Car car2;
	private Laptop laptop1;
	private Laptop laptop2;
	private Room room1;
	private Room room2;

	@Before
	public void setup() {

		carController = new ResourceController<Car>(new ResourceDaoImpl<Car>());
		roomController = new ResourceController<Room>(new ResourceDaoImpl<Room>());
		laptopController = new ResourceController<Laptop>(new ResourceDaoImpl<Laptop>());

		car1 = new Car("CAR01", "ABFDER", 4, BrandCar.FIAT);
		car2 = new Car("CAR02", "RBDGER", 5, BrandCar.MERCEDES);
		laptop1 = new Laptop("PC001", 8, 4, BrandPc.LENOVO);
		laptop2 = new Laptop("PC002", 16, 6, BrandPc.SAMSUNG);
		room1 = new Room("RA1", 20, "Sala A1");
		room2 = new Room("RA2", 80, "Sala A2");
	}

	@Test
	public void testAddResource() {

		carController.addRecord(car1);
		carController.addRecord(car2);
		listaCar = carController.getAllRecords();
		roomController.addRecord(room1);
		roomController.addRecord(room2);
		listaRoom = roomController.getAllRecords();
		laptopController.addRecord(laptop1);
		laptopController.addRecord(laptop2);
		listaLaptop = laptopController.getAllRecords();

		Assert.assertEquals(listaCar.size() + listaLaptop.size() + listaRoom.size(), 6);
	}

	@Test
	public void testUpdateResource() {

		roomController.addRecord(room1);
		room1.setCapacity(25);
		roomController.updateRecord(room1);
		listaRoom = roomController.getAllRecords();
		Assert.assertEquals("Utente modificato", listaRoom.size(), 1);

		Room roomTest = (Room) roomController.getRecord(room1.getCode());
		Assert.assertEquals(roomTest.getCapacity(), 25);
	}

	@Test
	public void testDeleteResource() {

		laptopController.addRecord(laptop1);
		laptopController.deleteRecord(laptop1);
		laptopController.deleteRecord(laptop2);
		listaLaptop = laptopController.getAllRecords();

		Assert.assertEquals(listaLaptop.size(), 0);
	}

	@Test
	public void testSearchCondition() {

		roomController.addRecord(room1);
		roomController.addRecord(room2);
		Assert.assertEquals(roomController.getResourceFiltered(30).size(), 1);

	}

	@Test
	public void testAvaibleResources() {

		roomController.addRecord(room1);
		roomController.addRecord(room2);
		room2.setAvailable(false);
		roomController.updateRecord(room2);

		Assert.assertEquals(roomController.getResourcesAvaible().size(), 1);

	}

}

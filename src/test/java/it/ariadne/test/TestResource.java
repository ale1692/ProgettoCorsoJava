package it.ariadne.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.controller.ResourceController;
import it.ariadne.dao.ResourceDaoImpl;
import it.ariadne.resource.BrandCar;
import it.ariadne.resource.BrandPc;
import it.ariadne.resource.Car;
import it.ariadne.resource.Laptop;
import it.ariadne.resource.Room;

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

		car1 = new Car("CAR01", true, "ABFDER", 4, BrandCar.FIAT);
		car2 = new Car("CAR02", false, "RBDGER", 5, BrandCar.MERCEDES);
		laptop1 = new Laptop("PC001", true, 8, 4, BrandPc.LENOVO);
		laptop2 = new Laptop("PC002", false, 16, 6, BrandPc.SAMSUNG);
		room1 = new Room("RA1", true, 20, "Sala A1");
		room2 = new Room("RA2", false, 80, "Sala A2");
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

		Assert.assertEquals("Risorse memorizzate nel DB", listaCar.size() + listaLaptop.size() + listaRoom.size(), 6);
	}

	 @Test
	 public void testUpdateResource() {
	
	roomController.addRecord(room1);
	room1.setCapacity(25);
	roomController.updateRecord(room1);
	listaRoom = roomController.getAllRecords();
	Assert.assertEquals("Utente modificato", listaRoom.size(), 1);
	
	 Room roomTest= (Room) roomController.getRecord(room1.getCode());
	 Assert.assertEquals("Risorsa modificata", roomTest.getCapacity(), 25);
	 }
	
	@Test
	public void testDeleteResource() {

		laptopController.addRecord(laptop1);
		laptopController.deleteRecord(laptop1);
		laptopController.deleteRecord(laptop2);
		listaLaptop = laptopController.getAllRecords();

		Assert.assertEquals("Risorsa eliminata", listaLaptop.size(), 0);
	}

	@Test
	public void testSearch() {

		roomController.addRecord(room1);
		roomController.addRecord(room2);
		Assert.assertEquals("Filtraggio risorse", roomController.getResourceFiltered(30).size(), 1);

	}

}

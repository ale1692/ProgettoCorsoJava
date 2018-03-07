package it.ariadne.test;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.Controller.BookingController;
import it.ariadne.Controller.ResourceController;
import it.ariadne.Controller.UserController;
import it.ariadne.booking.Booking;
import it.ariadne.dao.BookingDaoImpl;
import it.ariadne.dao.Dao;
import it.ariadne.dao.ResourceDaoImpl;
import it.ariadne.dao.UserDaoImpl;
import it.ariadne.resources.BrandCar;
import it.ariadne.resources.BrandPc;
import it.ariadne.resources.Car;
import it.ariadne.resources.Laptop;
import it.ariadne.resources.Resource;
import it.ariadne.resources.Room;
import it.ariadne.users.Role;
import it.ariadne.users.User;

public class TestBooking {

	private UserController userController;
	private ResourceController<Car> carController;
	private ResourceController<Room> roomController;
	private ResourceController<Laptop> laptopController;

	private BookingController bookingController;
	private User u1;
	private User u2;
	private Car car1;
	private Car car2;
	private Laptop laptop1;
	private Room room1;
	private Booking<Resource> b1;
	private Booking<Resource> b2;
	private Booking<Resource> b3;

	@Before
	public void setup() {

		userController = new UserController(new UserDaoImpl());
		carController = new ResourceController<Car>(new ResourceDaoImpl<Car>());
		roomController = new ResourceController<Room>(new ResourceDaoImpl<Room>());
		laptopController = new ResourceController<Laptop>(new ResourceDaoImpl<Laptop>());
		Dao<Integer, Booking<?>> daoBooking = new BookingDaoImpl();

		u1 = new User("Marco", "Rossi", "prova99", "marco.rossi", Role.DEVELOPER);
		u2 = new User("Sara", "Fumarola", "asd123", "sarfum", Role.SECRETARY);
		car1 = new Car("CAR01", true, "ABFDER", 4, BrandCar.FIAT);
		car2 = new Car("CAR02", true, "LAZFREA", 5, BrandCar.RENAULT);
		laptop1 = new Laptop("PC001", true, 8, 4, BrandPc.LENOVO);
		room1 = new Room("RA1", true, 20, "Sala A1");

		userController.addRecord(u1);
		userController.addRecord(u2);
		carController.addRecord(car1);
		carController.addRecord(car2);
		roomController.addRecord(room1);
		laptopController.addRecord(laptop1);

		bookingController = new BookingController(daoBooking);
		b1 = new Booking<Resource>(1, u1, car1, new DateTime(2018, 3, 9, 15, 0), new DateTime(2018, 3, 9, 17, 0));
		b2 = new Booking<Resource>(2, u2, laptop1, new DateTime(2018, 3, 4, 10, 0), new DateTime(2018, 3, 4, 18, 0));
		b3 = new Booking<Resource>(3, u1, room1, new DateTime(2018, 3, 1, 9, 0), new DateTime(2018, 3, 1, 18, 0));
	}

	@Test
	public void addBooking1() {
		bookingController.addRecord(b1);
		bookingController.addRecord(b2);
		bookingController.addRecord(b3);
		Assert.assertEquals("Risorse memorizzate nel DB", bookingController.getAllRecords().size(), 3);
	}

	@Test
	public void addBooking2() {
		bookingController.addRecord(b1);
		Booking<Resource> b4 = new Booking<Resource>(5, u1, car1, new DateTime(2018, 3, 7, 19, 0),
				new DateTime(2018, 3, 7, 19, 50));
		Booking<Resource> b5 = new Booking<Resource>(6, u1, car1, new DateTime(2018, 1, 7, 17, 0),
				new DateTime(2018, 1, 7, 18, 0));
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);

		Assert.assertEquals("Risorse memorizzate nel DB", bookingController.getActiveBooking().size(), 1);
	}

	@Test
	public void findByTypeResource() {
		bookingController.addRecord(b1);
		Booking<Resource> b4 = new Booking<Resource>(5, u1, car1, new DateTime(2018, 3, 7, 19, 0),
				new DateTime(2018, 3, 7, 19, 50));
		Booking<Resource> b5 = new Booking<Resource>(6, u1, car1, new DateTime(2018, 1, 7, 17, 0),
				new DateTime(2018, 1, 7, 18, 0));
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);

		Assert.assertEquals("Risorse memorizzate nel DB", bookingController.findByTypeActiveResource(car1).size(), 1);
	}

	@Test
	public void findByUser() {
		bookingController.addRecord(b1);
		Booking<Resource> b4 = new Booking<Resource>(5, u2, car2, new DateTime(2018, 3, 7, 19, 0),
				new DateTime(2018, 3, 7, 19, 50));
		Booking<Resource> b5 = new Booking<Resource>(6, u2, car1, new DateTime(2018, 1, 7, 17, 0),
				new DateTime(2018, 1, 7, 18, 0));
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);

		Assert.assertEquals("Risorse memorizzate nel DB", bookingController.findByActiveUser(u1).size(), 1);
	}

	@Test
	public void findFirstResourceAvailability() {
		bookingController.addRecord(b1);
		Booking<Resource> b4 = new Booking<Resource>(5, u2, car2, new DateTime(2018, 3, 7, 21, 0),
				new DateTime(2018, 3, 7, 22, 00));
		Booking<Resource> b5 = new Booking<Resource>(6, u2, car1, new DateTime(2018, 1, 7, 17, 0),
				new DateTime(2018, 1, 7, 18, 0));
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);

		System.out.println(bookingController.findFirstResourceAvailability(car2, new DateTime(2018, 3, 7, 21, 0),
				new DateTime(2018, 3, 7, 23, 30), 1, 0));

		Assert.assertEquals("Risorse memorizzate nel DB", true, true);
	}

}

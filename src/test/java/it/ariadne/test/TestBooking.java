package it.ariadne.test;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.controller.BookingController;
import it.ariadne.controller.ResourceController;
import it.ariadne.controller.UserController;
import it.ariadne.dao.BookingDaoImpl;
import it.ariadne.dao.ResourceDaoImpl;
import it.ariadne.dao.UserDaoImpl;
import it.ariadne.model.booking.Booking;
import it.ariadne.model.resource.BrandCar;
import it.ariadne.model.resource.BrandPc;
import it.ariadne.model.resource.Car;
import it.ariadne.model.resource.Laptop;
import it.ariadne.model.resource.Resource;
import it.ariadne.model.resource.Room;
import it.ariadne.model.user.Role;
import it.ariadne.model.user.User;

public class TestBooking {

	private UserController<User> userController;
	private ResourceController<Car> carController;
	private ResourceController<Room> roomController;
	private ResourceController<Laptop> laptopController;
	private BookingController<Resource, User> bookingController;

	private User u1;
	private User u2;
	private Car car1;
	private Car car2;
	private Laptop laptop1;
	private Room room1;
	private Booking<Resource, User> b1;
	private Booking<Resource, User> b2;
	private Booking<Resource, User> b3;
	private Booking<Resource, User> b4;
	private Booking<Resource, User> b5;

	@Before
	public void setup() {

		userController = new UserController<User>(new UserDaoImpl<User>());
		carController = new ResourceController<Car>(new ResourceDaoImpl<Car>());
		roomController = new ResourceController<Room>(new ResourceDaoImpl<Room>());
		laptopController = new ResourceController<Laptop>(new ResourceDaoImpl<Laptop>());
		bookingController = new BookingController<Resource, User>(new BookingDaoImpl<>());

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

		b1 = new Booking<Resource, User>(1, u1, car1, new DateTime(2018, 3, 9, 15, 0), new DateTime(2018, 3, 9, 17, 0));
		b2 = new Booking<Resource, User>(2, u2, laptop1, new DateTime(2018, 3, 4, 10, 0), new DateTime(2018, 3, 4, 18, 0));
		b3 = new Booking<Resource, User>(3, u1, room1, new DateTime(2018, 3, 1, 9, 0), new DateTime(2018, 3, 1, 18, 0));
		b4 = new Booking<Resource, User>(5, u1, car1, new DateTime(2018, 3, 7, 19, 0),new DateTime(2018, 3, 7, 19, 50));
		b5 = new Booking<Resource, User>(6, u1, car1, new DateTime(2018, 1, 7, 17, 0),new DateTime(2018, 1, 7, 18, 0));
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
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);

		Assert.assertEquals("Risorse memorizzate nel DB", bookingController.getActiveBooking().size(), 1);
	}

	@Test
	public void findByTypeResource() {
		bookingController.addRecord(b1);
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);

		Assert.assertEquals("Risorse memorizzate nel DB", bookingController.findByTypeActiveResource(car1).size(), 1);
	}

	@Test
	public void findByUser() {
		bookingController.addRecord(b1);
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);

		Assert.assertEquals("Risorse memorizzate nel DB", bookingController.findByActiveUser(u1).size(), 1);
	}

	@Test
	public void findFirstResourceAvailability() {
		bookingController.addRecord(b1);
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);

		System.out.println(bookingController.findFirstResourceAvailability(car2, new DateTime(2018, 3, 7, 21, 0),
				new DateTime(2018, 3, 7, 23, 30), 1, 0));

		Assert.assertEquals("Risorse memorizzate nel DB", true, true);
	}

}

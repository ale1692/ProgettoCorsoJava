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
import it.ariadne.dao.CarDaoImpl;
import it.ariadne.dao.Dao;
import it.ariadne.dao.LaptopDaoImpl;
import it.ariadne.dao.RoomDaoImpl;
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

	private UserController<User> userController;
	private ResourceController<Car> carController;
	private ResourceController<Room> roomController;
	private ResourceController<Laptop> laptopController;

	private BookingController<Booking<Resource>> bookingController;
	private User u1;
	private User u2;
	private Car car1;
	private Laptop laptop1;
	private Room room1;
	private Booking<Resource> b1;
	private Booking<Resource> b2;
	private Booking<Resource> b3;

	@Before
	public void setup() {

		userController = new UserController<User>(new UserDaoImpl());
		carController = new ResourceController<Car>(new CarDaoImpl());
		roomController = new ResourceController<Room>(new RoomDaoImpl());
		laptopController = new ResourceController<>(new LaptopDaoImpl());
		Dao<Integer, Booking<?>> daoBooking = new BookingDaoImpl();

		u1 = new User("Marco", "Rossi", "prova99", "marco.rossi", Role.DEVELOPER);
		u2 = new User("Sara", "Fumarola", "asd123", "sarfum", Role.SECRETARY);
		car1 = new Car("CAR01", true, "ABFDER", 4, BrandCar.FIAT);
		laptop1 = new Laptop("PC001", true, 8, 4, BrandPc.LENOVO);
		room1 = new Room("RA1", true, 20, "Sala A1");

		userController.addRecord(u1);
		userController.addRecord(u2);
		carController.addRecord(car1);
		roomController.addRecord(room1);
		laptopController.addRecord(laptop1);

		bookingController = new BookingController<Booking<Resource>>(daoBooking);
		b1 = new Booking<Resource>(1, u1, car1, new DateTime(2018, 3, 7, 15, 0), new DateTime(2018, 3, 7, 17, 0));
		b2 = new Booking<Resource>(2, u2, laptop1, new DateTime(2018, 3, 4, 10, 0), new DateTime(2018, 3, 4, 18, 0));
		b3 = new Booking<Resource>(3, u1, room1, new DateTime(2018, 3, 1, 9, 0), new DateTime(2018, 3, 1, 18, 0));
	}

//	@Test
//	public void addBooking1() {
//		bookingController.addBooking(b1);
//		bookingController.addBooking(b2);
//		bookingController.addBooking(b3);
//		Assert.assertEquals("Risorse memorizzate nel DB", bookingController.getAllBookings().size(), 3);
//	}
	
	@Test
	public void addBooking2() {
		bookingController.addBooking(b1);
//		Booking<Resource> b4 = new Booking<Resource>(5, u1, car1, new DateTime(2018, 3, 7, 17, 0), new DateTime(2018, 3, 7, 18, 0));
//		Booking<Resource> b5 = new Booking<Resource>(6, u1, car1, new DateTime(2018, 1, 7, 17, 0), new DateTime(2018, 1, 7, 18, 0));
		bookingController.addBooking(b2);
		bookingController.addBooking(b3);
		Assert.assertEquals("Risorse memorizzate nel DB", bookingController.getAllBookings().size(), 3);
	}

}

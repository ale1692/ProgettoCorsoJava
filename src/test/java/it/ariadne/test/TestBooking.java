package it.ariadne.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import it.ariadne.util.ResourceUtil;

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
	private Car car3;
	private Laptop laptop1;
	private Room room1;
	private Booking<Resource, User> b1;
	private Booking<Resource, User> b2;
	private Booking<Resource, User> b3;
	private Booking<Resource, User> b4;
	private Booking<Resource, User> b5;
	private Booking<Resource, User> b6;
	private DateTimeFormatter df;
	private ResourceUtil resourceUtil;

	@Before
	public void setup() {

		resourceUtil = ResourceUtil.getInstance();
		df = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
		userController = new UserController<User>(new UserDaoImpl<User>());
		carController = new ResourceController<Car>(new ResourceDaoImpl<Car>());
		roomController = new ResourceController<Room>(new ResourceDaoImpl<Room>());
		laptopController = new ResourceController<Laptop>(new ResourceDaoImpl<Laptop>());
		bookingController = new BookingController<Resource, User>(new BookingDaoImpl<>());

		u1 = new User("Marco", "Rossi", "prova99", "marco.rossi", Role.DEVELOPER);
		u2 = new User("Sara", "Fumarola", "asd123", "sarfum", Role.SECRETARY);
		car1 = new Car("CAR01", "ABFDER", 4, BrandCar.FIAT);
		car2 = new Car("CAR02", "LAZFREA", 5, BrandCar.RENAULT);
		car3 = new Car("CAR03", "LAASAEA", 5, BrandCar.FIAT);
		laptop1 = new Laptop("PC001", 8, 4, BrandPc.LENOVO);
		room1 = new Room("RA1", 20, "Sala A1");

		userController.addRecord(u1);
		userController.addRecord(u2);
		carController.addRecord(car1);
		carController.addRecord(car2);
		roomController.addRecord(room1);
		laptopController.addRecord(laptop1);

		b1 = new Booking<Resource, User>(1, u1, car1, new DateTime(2018, 3, 12, 15, 0),
				new DateTime(2018, 3, 12, 17, 0));
		b2 = new Booking<Resource, User>(2, u2, laptop1, new DateTime(2018, 3, 4, 10, 0),
				new DateTime(2018, 3, 4, 18, 0));
		b3 = new Booking<Resource, User>(3, u1, room1, new DateTime(2018, 3, 1, 9, 0), new DateTime(2018, 3, 1, 18, 0));
		b4 = new Booking<Resource, User>(5, u1, car1, new DateTime(2018, 3, 12, 19, 0),
				new DateTime(2018, 3, 12, 19, 50));
		b5 = new Booking<Resource, User>(6, u1, car2, new DateTime(2018, 3, 12, 9, 0),
				new DateTime(2018, 3, 12, 18, 0));
		b6 = new Booking<Resource, User>(7, u1, car3, new DateTime(2018, 3, 12, 9, 0),
				new DateTime(2018, 3, 12, 17, 0));
	}

	@Test
	public void testAddBooking() {

		bookingController.addRecord(b1);
		bookingController.addRecord(b2);
		bookingController.addRecord(b3);
		Assert.assertEquals(bookingController.getAllRecords().size(), 3);
	}

	@Test
	public void testAddAvaibleResourceBooking() {

		Laptop laptop2 = new Laptop("PC002", 16, 6, BrandPc.SONY);
		laptop2.setAvailable(false);
		laptopController.updateRecord(laptop2);
		// L'aggiunta di una nuova prenotazione non deve andare a buon fine in quanto la
		// risorsa non è disponibile
		Booking<Resource, User> b7 = new Booking<Resource, User>(7, u1, laptop2, new DateTime(2018, 3, 12, 9, 0),
				new DateTime(2018, 3, 12, 17, 0));
		bookingController.addRecord(b7);
		Assert.assertEquals(bookingController.getAllRecords().size(), 0);
	}

	@Test
	public void testGetActiveBooking() {

		bookingController.addRecord(b1);
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);
		Assert.assertEquals(bookingController.getActiveBooking().size(), 3);
	}

	@Test
	public void testFindByTypeResource() {

		bookingController.addRecord(b1);
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);
		String typeRisorsa = resourceUtil.getTypeResource("Macchina Aziendale");
		Assert.assertEquals(bookingController.findByTypeResource(typeRisorsa).size(), 3);
	}

	@Test
	public void testFindByUser() {

		bookingController.addRecord(b1);
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);
		Assert.assertEquals(bookingController.findActiveByUser(u1).size(), 3);
	}

	@Test
	public void testFindFirstResourceAvailability() {

		bookingController.addRecord(b1);
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);
		String startD = "12/03/2018 16:00";
		String endD = "12/03/2018 23:00";
		DateTime startDate = df.parseDateTime(startD);
		DateTime endDate = df.parseDateTime(endD);
		DateTime result = bookingController.findFirstResourceAvailability(car2, startDate, endDate, 8, 0);
		Assert.assertEquals(result, null);
	}

	@Test
	public void testFindFirstResourceByConstraint() {

		bookingController.addRecord(b1);
		bookingController.addRecord(b4);
		bookingController.addRecord(b5);
		bookingController.addRecord(b6);
		String startD = "12/03/2018 12:00";
		String endD = "12/03/2018 23:30";
		DateTime startDate = df.parseDateTime(startD);
		DateTime endDate = df.parseDateTime(endD);
		String typeRisorsa = resourceUtil.getTypeResource("Macchina Aziendale");
		Resource r = bookingController.findResourceAvailabilityByConstraint(typeRisorsa, startDate, endDate, 1, 0, 5);
		Assert.assertEquals("Risorse memorizzate nel DB", r.getCode(), "CAR03");

	}

	@Test
	public void testPenaltyUser() {

		Booking<Resource, User> b7 = new Booking<Resource, User>(8, u1, car3, new DateTime(2018, 3, 11, 9, 0),
				new DateTime(2018, 3, 11, 17, 0));
		bookingController.addRecord(b7);
		String dateTest = "25/03/2018 12:00";
		DateTime deliveryDate = df.parseDateTime(dateTest);
		userController.updatePenaltyUser(bookingController.setPenalty(b7, deliveryDate), b7.getUtente());
		Assert.assertEquals(b7.getUtente().isPenality(), true);
		Assert.assertEquals(userController.getAllUserPenalty().size(), 1);

	}

	@Test
	public void testBookingValidity() {

		Booking<Resource, User> b7 = new Booking<Resource, User>(8, u1, car3, new DateTime(2018, 3, 11, 15, 0),
				new DateTime(2018, 3, 11, 12, 0));
		bookingController.addRecord(b7);
		Assert.assertEquals(bookingController.getAllRecords().size(), 0);

	}

}

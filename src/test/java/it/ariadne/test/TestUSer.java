package it.ariadne.test;

import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.dao.UserDaoImpl;
import it.ariadne.users.User;

public class TestUSer {

	private UserDaoImpl userImpl;
	private TreeMap<String, User> utenti;
	private User u1;
	private User u2;
	private User u3;

	@Before
	public void setup() {
		userImpl = new UserDaoImpl();
		utenti = userImpl.getAllUSers();
		u1 = new User("Marco", "Rossi", "MRCRSS68D07F205B", "prova99", "marco.rossi", Role.DEVELOPER);
		u2 = new User("Marco", "Rossi", "MRCRSS68D07F205B", "prova99", "marco.rossi", Role.MARKETING);
		u3 = new User("Sara", "Fumarola", "SRAFRL51D42C351U", "asd123", "sarfum", Role.SECRETARY);

	}

	@Test
	public void testAddUser() {

		userImpl.addUser(u1);
		userImpl.addUser(u3);
		Assert.assertEquals("Utenti memorizzati nel DB", utenti.size(), 2);
	}

	@Test
	public void testUpdateUser() {

		testAddUser();
		userImpl.updateUser(u2);
		Assert.assertEquals("Utente modificato", utenti.get(u2.getFiscalCode()).getRole(), u2.getRole());
	}

}

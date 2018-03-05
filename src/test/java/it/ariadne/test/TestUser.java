package it.ariadne.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.dao.UserDaoImpl;
import it.ariadne.users.Role;
import it.ariadne.users.User;

public class TestUser {

	private UserDaoImpl userImpl;
	private List<User> lista;
	private User u1;
	private User u2;
	private User u3;

	@Before
	public void setup() {

		userImpl = new UserDaoImpl();
		lista = userImpl.getAllRecords();
		u1 = new User("Marco", "Rossi", "MRCRSS68D07F205B", "prova99", "marco.rossi", Role.DEVELOPER);
		u2 = new User("Marco", "Rossi", "MRCRSS68D07F205B", "prova99", "marco.rossi", Role.MARKETING);
		u3 = new User("Sara", "Fumarola", "SRAFRL51D42C351U", "asd123", "sarfum", Role.SECRETARY);
		userImpl.addRecord(u1);

	}

	@Test
	public void testAddUser() {

		userImpl.addRecord(u3);
		lista = userImpl.getAllRecords();
		Assert.assertEquals("Utenti memorizzati nel DB", lista.size(), 2);
	}

	@Test
	public void testUpdateUser() {

		userImpl.updateRecord(u2);
		lista = userImpl.getAllRecords();
		Assert.assertEquals("Utente modificato", lista.size(), 1);

		Assert.assertEquals("Utente modificato", userImpl.getRecord(u2.getFiscalCode()).getRole(), u2.getRole());
	}

	@Test
	public void testDeleteUser() {

		lista = userImpl.getAllRecords();
		userImpl.deleteRecord(u1);
		lista = userImpl.getAllRecords();
		
		Assert.assertEquals("Utente eliminato",lista.size(),0);
	}

}

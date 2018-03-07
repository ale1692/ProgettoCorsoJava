package it.ariadne.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.Controller.UserController;
import it.ariadne.dao.UserDaoImpl;
import it.ariadne.users.Admin;
import it.ariadne.users.Role;
import it.ariadne.users.User;

public class TestUser {

	private UserController userController;
	private List<User> lista;
	private User u1;
	private User u2;
	private User u3;

	@Before
	public void setup() {

		userController = new UserController(new UserDaoImpl());
		u1 = new User("Marco", "Rossi", "prova99", "marco.rossi", Role.DEVELOPER);
		u2 = new User("Marco", "Rossi", "prova99", "marco.rossi", Role.MARKETING);
		u3 = new User("Sara", "Fumarola", "asd123", "sarfum", Role.SECRETARY);
		userController.addRecord(u1);

	}

	@Test
	public void testAddUser() {

		userController.addRecord(u3);
		lista = userController.getAllRecords();
		Assert.assertEquals("Utenti memorizzati nel DB", lista.size(), 2);
	}

	@Test
	public void testUpdateUser() {

		userController.updateRecord(u2);
		lista = userController.getAllRecords();
		Assert.assertEquals("Utente modificato", lista.size(), 1);

		Assert.assertEquals("Utente modificato", userController.getRecord(u2.getUserName()).getRole(), u2.getRole());
	}

	@Test
	public void testDeleteUser() {

		lista = userController.getAllRecords();
		userController.deleteRecord(u1);
		lista = userController.getAllRecords();
		
		Assert.assertEquals("Utente eliminato",lista.size(),0);
	}
	
	@Test
	public void testAdmin() {
		
		Admin a1=new Admin("Angelo","Sala","ang.12","angelo12", Role.SECRETARY);
		Assert.assertEquals("Test admin",a1.isAdmin(), true);
	}
	
	public void testLogin() {
		
		User userTest=userController.performLogin("ang.12", "angelo12");
		
		Assert.assertEquals("Test login",userTest.getName(), "Angelo");
	}
	

}

package it.ariadne.util;

import it.ariadne.model.resource.Car;
import it.ariadne.model.resource.Laptop;
import it.ariadne.model.resource.Room;

public class ResourceUtil {

	private static ResourceUtil instance = null;

	protected ResourceUtil() {
	}

	public static ResourceUtil getInstance() {
		if (instance == null) {
			instance = new ResourceUtil();
		}
		return instance;
	}

	public String getTypeResource(String resourceType) {

		if (resourceType.equalsIgnoreCase("Sala Riunioni")) {
			
			return Room.class.getSimpleName();

		}

		else if (resourceType.equalsIgnoreCase("Laptop")) {

			return Laptop.class.getSimpleName();

		}

		else if (resourceType.equalsIgnoreCase("Macchina Aziendale")) {
			
			return Car.class.getSimpleName();

		}

		else {
			return null;
		}
	}
}

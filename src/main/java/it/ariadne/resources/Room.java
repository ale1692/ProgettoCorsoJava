package it.ariadne.resources;

public class Room extends Resource {

	private int capacity;
	private String name;

	public Room(String code, boolean available, int capacity, String name) {
		super(code, available);
		this.capacity = capacity;
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean searchByConstraint(int condition) {

		if (this.getCapacity() >= condition) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {

		return "Room ID: " + this.getCode() + " Capacity: " + this.getCapacity() + " Name: "
				+ this.getName();
	}

	

}

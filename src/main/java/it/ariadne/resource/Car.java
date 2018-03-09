package it.ariadne.resource;

public class Car extends Resource {

	private String numberPlate;
	private int numberSeat;
	private BrandCar brand;

	public Car(String code, boolean available, String numberPlate, int numberSeat, BrandCar brand) {
		super(code, available);
		this.numberPlate = numberPlate;
		this.numberSeat = numberSeat;
		this.brand = brand;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public int getNumberSeat() {
		return numberSeat;
	}

	public void setNumberSeat(int numberSeat) {
		this.numberSeat = numberSeat;
	}

	@Override
	public String toString() {

		return "Car ID: " + this.getCode() + " Numberplate: " + this.getNumberPlate() + " Number of seats: "
				+ this.getNumberSeat();
	}

	public BrandCar getBrand() {
		return brand;
	}

	public void setBrand(BrandCar brand) {
		this.brand = brand;
	}

	@Override
	public boolean searchByConstraint(int condition) {

		if (this.getNumberSeat() >= condition) {
			return true;
		} else {
			return false;
		}
	}
}

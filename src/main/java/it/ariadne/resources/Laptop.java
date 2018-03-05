package it.ariadne.resources;

public class Laptop extends Resource {

	private int ramCapacity;
	private int numberCores;
	private BrandPc brand;

	public Laptop(String code, boolean available, int ramCapacity, int numberCores, BrandPc brand) {
		super(code, available);
		this.ramCapacity = ramCapacity;
		this.numberCores = numberCores;
		this.brand = brand;
	}

	public int getCapienzaRam() {
		return ramCapacity;
	}

	public void setCapienzaRam(int ramCapacity) {
		this.ramCapacity = ramCapacity;
	}

	public int getNumberCores() {
		return numberCores;
	}

	public void setNumberCores(int numberCores) {
		this.numberCores = numberCores;
	}

	public BrandPc getBrand() {
		return brand;
	}

	public void setBrand(BrandPc brand) {
		this.brand = brand;
	}

	@Override
	public boolean searchByConstraint(int condition) {

		if (this.getCapienzaRam() >= condition) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {

		return "Laptop ID: " + this.getCode() + " Ram: " + this.getCapienzaRam() + " Cores: " + this.getNumberCores()
				+ " Brand: " + this.getBrand();
	}

}

package it.ariadne.resources;

public class Laptop extends Resource {

	private int ramCapacity;
	private int hdCapacity;
	private BrandPc brand;

	public Laptop(int code, boolean available, int ramCapacity, int hdCapacity, BrandPc brand) {
		super(code, available);
		this.ramCapacity = ramCapacity;
		this.hdCapacity = hdCapacity;
		this.brand = brand;
	}

	public int getCapienzaRam() {
		return ramCapacity;
	}

	public void setCapienzaRam(int ramCapacity) {
		this.ramCapacity = ramCapacity;
	}

	public int getCapienzaHd() {
		return hdCapacity;
	}

	public void setCapienzaHd(int hdCapacity) {
		this.hdCapacity = hdCapacity;
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

		return "Laptop ID: " + this.getCode() + " Ram: " + this.getCapienzaRam() + " HD: " + this.getCapienzaHd()
				+ " Brand: " + this.getBrand();
	}

}

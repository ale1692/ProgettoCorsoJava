package it.ariadne.user;

public enum Role {

	MARKETING("Marketing"), DEVELOPER("Software Developer"), OWNER("Company owner"), SECRETARY("Secretary");

	private String mansione;

	private Role(String name) {
		this.mansione = name;
	}

	@Override
	public String toString() {
		return "Mansione: " + mansione;
	}

	public String getBrandname() {
		return mansione;
	}

	public void setBrandname(String name) {
		this.mansione = name;
	}

}

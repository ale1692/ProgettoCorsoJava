package it.ariadne.resources;

public abstract class Resource {

	private String code;
	private boolean available;

	public Resource(String code, boolean available) {
		this.code = code;
		setAvailable(true);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public abstract boolean searchByConstraint(int condition);

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public boolean equals(Object o) {

		if (o.getClass().equals(this.getClass())) {
			Resource r = (Resource) o;

			if (this.getCode() == r.getCode()) {
				return true;
			} else {
				return false;
			}

		}

		else {
			return false;
		}
	}

}

package it.ariadne.resources;

public abstract class Resource {

	private int code;
	private boolean available;

	public Resource(int code, boolean available) {
		super();
		this.code = code;
		this.available = available;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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

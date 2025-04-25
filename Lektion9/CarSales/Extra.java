package CarSales;

public abstract class Extra extends Car {

	private double price;
	private String description;
	private Car wrappedObj;

	public Extra(double price, String description, Car wrappedObj) {
		this.wrappedObj = wrappedObj;
		this.price = price;
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public Car getWrappedObj() {
		return wrappedObj;
	}

}

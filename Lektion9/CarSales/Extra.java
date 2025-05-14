package CarSales;

public abstract class Extra extends Car {

	private String description;
	private Car wrappedObj;

	public Extra(double price, String model, Car wrappedObj) {
		super(price, model);
		this.wrappedObj = wrappedObj;
		generateDescription();
	}

	private void generateDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append(wrappedObj.getDescription());
		sb.append("\n");
		sb.append(this.toString());
		this.description = sb.toString();
	}

	public double getPrice() {
		return super.getPrice();
	}

	public String getDescription() {
		return description;
	}

	public Car getWrappedObj() {
		return wrappedObj;
	}
}

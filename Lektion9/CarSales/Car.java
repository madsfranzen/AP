package CarSales;

public abstract class Car {

	private double price;
	private String model;

	public Car(double price, String model){
		this.price = price;
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public String getModel() {
		return model;
	}

	@Override
	public String toString() {
		String str = getModel() + " : $" + getPrice();
		return str;
	}

	public String getDescription(){
		return toString();
	}

}

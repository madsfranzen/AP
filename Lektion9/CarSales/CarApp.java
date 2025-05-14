package CarSales;

public class CarApp {
	public static void main(String[] args) {
		System.out.println(" --- HELLO CAR APP --- ");

		Car kuga1 = new Kuga();
		kuga1 = new LeatherSeats(kuga1);

		System.out.println(kuga1.getDescription());
	}
}

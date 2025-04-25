public class AnvendCounter {
	public static void main(String[] args) {

		Counter c1 = Counter.getInstance();
		System.out.println("C1: " + c1.getValue());
		c1.count();
		System.out.println("C1: " + c1.getValue());
		c1.count();
		System.out.println("C1: " + c1.getValue());
		c1.count();
		System.out.println("C1: " + c1.getValue());

		Counter c2 = Counter.getInstance();
		System.out.println("C2: " + c2.getValue());
	}
}

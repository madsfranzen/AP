public class Counter {
	private static Counter instance;
	private int value;

	private Counter() {
		value = 0;
	}

	public static Counter getInstance() {
		if (instance == null) {
			instance = new Counter();
		}
		return instance;
	}

	public void count() {
		value++;
	}

	public void times2() {
		value = value * 2;
	}

	public void zero() {
		value = 0;
	}

	public int getValue() {
		return value;
	}
}

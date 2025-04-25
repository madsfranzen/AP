public enum CounterEnum {
	COUNTER;

	private int value;

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

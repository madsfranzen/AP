package composite_shapes;

public class Rectangle extends Shape {
	private double height;
	private double width;

	public Rectangle(double height, double width, String name) {
		this.height = height;
		this.width = width;
		super.setName(name);
	}

	public double getArea() {
		return width * height;
	}
}

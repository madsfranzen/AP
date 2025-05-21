package composite_shapes;

public class App {
	public static void main(String[] args) {
		System.out.println("\n --- Composite Shapes --- \n");

		CompoundShape myCompoundShape1 = new CompoundShape("myCompoundShape1");

		Shape myRectangle1 = new Rectangle(20, 10, "myRectangle1");
		Shape myRectangle2 = new Rectangle(20, 10, "myRectangle1");

		myRectangle1.draw();
		System.out.println(myRectangle1.getArea());

		myCompoundShape1.draw();
		myCompoundShape1.addChild(myRectangle1);
		myCompoundShape1.addChild(myRectangle2);
		System.out.println(myCompoundShape1.getArea());
	}
}

package composite_shapes;

import java.util.ArrayList;

public class CompoundShape extends Shape {

	private ArrayList<Shape> children;

	public CompoundShape(String name){
		this.children = new ArrayList<>();
		super.setName(name);
	}

	@Override
	public double getArea() {
		double totalArea = 0;
		for (Shape child : children) {
			totalArea += child.getArea();
		}
		return totalArea;
	}

	public void addChild(Shape child){
		children.add(child);
	}
	
	public void removeChild(Shape child){
		children.remove(child);
	}
}

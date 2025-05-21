package composite_shapes;

public abstract class Shape {

	private String name;

	public void draw(){
		System.out.println(this.getName());
	};

	public abstract double getArea();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

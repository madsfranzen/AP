package personcollection;

import java.util.Iterator;

public class App {
	public static void main(String[] args) {
		PersonCollectionLink col = new PersonCollectionLink();
		System.out.println(col.isEmpty());
		col.add(new Person("Kasper"));
		col.add(new Person("Jesper"));
		col.add(new Person("Jens"));
		col.add(new Person("Martin"));
		col.add(new Person("Mikkel"));
		System.out.println(col.isEmpty());
		System.out.println(col.toString());
		System.out.println(col.size());

		col.add(0, new Person("Thomas"));
		System.out.println(col.toString());
		System.out.println(col.size());
		col.remove(1);
		System.out.println(col.toString());
		System.out.println(col.size());

		col.add(5, new Person("TOm"));
		System.out.println(col.toString());
		System.out.println(col.size());

		col.remove(5);
		System.out.println(col.toString());
		System.out.println(col.size());

		for (int i = 0; i < col.size(); i++) {
			System.out.println(col.get(i));
		}
		System.out.println(col.isEmpty());

		System.out.println("All Persons in array:");
		Iterator<Person> iter = col.iterator();

		while (iter.hasNext()) {
			Person p = iter.next();
			if (p.getName().equals("Jesper")) {
				iter.remove();
				p = iter.next();
			}
			System.out.println(p.getName());
		}
	}
}

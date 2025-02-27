package Opgave2;

/**
 * This program demonstrates the hash set class.
 */
public class HashSetDemo {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		MyHashSetChaining<String> names = new MyHashSetChaining(13);
		//MyHashSetLinearProbing names = new MyHashSetLinearProbing(13);

		names.add("Harry");
		names.add("Sue");
		names.add("Nina");
		names.add("Susannah");
		names.add("Larry");
		names.add("Eve");
		names.add("Sarah");
		names.add("Adam");
		names.add("Tony");
		names.add("Katherine");
		names.add("Juliet");
		names.add("Romeo");
		names.writeOut();
		System.out.println();

		System.out.println(names.size());
		System.out.println(names.contains("Romeo"));

		names.remove("Romeo");
		System.out.println(names.contains("Romeo"));
		System.out.println(names.contains("George"));
		names.remove("George");
		System.out.println(names.size());
		System.out.println();
		names.writeOut();
		System.out.println();
	}
}

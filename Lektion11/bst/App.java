package bst;

public class App {
	public static void main(String[] args) {
		System.out.println(" --- HELLO BST --- ");

		BST<Integer> myBST = new BST<Integer>();
		myBST.insert(4);
		myBST.insert(8);
		myBST.insert(9);
		myBST.insert(1);
		myBST.insert(15);
		myBST.insert(12);
		myBST.insert(8);

		for (int val : myBST) {
			System.out.println(val);
		}
	}
}

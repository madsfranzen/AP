public class App {
	public static void main(String[] args) {
		System.out.println("BST 4EVER");

		BST<Integer> bst = new BST<>();

		bst.insert(45);
		bst.insert(22);
		bst.insert(77);
		bst.insert(11);
		bst.insert(30);
		bst.insert(15);
		bst.insert(25);
		bst.insert(90);
		bst.insert(88);

		/*
		 * PREORDER:
		 * 45 - 22 - 11 - 15 - 30 - 25 - 77 - 90 - 88
		 *
		 * INORDER:
		 * 11 - 15 - 22 - 25 - 30 - 45 - 77 - 88 - 90
		 *
		 * POSTORDER:
		 * 15 - 11 - 25 - 30 - 22 - 88 - 90 - 77 - 45
		 */

		System.out.println("\nInorder:");
		bst.inorder();
		System.out.println("\nPreorder:");
		bst.preorder();
		System.out.println("\nPostorder:");
		bst.postorder();


		System.out.println("\nInorder List:");
		System.out.println(bst.inorderList());

		System.out.println("\nHeight:");
		System.out.println(bst.height());

		System.out.println("\nMax:");
		System.out.println(bst.findMax());

		System.out.println("\nMin:");
		System.out.println(bst.findMin());


	}
}

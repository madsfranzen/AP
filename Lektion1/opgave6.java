public class opgave6 {
	public static void main(String[] args) {
		System.out.println("Opgave 6:");

		char[] SGR = new char[] { 'G', 'R', 'S', 'G', 'G', 'R', 'S', 'R', 'G', 'G', 'R', 'S', 'G' };
		System.out.println(SGR);
		belgisk_flag(SGR);
		System.out.println(SGR);

	}

	public static void belgisk_flag(char[] belgisk_flag) {
		int indexOfLast_S = 0;
		int numFound_S = 0;
		int indexOfFirst_R = belgisk_flag.length;
		int numFound_R = 0;
		char swapChar = 'X';

		for (int i = 0; i < belgisk_flag.length; i++) {

			if (belgisk_flag[i] == 'S') {
				numFound_S++;

				if (i > numFound_S - 1) {
					swapChar = belgisk_flag[indexOfLast_S];
					belgisk_flag[indexOfLast_S] = belgisk_flag[i];
					belgisk_flag[i] = swapChar;
					i--;
				}
				indexOfLast_S++;

			} else if (belgisk_flag[i] == 'R') {
				numFound_R++;

				if (indexOfFirst_R > belgisk_flag.length - numFound_R) {
					swapChar = belgisk_flag[indexOfFirst_R - 1];
					belgisk_flag[indexOfFirst_R - 1] = belgisk_flag[i];
					belgisk_flag[i] = swapChar;
					i--;
				}
				indexOfFirst_R--;
			}

			if (i + numFound_R + 1 == belgisk_flag.length) {
				break;
			}
		}
	}
}

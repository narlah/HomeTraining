public class f7 {
	// ***************************f6**********************************

	// ***************************f6**********************************
	public static void main(String[] args) {
		// ***************************f6**********************************

		System.out.println(mystery(601231230));
		// ***************************************************************
	}

	private static int mystery(int n) {
		while (n >= 10) {
			int k = 0;
			while (n > 0) {
				k += n % 10;
				n /= 10;
			}
			n = k;
		}
		return n;
	}

}
package tony.test;

public class NumberTest {

	public static void main(String[] args) {
		for (int i = 1; i < 201; i++) {
			if (i==200) {
				System.out.print(i);
				break;
			}
			System.err.print(i+",");
		}
	}

}

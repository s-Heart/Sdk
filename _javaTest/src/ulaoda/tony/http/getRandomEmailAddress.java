package ulaoda.tony.http;

import java.util.Date;
import java.util.Random;

public class getRandomEmailAddress {
	public static void main(String[] args) {
		Random random=new Random();
		for (int i = 0; i < 100; i++) {
			String userAddress=Utility.getRandomLetter(3)+Utility.getRandomLetter(2)+random.nextInt(99999);
			System.out.println("EmailAddress-->"+i+":"+userAddress);
		}
	}
}

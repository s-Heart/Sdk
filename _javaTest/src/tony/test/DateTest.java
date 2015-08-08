package tony.test;

import java.util.Calendar;
import java.util.Locale;

public class DateTest {

	public static void main(String[] args) {
		Calendar rightNow=Calendar.getInstance(Locale.CHINESE);
		System.out.println(rightNow);
		
		Calendar testCalendar=Calendar.getInstance();
		testCalendar.set(2013, 11, 31);
		System.out.println(testCalendar);
		
		System.out.println(rightNow.compareTo(rightNow));
		
		
	}
}

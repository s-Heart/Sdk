
public class SplitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String content="hotline=010-62263160|_|";
		String[] contents=content.split("\\|_\\|");
		String servicehotline=contents[0].replace("hotline=", "客服电话:");
		System.out.println(servicehotline);
		
		
		String content2="11212121"+"|"+"sasasasdsa";
		System.out.println(content2);
	}

}

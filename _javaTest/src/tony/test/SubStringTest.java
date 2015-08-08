package tony.test;

public class SubStringTest {
	
	public static void main(String[] args) {
		String returnJsonString="{\"resultCode\":1,\"uid\":17281582}";
		
		System.out.println(returnJsonString.substring(returnJsonString.indexOf("uid")+5,returnJsonString.length()-1));
		
	}
}

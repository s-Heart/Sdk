package tony.test;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;


public class AboutObject {
	public static void main(String[] args) {
		File file=new java.io.File("aasa");
		System.out.println(file);
		
		String test="test";
		System.out.println(test);
		
		try {
			ServerSocket socket=new ServerSocket(8080);
			System.out.println(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

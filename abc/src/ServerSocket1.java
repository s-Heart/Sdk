import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket1 {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8906);
			while (true) {
				Socket socket2=serverSocket.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket2.getInputStream(), "utf-8"));
				String a;
				int line=1;
				while((a = reader.readLine()) != null) {
					System.out.println("lineNum:"+line);
					line++;
					String msg = a;
					int messageHeaderLength = 10;
					String messageHeader = msg.substring(0, messageHeaderLength);
					String messageBody = msg.substring(messageHeaderLength, msg.length());
					
					System.out.println("messageHeader:" + messageHeader);
					System.out.println("messageBody:" + messageBody);
					System.out.println("messageHeader length:"+messageHeader.length());
					System.out.println("messageBody length:"+messageBody.length());
				}
			}
		} catch (Exception e) {
			
		}

	}

}

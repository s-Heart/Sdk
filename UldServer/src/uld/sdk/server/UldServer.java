package uld.sdk.server;

import java.io.IOException;

public class UldServer {
	/**
	 * @param args
	 * @throws IOException
	 * @throws Exception
	 */
	public static void main(String[] args) throws IOException {
		ThreadManager.getInstance().run();
	}
}
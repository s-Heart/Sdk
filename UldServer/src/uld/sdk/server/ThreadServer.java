package uld.sdk.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import uld.sdk.tools.Config;
import uld.sdk.tools.LogHelper;

public class ThreadServer extends Thread {

	private static ThreadServer Instance = new ThreadServer();

	public static ThreadServer getInstance() {
		return Instance;
	}

	public static final int SERVERPORT = Config.SERVERPORT;
	private ExecutorService executorService = null;
	private ServerSocket serverSocket = null;
	private static List<Socket> socketList = new ArrayList<Socket>();

	private boolean isPause = false;
	private boolean isStop = false;

	@Override
	public void run() {
		try {
			System.out.println("start server...");
			serverSocket = new ServerSocket(SERVERPORT);
			executorService = Executors.newCachedThreadPool();
			Socket socket = null;
			while (!isStop) {
				if (isPause) {
					System.out.println("pause thread...");
					Thread.sleep(3000);
					continue;
				}
				System.out.println("\r\nwaiting client message...");
				socket = serverSocket.accept();
				System.out.println("accept client...");
				//socketList.add(socket);
				executorService.execute(new ThreadProcess(socket));
				Thread.sleep(100);
			}
			System.out.println("stop thread...");
		} catch (Exception e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public void pauseThread() {
		System.out.println("begin pause thread...");
		isPause = true;
	}

	public void resumeThread() {
		System.out.println("begin resume thread...");
		isPause = false;
	}

	public void stopThread() {
		isStop = true;
		for (Socket socket : socketList) {
			if (socket != null) {
				System.out.println("socket.isClosed():" + socket.isClosed());
			}
			if (socket != null && !socket.isClosed()) {
				try {
					socket.close();
				} catch (Exception e) {
					LogHelper.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		}
	}
}

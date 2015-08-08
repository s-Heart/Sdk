package uld.sdk.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

import uld.sdk.tools.LogHelper;
import uld.sdk.tools.Utility;

public class ThreadManager {

	private static ThreadManager Instance = new ThreadManager();

	public static ThreadManager getInstance() {
		return Instance;
	}

	private Thread startThread = null;

	private final String BYEMSG = "Bye!";
	private final String QUITKEY = "q";
	private final String FG = "\r\n###########################################";
	private final String WELCOME = uld.sdk.tools.Config.UldServerWelcome + ThreadServer.SERVERPORT;
	private final String MENU = WELCOME + "\r\n--SDK Server菜单--\r\n" + "1：start  启动线程\r\n" + "2：pause  暂停线程\r\n" + "3：resume 恢复线程\r\n" + "4：stop   停止线程\r\n" + "q：quit   退出系统\r\n" + "请输入操作指令[1-4]，输入[%s]退出系统";

	public void run() throws IOException {
		System.out.println(FG);
		System.out.println(WELCOME);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String inputString = "";
		while (true) {
			// 欢迎界面
			System.out.println(FG);
			System.out.println("当前时间：" + Utility.getFormatDate());
			System.out.println(String.format(MENU, QUITKEY));

			// 处理是否退出
			inputString = bufferedReader.readLine();
			if (inputString.equals(QUITKEY)) {
				System.out.println("正在关闭程序，请稍等...");
				System.out.println(FG);
				break;
			}

			// 转换输入字符为数字
			int inputValue = 0;
			try {
				inputValue = Utility.getInt(inputString);
			} catch (Exception e) {
				LogHelper.log(Level.SEVERE, e.getMessage(), e);
			}

			// 根据输入数字进行相关操作
			switch (inputValue) {
			// start 启动接收socket线程
			case 1:
				startThread = new Thread(ThreadServer.getInstance());
				startThread.start();
				break;
			// pause 暂停接收socket线程
			case 2:
				ThreadServer.getInstance().pauseThread();
				break;
			// resume 恢复接收socket线程
			case 3:
				ThreadServer.getInstance().resumeThread();
				break;
			// stop 停止接收socket线程
			case 4:
				ThreadServer.getInstance().stopThread();
				break;
			// 其它不进行处理
			default:
				System.out.println("您输入了【" + inputString + "】，没有找到相应的处理接口");
				break;
			}
		}

		ThreadServer.getInstance().stopThread();

		if (bufferedReader != null) {
			bufferedReader.close();
		}
		if (startThread != null) {
			startThread.interrupt();
		}
		LogHelper.close();
		System.out.println(BYEMSG);
	}
}

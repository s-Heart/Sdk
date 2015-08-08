package uld.sdk.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.LogHelper;
import uld.sdk.tools.Utility;

public class ThreadProcess extends Thread {
	private Socket socket = null;
	/*
	 * 超时时间30秒钟
	 */
	private static final int TIMEOUT = 30000;

	private ObjectInputStream oisStream = null;
	private ObjectOutputStream oosStream = null;

	/**
	 * @param socket
	 */
	public ThreadProcess(Socket socket) {
		this.socket = socket;
		if (socket != null) {
			try {
				socket.setSoTimeout(TIMEOUT);
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void run() {
		MessageHeader messageHeader = null;
		MessageBody messageBody = null;
		MessageReturn messageReturn = new MessageReturn();
		try {
			oisStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			messageHeader = (MessageHeader) oisStream.readObject();
			if (messageHeader != null) {
				String formateDate = messageHeader.getSendDate();
				String decodeDate = Utility.decodeDES(messageHeader.getSign());
				System.out.println("formateDate:" + formateDate);
				System.out.println("decodeDate:" + decodeDate);
				if (!decodeDate.equals(formateDate)) {
					messageReturn.setErrNo(-1);
					messageReturn.setErrMsg("加密数据不正确");
				} else {
					Object retValue = null;
					messageBody = (MessageBody) oisStream.readObject();
					if (messageBody != null) {
						try {
							retValue = Utility.Invoke(messageBody.getClassPathName(), messageBody.getMethodName(), messageBody.getParmTypeList(), messageBody.getParmList());
						} catch (Exception e) {
							messageReturn.setErr(-1, "调用 方法报错:" + e.getMessage());
							LogHelper.log(Level.SEVERE, e.getMessage(), e);
						}
					}
					if (messageReturn.getErrNo() == 0) {
						messageReturn = (MessageReturn) retValue;
					}
				}
			} else {
				messageReturn.setErr(-1, "消息头为空");
			}
			oosStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			oosStream.writeObject(messageReturn);
			oosStream.flush();
		} catch (Exception e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			try {
				if (oisStream != null) {
					oisStream.close();
				}
			} catch (IOException e) {
			}
			try {
				if (oosStream != null) {
					oosStream.close();
				}
			} catch (IOException e) {
			}
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
			}

			if (messageHeader != null) {
				System.out.println("客户端发送时间：" + Utility.getFormatDate(messageHeader.getSendDate()));
				System.out.println("服务器当前时间：" + Utility.getFormatDate(new Date()));
			}
			if (messageBody != null) {
//				System.out.println("调用方法：" + messageBody.getClassPathName() + "->" + messageBody.getMethodName());
				LogHelper.log("调用方法：" + messageBody.getClassPathName() + "->" + messageBody.getMethodName());
			}

			if (messageReturn.getErrNo() < 0) {
				LogHelper.log(Level.WARNING, messageReturn.getErrMsg());
			}
		}
	}
}

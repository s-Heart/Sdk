package com.nearme.gamecenter.open.demo.nearme.gamecenter;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class IMInspectHandler {
	
	private String imsi ="delete&";

	
	/**
	 * 测试连接是否正常
	 */
	public String call(String number) {
		imsi = imsi + number;
		//默认返回的值
		String  telphone = "fail";
        Socket socket = null;
        OutputStream netOut = null;
        InputStream netIn = null;
        
        try {
//			socket = new Socket("127.0.0.1", 8888);
        	socket = new Socket("115.236.101.117", 8888);
			if(null != socket){
				socket.setSoTimeout(20000);
                netOut = socket.getOutputStream();
                netOut.write(getOutBuffer(imsi));
                netOut.flush();
                netIn = socket.getInputStream();
                byte[] buff = new byte[100];
                int ret = netIn.read(buff);
                if (ret > 0) {
                    String inBuff = new String(buff, "UTF-8");
                    int len = inBuff.indexOf("\r\n");
                    telphone = inBuff.substring(0, len);
                }
//                System.out.println(telphone);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch(SocketTimeoutException ste){
			ste.printStackTrace();
			return telphone;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
                if (netOut != null) {
                    netOut.close();
                }
                if (netIn != null) {
                    netIn.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
		return telphone;
	}
	
	//由于netty采用的StringSplit来处理的，故增加\r\n
	private byte[] getOutBuffer(String imsi){
		imsi = imsi + "\r\n";
		byte[] b = imsi.getBytes();
		return b;
	}
	
}

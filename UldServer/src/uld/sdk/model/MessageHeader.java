package uld.sdk.model;

import java.io.Serializable;
import java.util.Date;

import uld.sdk.tools.Utility;

public class MessageHeader implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sendDate;
	private String sign;

	public MessageHeader() {
	}

	public MessageHeader(String sendDate, String sign) {
		this.sendDate = sendDate;
		this.sign = sign;
	}

	/**
	 * 初始化snedDate和sign
	 */
	public void init() {
		this.sendDate = Utility.getFormatDate(new Date());
		this.sign = Utility.encodeDES(this.sendDate);
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}

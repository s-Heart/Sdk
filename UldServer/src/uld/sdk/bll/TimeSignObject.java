package uld.sdk.bll;

/**
 * TimeSign字段所含内容
 * 
 * @author 史少杰
 * 
 */
public class TimeSignObject {
	private String time;
	private String channelid;
	private String addvalue;
	private String userid;
	private String md5;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getAddvalue() {
		return addvalue;
	}

	public void setAddvalue(String addvalue) {
		this.addvalue = addvalue;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}

package wh.member.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import wh.member.model.UserEnums.GenderType;
import wh.promotion.model.MobileDevice;

public class UserTemp implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;
	private String userName;
	private String password;
	private String rawPassword;
	private GenderType genderType;
	private Date birthday;
	private MobileDevice mobileDevice;

	public UserTemp() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public GenderType getGenderType() {
		return genderType;
	}

	public void setGenderType(GenderType genderType) {
		this.genderType = genderType;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public MobileDevice getMobileDevice() {
		return mobileDevice;
	}

	public void setMobileDevice(MobileDevice mobileDevice) {
		this.mobileDevice = mobileDevice;
	}

	public int getCreateDateYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(birthday);
		return calendar.get(Calendar.YEAR);
	}
}
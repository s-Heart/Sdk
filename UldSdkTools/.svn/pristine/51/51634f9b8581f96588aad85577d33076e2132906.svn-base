package wh.member.model;

import java.io.Serializable;

public class UserEnums implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum UserStatus {
		状态(0), 有效(1), 无效(2);

		private final int value;

		public int getValue() {
			return value;
		}

		UserStatus(int value) {
			this.value = value;
		}

		public static UserStatus parse(String value) {
			UserStatus retValue = null;
			for (UserStatus item : UserStatus.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum OnLineType {
		在线类型(0), 在线(1), 下线(2);

		private final int value;

		public int getValue() {
			return value;
		}

		OnLineType(int value) {
			this.value = value;
		}

		public static OnLineType parse(String value) {
			OnLineType retValue = null;
			for (OnLineType item : OnLineType.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum GenderType {
		性别(0), 男(1), 女(2);

		private final int value;

		public int getValue() {
			return value;
		}

		GenderType(int value) {
			this.value = value;
		}

		public static GenderType parse(String value) {
			GenderType retValue = null;
			for (GenderType item : GenderType.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum ChannelType {
		渠道类型(0), 自然渠道(1), 推广渠道(2);

		private final int value;

		public int getValue() {
			return value;
		}

		ChannelType(int value) {
			this.value = value;
		}

		public static ChannelType parse(String value) {
			ChannelType retValue = null;
			for (ChannelType item : ChannelType.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum SourceType {
		来源类型(0), 导航(1), 角色(2);

		private final int value;

		public int getValue() {
			return value;
		}

		SourceType(int value) {
			this.value = value;
		}

		public static SourceType parse(String value) {
			SourceType retValue = null;
			for (SourceType item : SourceType.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum MemberRankType {
		会员等级(0), 普通会员(1), 铜牌会员(2), 白银会员(3), 黄金会员(4), 白金会员(5), 钻石会员(6), 皇冠会员(7);
		private final int value;

		public int getValue() {
			return value;
		}

		MemberRankType(int value) {
			this.value = value;
		}

		public static MemberRankType parse(String value) {
			MemberRankType retValue = null;
			for (MemberRankType item : MemberRankType.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum AuthenticationType {
		密保认证类型(0), 未进入认证(1), 密保认证未通过(2), 密保认证中(4), 密保认证通过(8);
		private final int value;

		public int getValue() {
			return value;
		}

		AuthenticationType(int value) {
			this.value = value;
		}

		public static AuthenticationType parse(String value) {
			AuthenticationType retValue = null;
			for (AuthenticationType item : AuthenticationType.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum EmailAuthenticationType {
		邮箱认证类型(0), 未进入认证(1), 邮箱认证未通过(2), 邮箱认证中(4), 邮箱认证通过(8);
		private final int value;

		public int getValue() {
			return value;
		}

		EmailAuthenticationType(int value) {
			this.value = value;
		}

		public static EmailAuthenticationType parse(String value) {
			EmailAuthenticationType retValue = null;
			for (EmailAuthenticationType item : EmailAuthenticationType
					.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum MobileAuthenticationType {
		手机认证类型(0), 未进入认证(1), 手机认证未通过(2), 手机认证中(4), 手机认证通过(8);
		private final int value;

		public int getValue() {
			return value;
		}

		MobileAuthenticationType(int value) {
			this.value = value;
		}

		public static MobileAuthenticationType parse(String value) {
			MobileAuthenticationType retValue = null;
			for (MobileAuthenticationType item : MobileAuthenticationType
					.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum IndulgenceAuthenticationType {
		防沉迷认证类型(0), 未进入认证(1), 防沉迷认证未通过(2), 防沉迷认证中(4), 防沉迷认证通过(8);
		private final int value;

		public int getValue() {
			return value;
		}

		IndulgenceAuthenticationType(int value) {
			this.value = value;
		}

		public static IndulgenceAuthenticationType parse(String value) {
			IndulgenceAuthenticationType retValue = null;
			for (IndulgenceAuthenticationType item : IndulgenceAuthenticationType
					.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum PassordQuestionOneType {
		请选择密码保护问题(0), 我的初中名称(1), 我的高中名称(2), 我的大学名称(3);
		private final int value;

		public int getValue() {
			return value;
		}

		PassordQuestionOneType(int value) {
			this.value = value;
		}

		public static PassordQuestionOneType parse(String value) {
			PassordQuestionOneType retValue = null;
			for (PassordQuestionOneType item : PassordQuestionOneType.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

	public enum PassordQuestionTwoType {
		请选择密码保护问题(0), 我的出生地(1), 我父亲的名字(2), 我母亲的名字(3);
		private final int value;

		public int getValue() {
			return value;
		}

		PassordQuestionTwoType(int value) {
			this.value = value;
		}

		public static PassordQuestionTwoType parse(String value) {
			PassordQuestionTwoType retValue = null;
			for (PassordQuestionTwoType item : PassordQuestionTwoType.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}

}

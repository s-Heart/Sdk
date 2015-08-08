package wh.member.model;

public class UserEnum {
	
	/**
	 * 性别：0、性别；1、男；2、女；
	 */
	public enum GenderType {
		性别((byte)0),男((byte)1),女((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GenderType(byte value) {
			this.value = value;
		}

		public static GenderType parse(byte value) {
			GenderType retValue = GenderType.性别;
			for (GenderType item : GenderType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 */
	public enum ChannelType {
		渠道类型((byte)0),自然渠道((byte)1),推广渠道((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ChannelType(byte value) {
			this.value = value;
		}

		public static ChannelType parse(byte value) {
			ChannelType retValue = ChannelType.渠道类型;
			for (ChannelType item : ChannelType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 来源类型：0、来源类型；1、导航；2、角色；4、喔喔；8、明珠；16、问仙；32、仙域；64、龙门；128、傲视；256、星客；512、手机；1024、其它；
	 */
	public enum SourceType {
		来源类型(0),导航(1),角色(2),喔喔(4),明珠(8),问仙(16),仙域(32),龙门(64),傲视(128),星客(256),手机(512),其它(1024);
		
		private final int value;

		public int getValue() {
			return value;
		}

		SourceType(int value) {
			this.value = value;
		}

		public static SourceType parse(int value) {
			SourceType retValue = SourceType.来源类型;
			for (SourceType item : SourceType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 会员等级：0、会员等级；1、普通会员；2、铜牌会员；3、白银会员；4、黄金会员；5、白金会员；6、钻石会员；7、皇冠会员；
	 */
	public enum MemberRankType {
		会员等级(0),普通会员(1),铜牌会员(2),白银会员(3),黄金会员(4),白金会员(5),钻石会员(6),皇冠会员(7);
		
		private final int value;

		public int getValue() {
			return value;
		}

		MemberRankType(int value) {
			this.value = value;
		}

		public static MemberRankType parse(int value) {
			MemberRankType retValue = MemberRankType.会员等级;
			for (MemberRankType item : MemberRankType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 密保认证类型：0、全部；1、未进入认证；2、密保认证未通过；4、密保认证中；8、密保认证通过；
	 */
	public enum AuthenticationType {
		全部(0),未进入认证(1),密保认证未通过(2),密保认证中(4),密保认证通过(8);
		
		private final int value;

		public int getValue() {
			return value;
		}

		AuthenticationType(int value) {
			this.value = value;
		}

		public static AuthenticationType parse(int value) {
			AuthenticationType retValue = AuthenticationType.全部;
			for (AuthenticationType item : AuthenticationType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 邮箱认证类型：0、全部；1、未进入认证；2、邮箱认证未通过；4、邮箱认证中；8、邮箱认证通过；
	 */
	public enum EmailAuthenticationType {
		全部(0),未进入认证(1),邮箱认证未通过(2),邮箱认证中(4),邮箱认证通过(8);
		
		private final int value;

		public int getValue() {
			return value;
		}

		EmailAuthenticationType(int value) {
			this.value = value;
		}

		public static EmailAuthenticationType parse(int value) {
			EmailAuthenticationType retValue = EmailAuthenticationType.全部;
			for (EmailAuthenticationType item : EmailAuthenticationType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 手机认证类型：0、全部；1、未进入认证；2、手机认证未通过；4、手机认证中；8、手机认证通过；
	 */
	public enum MobileAuthenticationType {
		全部(0),未进入认证(1),手机认证未通过(2),手机认证中(4),手机认证通过(8);
		
		private final int value;

		public int getValue() {
			return value;
		}

		MobileAuthenticationType(int value) {
			this.value = value;
		}

		public static MobileAuthenticationType parse(int value) {
			MobileAuthenticationType retValue = MobileAuthenticationType.全部;
			for (MobileAuthenticationType item : MobileAuthenticationType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 防沉迷认证类型：0、全部；1、未进入认证；2、防沉迷认证未通过；4、防沉迷认证中；8、防沉迷认证通过；
	 */
	public enum IndulgenceAuthenticationType {
		全部(0),未进入认证(1),防沉迷认证未通过(2),防沉迷认证中(4),防沉迷认证通过(8);
		
		private final int value;

		public int getValue() {
			return value;
		}

		IndulgenceAuthenticationType(int value) {
			this.value = value;
		}

		public static IndulgenceAuthenticationType parse(int value) {
			IndulgenceAuthenticationType retValue = IndulgenceAuthenticationType.全部;
			for (IndulgenceAuthenticationType item : IndulgenceAuthenticationType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 在线类型：0、在线类型；1、在线；2、下线；
	 */
	public enum OnLineType {
		在线类型((byte)0),在线((byte)1),下线((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		OnLineType(byte value) {
			this.value = value;
		}

		public static OnLineType parse(byte value) {
			OnLineType retValue = OnLineType.在线类型;
			for (OnLineType item : OnLineType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 密码保护问题1：0、请选择密码保护问题；1、我的初中名称；2、我的高中名称；3、我的大学名称；
	 */
	public enum PassordQuestionOneType {
		请选择密码保护问题(0),我的初中名称(1),我的高中名称(2),我的大学名称(3);
		
		private final int value;

		public int getValue() {
			return value;
		}

		PassordQuestionOneType(int value) {
			this.value = value;
		}

		public static PassordQuestionOneType parse(int value) {
			PassordQuestionOneType retValue = PassordQuestionOneType.请选择密码保护问题;
			for (PassordQuestionOneType item : PassordQuestionOneType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 密码保护问题2：0、请选择密码保护问题；1、我的出生地；2、我父亲的名字；3、我母亲的名字；
	 */
	public enum PassordQuestionTwoType {
		请选择密码保护问题(0),我的出生地(1),我父亲的名字(2),我母亲的名字(3);
		
		private final int value;

		public int getValue() {
			return value;
		}

		PassordQuestionTwoType(int value) {
			this.value = value;
		}

		public static PassordQuestionTwoType parse(int value) {
			PassordQuestionTwoType retValue = PassordQuestionTwoType.请选择密码保护问题;
			for (PassordQuestionTwoType item : PassordQuestionTwoType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum UserStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		UserStatus(byte value) {
			this.value = value;
		}

		public static UserStatus parse(byte value) {
			UserStatus retValue = UserStatus.状态;
			for (UserStatus item : UserStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


package wh.server.model;

public class UserActiveCodeEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum UserActiveCodeStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		UserActiveCodeStatus(byte value) {
			this.value = value;
		}

		public static UserActiveCodeStatus parse(byte value) {
			UserActiveCodeStatus retValue = UserActiveCodeStatus.状态;
			for (UserActiveCodeStatus item : UserActiveCodeStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 类型：0、类型；1、新手卡；2、幸运礼包；3、平台激活码；
	 */
	public enum ActiveCodeType {
		类型((byte)0),新手卡((byte)1),幸运礼包((byte)2),平台激活码((byte)3);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ActiveCodeType(byte value) {
			this.value = value;
		}

		public static ActiveCodeType parse(byte value) {
			ActiveCodeType retValue = ActiveCodeType.类型;
			for (ActiveCodeType item : ActiveCodeType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


package wh.member.model;

public class UserPortraitEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum UserPortraitStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		UserPortraitStatus(byte value) {
			this.value = value;
		}

		public static UserPortraitStatus parse(byte value) {
			UserPortraitStatus retValue = UserPortraitStatus.状态;
			for (UserPortraitStatus item : UserPortraitStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


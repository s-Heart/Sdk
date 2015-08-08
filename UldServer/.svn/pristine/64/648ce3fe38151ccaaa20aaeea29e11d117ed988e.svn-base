package wh.member.model;

public class AccountEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum AccountStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		AccountStatus(byte value) {
			this.value = value;
		}

		public static AccountStatus parse(byte value) {
			AccountStatus retValue = AccountStatus.状态;
			for (AccountStatus item : AccountStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


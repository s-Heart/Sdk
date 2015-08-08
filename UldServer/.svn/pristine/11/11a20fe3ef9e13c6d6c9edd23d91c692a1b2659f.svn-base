package wh.member.model;

public class AccountDetailEnum {
	
	/**
	 * 使用类型：0、使用类型；1、获得；2、消费；
	 */
	public enum UseType {
		使用类型((byte)0),获得((byte)1),消费((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		UseType(byte value) {
			this.value = value;
		}

		public static UseType parse(byte value) {
			UseType retValue = UseType.使用类型;
			for (UseType item : UseType.values()) {
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
	public enum AccountDetailStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		AccountDetailStatus(byte value) {
			this.value = value;
		}

		public static AccountDetailStatus parse(byte value) {
			AccountDetailStatus retValue = AccountDetailStatus.状态;
			for (AccountDetailStatus item : AccountDetailStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


package wh.game.model;

public class ActiveCodeEnum {
	
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
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum ActiveCodeStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ActiveCodeStatus(byte value) {
			this.value = value;
		}

		public static ActiveCodeStatus parse(byte value) {
			ActiveCodeStatus retValue = ActiveCodeStatus.状态;
			for (ActiveCodeStatus item : ActiveCodeStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 是否被使用：0、是否被使用；1、未被使用；2、被使用；
	 */
	public enum UsedType {
		是否被使用((byte)0),未被使用((byte)1),被使用((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		UsedType(byte value) {
			this.value = value;
		}

		public static UsedType parse(byte value) {
			UsedType retValue = UsedType.是否被使用;
			for (UsedType item : UsedType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


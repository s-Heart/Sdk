package wh.member.model;

public class UserBasicEnum {
	
	/**
	 * 性别：0、性别；1、男；2、女；
	 */
	public enum MGenderType {
		性别((byte)0),男((byte)1),女((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		MGenderType(byte value) {
			this.value = value;
		}

		public static MGenderType parse(byte value) {
			MGenderType retValue = MGenderType.性别;
			for (MGenderType item : MGenderType.values()) {
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
	public enum MOnLinetype {
		在线类型((byte)0),在线((byte)1),下线((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		MOnLinetype(byte value) {
			this.value = value;
		}

		public static MOnLinetype parse(byte value) {
			MOnLinetype retValue = MOnLinetype.在线类型;
			for (MOnLinetype item : MOnLinetype.values()) {
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
	public enum UserBasicStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		UserBasicStatus(byte value) {
			this.value = value;
		}

		public static UserBasicStatus parse(byte value) {
			UserBasicStatus retValue = UserBasicStatus.状态;
			for (UserBasicStatus item : UserBasicStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


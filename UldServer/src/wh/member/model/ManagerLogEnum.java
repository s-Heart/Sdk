package wh.member.model;

public class ManagerLogEnum {
	
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
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum ManagerLogStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ManagerLogStatus(byte value) {
			this.value = value;
		}

		public static ManagerLogStatus parse(byte value) {
			ManagerLogStatus retValue = ManagerLogStatus.状态;
			for (ManagerLogStatus item : ManagerLogStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


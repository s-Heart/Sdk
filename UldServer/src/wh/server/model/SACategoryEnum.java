package wh.server.model;

public class SACategoryEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum SACategoryStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		SACategoryStatus(byte value) {
			this.value = value;
		}

		public static SACategoryStatus parse(byte value) {
			SACategoryStatus retValue = SACategoryStatus.状态;
			for (SACategoryStatus item : SACategoryStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


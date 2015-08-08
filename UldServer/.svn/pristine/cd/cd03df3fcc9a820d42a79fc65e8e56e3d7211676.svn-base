package wh.server.model;

public class AccessedGameEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum AccessedGameStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		AccessedGameStatus(byte value) {
			this.value = value;
		}

		public static AccessedGameStatus parse(byte value) {
			AccessedGameStatus retValue = AccessedGameStatus.状态;
			for (AccessedGameStatus item : AccessedGameStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


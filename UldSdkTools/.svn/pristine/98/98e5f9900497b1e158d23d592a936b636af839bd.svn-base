package wh.game.model;

public class ProviderEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum ProviderStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ProviderStatus(byte value) {
			this.value = value;
		}

		public static ProviderStatus parse(byte value) {
			ProviderStatus retValue = ProviderStatus.状态;
			for (ProviderStatus item : ProviderStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


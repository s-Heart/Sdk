package wh.promotion.model;

public class DomainEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum DomainStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		DomainStatus(byte value) {
			this.value = value;
		}

		public static DomainStatus parse(byte value) {
			DomainStatus retValue = DomainStatus.状态;
			for (DomainStatus item : DomainStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


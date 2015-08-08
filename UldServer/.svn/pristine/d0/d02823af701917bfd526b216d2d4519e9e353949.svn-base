package wh.order.model;

public class OrderLogEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum OrderLogStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		OrderLogStatus(byte value) {
			this.value = value;
		}

		public static OrderLogStatus parse(byte value) {
			OrderLogStatus retValue = OrderLogStatus.状态;
			for (OrderLogStatus item : OrderLogStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


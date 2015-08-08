package wh.order.model;

public class OrderChannelEnum {
	
	/**
	 * 状态：0、状态；1、显示；2、不显示；
	 */
	public enum OrderChannelStatus	{
		状态((byte)0),显示((byte)1),不显示((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		OrderChannelStatus(byte value) {
			this.value = value;
		}

		public static OrderChannelStatus parse(byte value) {
			OrderChannelStatus retValue = OrderChannelStatus.状态;
			for (OrderChannelStatus item : OrderChannelStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


package wh.promotion.model;

public class ChannelEnum {
	
	/**
	 * 渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 */
	public enum ChannelType {
		渠道类型((byte)0),自然渠道((byte)1),推广渠道((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ChannelType(byte value) {
			this.value = value;
		}

		public static ChannelType parse(byte value) {
			ChannelType retValue = ChannelType.渠道类型;
			for (ChannelType item : ChannelType.values()) {
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
	public enum ChannelStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ChannelStatus(byte value) {
			this.value = value;
		}

		public static ChannelStatus parse(byte value) {
			ChannelStatus retValue = ChannelStatus.状态;
			for (ChannelStatus item : ChannelStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


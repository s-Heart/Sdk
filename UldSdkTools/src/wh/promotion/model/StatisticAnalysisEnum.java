package wh.promotion.model;

public class StatisticAnalysisEnum {
	
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
}


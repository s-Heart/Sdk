package wh.promotion.model;

public class ChannelInterfaceEnum {
	
	/**
	 * 接口类型：0、接口类型；1、第三方注册接口；
	 */
	public enum CIType {
		接口类型((byte)0),第三方注册接口((byte)1);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		CIType(byte value) {
			this.value = value;
		}

		public static CIType parse(byte value) {
			CIType retValue = CIType.接口类型;
			for (CIType item : CIType.values()) {
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
	public enum ChannelInterfaceStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ChannelInterfaceStatus(byte value) {
			this.value = value;
		}

		public static ChannelInterfaceStatus parse(byte value) {
			ChannelInterfaceStatus retValue = ChannelInterfaceStatus.状态;
			for (ChannelInterfaceStatus item : ChannelInterfaceStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


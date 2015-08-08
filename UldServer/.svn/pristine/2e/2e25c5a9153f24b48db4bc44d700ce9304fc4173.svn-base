package wh.member.model;

public class CustomerServiceEnum {
	
	/**
	 * 启用状态：0、启用状态；1、开启；2、暂停；4、停止；
	 */
	public enum EnableType {
		启用状态((byte)0),开启((byte)1),暂停((byte)2),停止((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		EnableType(byte value) {
			this.value = value;
		}

		public static EnableType parse(byte value) {
			EnableType retValue = EnableType.启用状态;
			for (EnableType item : EnableType.values()) {
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
	public enum CustomerServiceStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		CustomerServiceStatus(byte value) {
			this.value = value;
		}

		public static CustomerServiceStatus parse(byte value) {
			CustomerServiceStatus retValue = CustomerServiceStatus.状态;
			for (CustomerServiceStatus item : CustomerServiceStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


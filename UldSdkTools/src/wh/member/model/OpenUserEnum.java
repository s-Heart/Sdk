package wh.member.model;

public class OpenUserEnum {
	
	/**
	 * 平台类型：0、平台类型；1、QQ；2、sina微博；4、YY；8、一七一七三；
	 */
	public enum OpenType {
		平台类型((byte)0),QQ((byte)1),sina微博((byte)2),YY((byte)4),一七一七三((byte)8);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		OpenType(byte value) {
			this.value = value;
		}

		public static OpenType parse(byte value) {
			OpenType retValue = OpenType.平台类型;
			for (OpenType item : OpenType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 绑定类型：0、绑定类型；1、已绑定；2、未绑定；
	 */
	public enum BindType {
		绑定类型((byte)0),已绑定((byte)1),未绑定((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		BindType(byte value) {
			this.value = value;
		}

		public static BindType parse(byte value) {
			BindType retValue = BindType.绑定类型;
			for (BindType item : BindType.values()) {
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
	public enum OpenUserStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		OpenUserStatus(byte value) {
			this.value = value;
		}

		public static OpenUserStatus parse(byte value) {
			OpenUserStatus retValue = OpenUserStatus.状态;
			for (OpenUserStatus item : OpenUserStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


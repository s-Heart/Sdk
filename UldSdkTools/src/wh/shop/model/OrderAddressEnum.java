package wh.shop.model;

public class OrderAddressEnum {
	
	/**
	 * 默认类型：0、默认类型；1、是；2、否；
	 */
	public enum DefaultType {
		默认类型((byte)0),是((byte)1),否((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		DefaultType(byte value) {
			this.value = value;
		}

		public static DefaultType parse(byte value) {
			DefaultType retValue = DefaultType.默认类型;
			for (DefaultType item : DefaultType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


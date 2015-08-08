package wh.shop.model;

public class GoodsCategoryEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum GoodsCategoryStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GoodsCategoryStatus(byte value) {
			this.value = value;
		}

		public static GoodsCategoryStatus parse(byte value) {
			GoodsCategoryStatus retValue = GoodsCategoryStatus.状态;
			for (GoodsCategoryStatus item : GoodsCategoryStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


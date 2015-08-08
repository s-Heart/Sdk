package wh.shop.model;

public class ProductEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum ProductStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ProductStatus(byte value) {
			this.value = value;
		}

		public static ProductStatus parse(byte value) {
			ProductStatus retValue = ProductStatus.状态;
			for (ProductStatus item : ProductStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


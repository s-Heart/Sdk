package wh.shop.model;

public class GoodsOrderDetailEnum {
	
	/**
	 * 支付类型：0、支付类型；1、幸运点；2、平台币；4、幸运点平台币；
	 */
	public enum PaymentType {
		支付类型((byte)0),幸运点((byte)1),平台币((byte)2),幸运点平台币((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		PaymentType(byte value) {
			this.value = value;
		}

		public static PaymentType parse(byte value) {
			PaymentType retValue = PaymentType.支付类型;
			for (PaymentType item : PaymentType.values()) {
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
	public enum GoodsOrderDetailStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GoodsOrderDetailStatus(byte value) {
			this.value = value;
		}

		public static GoodsOrderDetailStatus parse(byte value) {
			GoodsOrderDetailStatus retValue = GoodsOrderDetailStatus.状态;
			for (GoodsOrderDetailStatus item : GoodsOrderDetailStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


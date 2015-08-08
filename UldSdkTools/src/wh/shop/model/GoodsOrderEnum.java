package wh.shop.model;

public class GoodsOrderEnum {
	
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
	 * 订单类型：0、订单类型；1、未发货；2、发货中；4、已发货；8、已签收；16、已完成；32、已取消；64、退款申请；128、退货中；256、已退货；
	 */
	public enum ShopOrderType {
		订单类型(0),未发货(1),发货中(2),已发货(4),已签收(8),已完成(16),已取消(32),退款申请(64),退货中(128),已退货(256);
		
		private final int value;

		public int getValue() {
			return value;
		}

		ShopOrderType(int value) {
			this.value = value;
		}

		public static ShopOrderType parse(int value) {
			ShopOrderType retValue = ShopOrderType.订单类型;
			for (ShopOrderType item : ShopOrderType.values()) {
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
	public enum GoodsOrderStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GoodsOrderStatus(byte value) {
			this.value = value;
		}

		public static GoodsOrderStatus parse(byte value) {
			GoodsOrderStatus retValue = GoodsOrderStatus.状态;
			for (GoodsOrderStatus item : GoodsOrderStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


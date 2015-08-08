package wh.shop.model;

public class GoodsOrderLogEnum {
	
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
}


package wh.order.model;

public class OrderDetailEnum {
	
	/**
	 * 支付类型：0、支付类型；1、网上银行充值；2、支付宝充值；4、手机卡充值；8、声讯充值；16、点卡充值；32、PayPal充值；
	 */
	public enum PayType {
		支付类型((byte)0),网上银行充值((byte)1),支付宝充值((byte)2),手机卡充值((byte)4),声讯充值((byte)8),点卡充值((byte)16),PayPal充值((byte)32);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		PayType(byte value) {
			this.value = value;
		}

		public static PayType parse(byte value) {
			PayType retValue = PayType.支付类型;
			for (PayType item : PayType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 卡类型：0、卡类型；1、神州行；2、联通卡；4、电信卡；
	 */
	public enum CardType {
		卡类型((byte)0),神州行((byte)1),联通卡((byte)2),电信卡((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		CardType(byte value) {
			this.value = value;
		}

		public static CardType parse(byte value) {
			CardType retValue = CardType.卡类型;
			for (CardType item : CardType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 订单类型：0、订单类型；1、已提交；2、处理成功；4、处理失败；8、已提交游戏方；16、游戏方处理成功；32、游戏方处理失败；64、掉单；
	 */
	public enum OrderType {
		订单类型((byte)0),已提交((byte)1),处理成功((byte)2),处理失败((byte)4),已提交游戏方((byte)8),游戏方处理成功((byte)16),游戏方处理失败((byte)32),掉单((byte)64);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		OrderType(byte value) {
			this.value = value;
		}

		public static OrderType parse(byte value) {
			OrderType retValue = OrderType.订单类型;
			for (OrderType item : OrderType.values()) {
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
	public enum OrderDetailStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		OrderDetailStatus(byte value) {
			this.value = value;
		}

		public static OrderDetailStatus parse(byte value) {
			OrderDetailStatus retValue = OrderDetailStatus.状态;
			for (OrderDetailStatus item : OrderDetailStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


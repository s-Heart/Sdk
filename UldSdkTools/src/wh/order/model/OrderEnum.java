package wh.order.model;

public class OrderEnum {
	
	/**
	 * 支付类型：0、支付类型；1、网上银行；2、支付宝；3、手机卡；4、声讯；5、点卡；6、PayPal；7、神州行；8、联通；9、电信；10、钱包；65、骏网一卡通支付；66、盛大卡；67、网易一卡通；68、完美一卡通支付；69、征途卡支付；70、久游一卡通支付；71、易宝E卡通支付；72、纵游一卡通；73、天下一卡通；74、G币返还；
	 */
	public enum PayType {
		支付类型((byte)0),网上银行((byte)1),支付宝((byte)2),手机卡((byte)3),声讯((byte)4),点卡((byte)5),PayPal((byte)6),神州行((byte)7),联通((byte)8),电信((byte)9),钱包((byte)10),骏网一卡通支付((byte)65),盛大卡((byte)66),网易一卡通((byte)67),完美一卡通支付((byte)68),征途卡支付((byte)69),久游一卡通支付((byte)70),易宝E卡通支付((byte)71),纵游一卡通((byte)72),天下一卡通((byte)73),G币返还((byte)74),其他渠道((byte)75),钱包支付((byte)76),快捷支付((byte)77);
		
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
	 * 帐户类型：0、全部；1、D币；2、幸运点；
	 */
	public enum AccountType {
		全部((byte)0),D币((byte)1),幸运点((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		AccountType(byte value) {
			this.value = value;
		}

		public static AccountType parse(byte value) {
			AccountType retValue = AccountType.全部;
			for (AccountType item : AccountType.values()) {
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
	public enum OrderStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		OrderStatus(byte value) {
			this.value = value;
		}

		public static OrderStatus parse(byte value) {
			OrderStatus retValue = OrderStatus.状态;
			for (OrderStatus item : OrderStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 充值类型：0、充值类型；1、充值钱包；2、充值游戏；4、钱包充值游戏；
	 */
	public enum ChargeType {
		充值类型((byte)0),充值钱包((byte)1),充值游戏((byte)2),钱包充值游戏((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ChargeType(byte value) {
			this.value = value;
		}

		public static ChargeType parse(byte value) {
			ChargeType retValue = ChargeType.充值类型;
			for (ChargeType item : ChargeType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 充值来源：0、充值来源；1、九亿网；2、手机；4、Android客户端；8、Iphone客户端；
	 */
	public enum PaySourceType {
		充值来源(0),九亿网(1),手机(2),Android客户端(4),Iphone客户端(8);
		
		private final int value;

		public int getValue() {
			return value;
		}

		PaySourceType(int value) {
			this.value = value;
		}

		public static PaySourceType parse(int value) {
			PaySourceType retValue = PaySourceType.充值来源;
			for (PaySourceType item : PaySourceType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


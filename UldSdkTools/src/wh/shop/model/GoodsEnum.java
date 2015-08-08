package wh.shop.model;

public class GoodsEnum {
	
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
	 * 活动类型：0、活动类型；1、热门商品；2、最新上架；4、促销；
	 */
	public enum ActiveType {
		活动类型((byte)0),热门商品((byte)1),最新上架((byte)2),促销((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ActiveType(byte value) {
			this.value = value;
		}

		public static ActiveType parse(byte value) {
			ActiveType retValue = ActiveType.活动类型;
			for (ActiveType item : ActiveType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 */
	public enum RecommendType {
		推荐类型((byte)0),推荐((byte)1),不推荐((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		RecommendType(byte value) {
			this.value = value;
		}

		public static RecommendType parse(byte value) {
			RecommendType retValue = RecommendType.推荐类型;
			for (RecommendType item : RecommendType.values()) {
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
	public enum GoodsStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GoodsStatus(byte value) {
			this.value = value;
		}

		public static GoodsStatus parse(byte value) {
			GoodsStatus retValue = GoodsStatus.状态;
			for (GoodsStatus item : GoodsStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


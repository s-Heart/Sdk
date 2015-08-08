package wh.shop.model;

public class FeedbackEnum {
	
	/**
	 * 问题类型：0、问题类型；1、平台相关；2、售后相关；4、订单相关；8、产品相关；16、其他；
	 */
	public enum FeedbackType {
		问题类型((byte)0),平台相关((byte)1),售后相关((byte)2),订单相关((byte)4),产品相关((byte)8),其他((byte)16);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		FeedbackType(byte value) {
			this.value = value;
		}

		public static FeedbackType parse(byte value) {
			FeedbackType retValue = FeedbackType.问题类型;
			for (FeedbackType item : FeedbackType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 问题处理状态：0、问题处理状态；1、已处理；2、未处理；
	 */
	public enum FeedbackDealType {
		问题处理状态((byte)0),已处理((byte)1),未处理((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		FeedbackDealType(byte value) {
			this.value = value;
		}

		public static FeedbackDealType parse(byte value) {
			FeedbackDealType retValue = FeedbackDealType.问题处理状态;
			for (FeedbackDealType item : FeedbackDealType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 管理问题类型：0、管理问题类型；1、提问；2、回复；
	 */
	public enum ManageType {
		管理问题类型((byte)0),提问((byte)1),回复((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ManageType(byte value) {
			this.value = value;
		}

		public static ManageType parse(byte value) {
			ManageType retValue = ManageType.管理问题类型;
			for (ManageType item : ManageType.values()) {
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
	public enum FeedbackStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		FeedbackStatus(byte value) {
			this.value = value;
		}

		public static FeedbackStatus parse(byte value) {
			FeedbackStatus retValue = FeedbackStatus.状态;
			for (FeedbackStatus item : FeedbackStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


package wh.shop.model;

public class CouponEnum {
	
	/**
	 * 优惠券类别：0、优惠券类别；1、平台卷；
	 */
	public enum CouponType {
		优惠券类别((byte)0),平台卷((byte)1);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		CouponType(byte value) {
			this.value = value;
		}

		public static CouponType parse(byte value) {
			CouponType retValue = CouponType.优惠券类别;
			for (CouponType item : CouponType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 是否被使用：0、是否被使用；1、未被使用；2、被使用；
	 */
	public enum UsedType {
		是否被使用((byte)0),未被使用((byte)1),被使用((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		UsedType(byte value) {
			this.value = value;
		}

		public static UsedType parse(byte value) {
			UsedType retValue = UsedType.是否被使用;
			for (UsedType item : UsedType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


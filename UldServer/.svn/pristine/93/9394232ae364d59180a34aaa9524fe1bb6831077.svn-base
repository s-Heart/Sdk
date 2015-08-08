package wh.member.model;

public class RefundEnum {
	
	/**
	 * 退款状态：0、退款状态；1、退款中；2、退款成功；4、退款失败；
	 */
	public enum RefundType {
		退款状态((byte)0),退款中((byte)1),退款成功((byte)2),退款失败((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		RefundType(byte value) {
			this.value = value;
		}

		public static RefundType parse(byte value) {
			RefundType retValue = RefundType.退款状态;
			for (RefundType item : RefundType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


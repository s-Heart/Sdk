package wh.promotion.model;

public class RegTemplateEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum RegTemplateStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		RegTemplateStatus(byte value) {
			this.value = value;
		}

		public static RegTemplateStatus parse(byte value) {
			RegTemplateStatus retValue = RegTemplateStatus.状态;
			for (RegTemplateStatus item : RegTemplateStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


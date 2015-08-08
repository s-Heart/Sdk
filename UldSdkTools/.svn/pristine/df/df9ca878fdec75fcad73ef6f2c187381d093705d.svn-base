package wh.promotion.model;

public class PosterCategoryEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum PosterCategoryStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		PosterCategoryStatus(byte value) {
			this.value = value;
		}

		public static PosterCategoryStatus parse(byte value) {
			PosterCategoryStatus retValue = PosterCategoryStatus.状态;
			for (PosterCategoryStatus item : PosterCategoryStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


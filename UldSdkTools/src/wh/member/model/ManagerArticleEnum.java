package wh.member.model;

public class ManagerArticleEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum ManagerArticleStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ManagerArticleStatus(byte value) {
			this.value = value;
		}

		public static ManagerArticleStatus parse(byte value) {
			ManagerArticleStatus retValue = ManagerArticleStatus.状态;
			for (ManagerArticleStatus item : ManagerArticleStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


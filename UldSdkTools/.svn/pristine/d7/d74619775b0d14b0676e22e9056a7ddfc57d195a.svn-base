package wh.member.model;

public class FortuneEnum {
	
	/**
	 * 幸运点类型：0、幸运点类型；1、幸运点；2、红包；
	 */
	public enum FortuneType {
		幸运点类型((byte)0),幸运点((byte)1),红包((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		FortuneType(byte value) {
			this.value = value;
		}

		public static FortuneType parse(byte value) {
			FortuneType retValue = FortuneType.幸运点类型;
			for (FortuneType item : FortuneType.values()) {
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
	public enum FortuneStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		FortuneStatus(byte value) {
			this.value = value;
		}

		public static FortuneStatus parse(byte value) {
			FortuneStatus retValue = FortuneStatus.状态;
			for (FortuneStatus item : FortuneStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


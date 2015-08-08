package wh.member.model;

public class FortuneDetailEnum {
	
	/**
	 * 幸运点获得方式：0、幸运点获得方式；1、充值；2、登录签到；3、注册；4、一元红包；5、满月红包；6、生日红包；
	 */
	public enum FortuneGetType {
		幸运点获得方式((byte)0),充值((byte)1),登录签到((byte)2),注册((byte)3),一元红包((byte)4),满月红包((byte)5),生日红包((byte)6);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		FortuneGetType(byte value) {
			this.value = value;
		}

		public static FortuneGetType parse(byte value) {
			FortuneGetType retValue = FortuneGetType.幸运点获得方式;
			for (FortuneGetType item : FortuneGetType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 幸运点类型：0、幸运点类型；1、幸运点；
	 */
	public enum FortuneType {
		幸运点类型((byte)0),幸运点((byte)1);
		
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
	 * 使用类型：0、使用类型；1、获得；2、消费；
	 */
	public enum UseType {
		使用类型((byte)0),获得((byte)1),消费((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		UseType(byte value) {
			this.value = value;
		}

		public static UseType parse(byte value) {
			UseType retValue = UseType.使用类型;
			for (UseType item : UseType.values()) {
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
	public enum FortuneDetailStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		FortuneDetailStatus(byte value) {
			this.value = value;
		}

		public static FortuneDetailStatus parse(byte value) {
			FortuneDetailStatus retValue = FortuneDetailStatus.状态;
			for (FortuneDetailStatus item : FortuneDetailStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


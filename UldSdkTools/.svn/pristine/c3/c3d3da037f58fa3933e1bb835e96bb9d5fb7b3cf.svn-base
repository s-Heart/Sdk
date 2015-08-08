package wh.member.model;

public class RebateEnum {
	
	/**
	 * 返利进度类型：0、返利进度类型；1、未开始执行；2、执行中；4、执行结束；
	 */
	public enum ProcessType {
		返利进度类型((byte)0),未开始执行((byte)1),执行中((byte)2),执行结束((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ProcessType(byte value) {
			this.value = value;
		}

		public static ProcessType parse(byte value) {
			ProcessType retValue = ProcessType.返利进度类型;
			for (ProcessType item : ProcessType.values()) {
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
	public enum RebateStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		RebateStatus(byte value) {
			this.value = value;
		}

		public static RebateStatus parse(byte value) {
			RebateStatus retValue = RebateStatus.状态;
			for (RebateStatus item : RebateStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


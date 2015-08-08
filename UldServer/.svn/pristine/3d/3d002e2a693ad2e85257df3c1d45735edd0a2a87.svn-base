package wh.member.model;

public class RebateDetailEnum {
	
	/**
	 * 返利执行情况：0、返利执行情况；1、执行成功；2、执行失败；
	 */
	public enum ExecuteType {
		返利执行情况((byte)0),执行成功((byte)1),执行失败((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ExecuteType(byte value) {
			this.value = value;
		}

		public static ExecuteType parse(byte value) {
			ExecuteType retValue = ExecuteType.返利执行情况;
			for (ExecuteType item : ExecuteType.values()) {
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
	public enum RebateDetailStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		RebateDetailStatus(byte value) {
			this.value = value;
		}

		public static RebateDetailStatus parse(byte value) {
			RebateDetailStatus retValue = RebateDetailStatus.状态;
			for (RebateDetailStatus item : RebateDetailStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


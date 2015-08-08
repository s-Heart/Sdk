package wh.game.model;

public class ServerEnum {
	
	/**
	 * 线路类型：0、线路类型；1、电信；2、网通；4、双线；
	 */
	public enum LineType {
		线路类型((byte)0),电信((byte)1),网通((byte)2),双线((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		LineType(byte value) {
			this.value = value;
		}

		public static LineType parse(byte value) {
			LineType retValue = LineType.线路类型;
			for (LineType item : LineType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 启用状态：0、启用状态；1、开启_时间无关；2、即将开启_时间相关；4、停止_维护；8、内测_需要激活码且时间相关；
	 */
	public enum EnableType {
		启用状态((byte)0),开启_时间无关((byte)1),即将开启_时间相关((byte)2),停止_维护((byte)4),内测_需要激活码且时间相关((byte)8);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		EnableType(byte value) {
			this.value = value;
		}

		public static EnableType parse(byte value) {
			EnableType retValue = EnableType.启用状态;
			for (EnableType item : EnableType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 充值类型：0、充值类型；1、允许充值；2、禁用充值；
	 */
	public enum RechargeType {
		充值类型((byte)0),允许充值((byte)1),禁用充值((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		RechargeType(byte value) {
			this.value = value;
		}

		public static RechargeType parse(byte value) {
			RechargeType retValue = RechargeType.充值类型;
			for (RechargeType item : RechargeType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 */
	public enum RecommendType {
		推荐类型((byte)0),推荐((byte)1),不推荐((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		RecommendType(byte value) {
			this.value = value;
		}

		public static RecommendType parse(byte value) {
			RecommendType retValue = RecommendType.推荐类型;
			for (RecommendType item : RecommendType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 启用消息类型：0、启用消息类型；1、启用；2、不启用；
	 */
	public enum EnableMessageType {
		启用消息类型((byte)0),启用((byte)1),不启用((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		EnableMessageType(byte value) {
			this.value = value;
		}

		public static EnableMessageType parse(byte value) {
			EnableMessageType retValue = EnableMessageType.启用消息类型;
			for (EnableMessageType item : EnableMessageType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 服务器类型：0、服务器类型；1、普通；2、新服；4、维护；8、火爆；
	 */
	public enum ServerType {
		服务器类型((byte)0),普通((byte)1),新服((byte)2),维护((byte)4),火爆((byte)8);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ServerType(byte value) {
			this.value = value;
		}

		public static ServerType parse(byte value) {
			ServerType retValue = ServerType.服务器类型;
			for (ServerType item : ServerType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 状态：0、状态；1、显示；2、不显示；
	 */
	public enum ServerStatus	{
		状态((byte)0),显示((byte)1),不显示((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ServerStatus(byte value) {
			this.value = value;
		}

		public static ServerStatus parse(byte value) {
			ServerStatus retValue = ServerStatus.状态;
			for (ServerStatus item : ServerStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


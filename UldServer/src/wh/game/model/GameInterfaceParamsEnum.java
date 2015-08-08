package wh.game.model;

public class GameInterfaceParamsEnum {
	
	/**
	 * 签名类型：0、签名类型；1、参与签名；2、不参与签名；4、参与Base64签名；
	 */
	public enum SignType {
		签名类型((byte)0),参与签名((byte)1),不参与签名((byte)2),参与Base64签名((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		SignType(byte value) {
			this.value = value;
		}

		public static SignType parse(byte value) {
			SignType retValue = SignType.签名类型;
			for (SignType item : SignType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 参数类型：0、参数类型；1、传入参数；2、返回参数；
	 */
	public enum ParamType {
		参数类型((byte)0),传入参数((byte)1),返回参数((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ParamType(byte value) {
			this.value = value;
		}

		public static ParamType parse(byte value) {
			ParamType retValue = ParamType.参数类型;
			for (ParamType item : ParamType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 传入参数类型：0、传入参数类型；1、帐号；2、时间；3、登录状态；4、游戏；5、服务器；6、联动商编号；7、防沉迷标识；8、签名；9、订单号；10、充值金额；11、签名Key；12、元宝；13、客户端IP；14、Base64签名；15、子渠道编号；
	 */
	public enum ParamInType {
		传入参数类型((byte)0),帐号((byte)1),时间((byte)2),登录状态((byte)3),游戏((byte)4),服务器((byte)5),联动商编号((byte)6),防沉迷标识((byte)7),签名((byte)8),订单号((byte)9),充值金额((byte)10),签名Key((byte)11),元宝((byte)12),客户端IP((byte)13),Base64签名((byte)14),子渠道编号((byte)15);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ParamInType(byte value) {
			this.value = value;
		}

		public static ParamInType parse(byte value) {
			ParamInType retValue = ParamInType.传入参数类型;
			for (ParamInType item : ParamInType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 传出参数类型：0、传出参数类型；1、成功；2、失败；
	 */
	public enum ParamOutType {
		传出参数类型((byte)0),成功((byte)1),失败((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ParamOutType(byte value) {
			this.value = value;
		}

		public static ParamOutType parse(byte value) {
			ParamOutType retValue = ParamOutType.传出参数类型;
			for (ParamOutType item : ParamOutType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


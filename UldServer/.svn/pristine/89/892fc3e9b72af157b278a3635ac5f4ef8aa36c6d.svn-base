package wh.promotion.model;

public class ChannelInterfaceParamsEnum {
	
	/**
	 * 签名类型：0、签名类型；1、参与签名；2、不参与签名；
	 */
	public enum SignType {
		签名类型((byte)0),参与签名((byte)1),不参与签名((byte)2);
		
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
	 * 传入参数类型：0、传入参数类型；1、合作方用户帐号编号；2、广告验证码常量；3、用户编号；4、用户名称；5、签名；6、签名Key；
	 */
	public enum CIInType {
		传入参数类型((byte)0),合作方用户帐号编号((byte)1),广告验证码常量((byte)2),用户编号((byte)3),用户名称((byte)4),签名((byte)5),签名Key((byte)6);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		CIInType(byte value) {
			this.value = value;
		}

		public static CIInType parse(byte value) {
			CIInType retValue = CIInType.传入参数类型;
			for (CIInType item : CIInType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 传出参数类型：0、传出参数类型；
	 */
	public enum CIOutType {
		传出参数类型((byte)0);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		CIOutType(byte value) {
			this.value = value;
		}

		public static CIOutType parse(byte value) {
			CIOutType retValue = CIOutType.传出参数类型;
			for (CIOutType item : CIOutType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


package wh.game.model;

public class GameInterfaceEnum {
	
	/**
	 * 接口类型：0、接口类型；1、登录；2、充值；4、查询帐号是否存在；8、获取游角色与等级；
	 */
	public enum InterfaceType {
		接口类型((byte)0),登录((byte)1),充值((byte)2),查询帐号是否存在((byte)4),获取游角色与等级((byte)8);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		InterfaceType(byte value) {
			this.value = value;
		}

		public static InterfaceType parse(byte value) {
			InterfaceType retValue = InterfaceType.接口类型;
			for (InterfaceType item : InterfaceType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 充值单位：0、充值单位；1、分；2、元；
	 */
	public enum PayUnitType {
		充值单位((byte)0),分((byte)1),元((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		PayUnitType(byte value) {
			this.value = value;
		}

		public static PayUnitType parse(byte value) {
			PayUnitType retValue = PayUnitType.充值单位;
			for (PayUnitType item : PayUnitType.values()) {
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
	public enum GameInterfaceStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GameInterfaceStatus(byte value) {
			this.value = value;
		}

		public static GameInterfaceStatus parse(byte value) {
			GameInterfaceStatus retValue = GameInterfaceStatus.状态;
			for (GameInterfaceStatus item : GameInterfaceStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


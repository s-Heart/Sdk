package wh.server.model;

public class ServerAccountEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum ServerAccountStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ServerAccountStatus(byte value) {
			this.value = value;
		}

		public static ServerAccountStatus parse(byte value) {
			ServerAccountStatus retValue = ServerAccountStatus.状态;
			for (ServerAccountStatus item : ServerAccountStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 游戏币类型：0、游戏币类型；1、元宝；2、金子；3、仙币；4、点卷；
	 */
	public enum GameMoneyType {
		游戏币类型((byte)0),元宝((byte)1),金子((byte)2),仙币((byte)3),点卷((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GameMoneyType(byte value) {
			this.value = value;
		}

		public static GameMoneyType parse(byte value) {
			GameMoneyType retValue = GameMoneyType.游戏币类型;
			for (GameMoneyType item : GameMoneyType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


package wh.game.model;

public class GameCategoryEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum GameCategoryStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GameCategoryStatus(byte value) {
			this.value = value;
		}

		public static GameCategoryStatus parse(byte value) {
			GameCategoryStatus retValue = GameCategoryStatus.状态;
			for (GameCategoryStatus item : GameCategoryStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


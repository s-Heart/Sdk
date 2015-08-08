package wh.game.model;

public class GameEnum {
	
	/**
	 * 上下架状态：0、上下架状态；1、上架；2、下架；
	 */
	public enum GameType {
		上下架状态((byte)0),上架((byte)1),下架((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GameType(byte value) {
			this.value = value;
		}

		public static GameType parse(byte value) {
			GameType retValue = GameType.上下架状态;
			for (GameType item : GameType.values()) {
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
	 * 状态：0、状态；1、显示；2、不显示；
	 */
	public enum GameStatus	{
		状态((byte)0),显示((byte)1),不显示((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GameStatus(byte value) {
			this.value = value;
		}

		public static GameStatus parse(byte value) {
			GameStatus retValue = GameStatus.状态;
			for (GameStatus item : GameStatus.values()) {
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
}


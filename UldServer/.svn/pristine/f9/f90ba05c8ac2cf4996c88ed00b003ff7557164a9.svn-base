package wh.content.model;

public class ContentEnum {
	
	/**
	 * 外键表名称类型：0、外键表名称类型；1、服务器；2、游戏；
	 */
	public enum FKTableNameType {
		外键表名称类型((byte)0),服务器((byte)1),游戏((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		FKTableNameType(byte value) {
			this.value = value;
		}

		public static FKTableNameType parse(byte value) {
			FKTableNameType retValue = FKTableNameType.外键表名称类型;
			for (FKTableNameType item : FKTableNameType.values()) {
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
	 * 显示位置：0、显示位置；1、首页；2、新闻页；
	 */
	public enum DisplayPositionType {
		显示位置((byte)0),首页((byte)1),新闻页((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		DisplayPositionType(byte value) {
			this.value = value;
		}

		public static DisplayPositionType parse(byte value) {
			DisplayPositionType retValue = DisplayPositionType.显示位置;
			for (DisplayPositionType item : DisplayPositionType.values()) {
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
	public enum ContentStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ContentStatus(byte value) {
			this.value = value;
		}

		public static ContentStatus parse(byte value) {
			ContentStatus retValue = ContentStatus.状态;
			for (ContentStatus item : ContentStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


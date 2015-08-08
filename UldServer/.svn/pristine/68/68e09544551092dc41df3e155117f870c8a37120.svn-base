package wh.shop.model;

public class GoodsCommentEnum {
	
	/**
	 * 星级数量：0、星级数量；1、一个星；2、一个半星；4、二个星；8、二个半星；16、三个星；32、三个半星；64、四个星；128、四个半星；256、五个星；
	 */
	public enum StarType {
		星级数量(0),一个星(1),一个半星(2),二个星(4),二个半星(8),三个星(16),三个半星(32),四个星(64),四个半星(128),五个星(256);
		
		private final int value;

		public int getValue() {
			return value;
		}

		StarType(int value) {
			this.value = value;
		}

		public static StarType parse(int value) {
			StarType retValue = StarType.星级数量;
			for (StarType item : StarType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 评论类型：0、评论类型；1、好评；2、差评；3、其他；
	 */
	public enum CommentType {
		评论类型((byte)0),好评((byte)1),差评((byte)2),其他((byte)3);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		CommentType(byte value) {
			this.value = value;
		}

		public static CommentType parse(byte value) {
			CommentType retValue = CommentType.评论类型;
			for (CommentType item : CommentType.values()) {
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
	public enum GoodsCommentStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GoodsCommentStatus(byte value) {
			this.value = value;
		}

		public static GoodsCommentStatus parse(byte value) {
			GoodsCommentStatus retValue = GoodsCommentStatus.状态;
			for (GoodsCommentStatus item : GoodsCommentStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


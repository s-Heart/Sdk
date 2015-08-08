package wh.promotion.model;

public class PosterEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum PosterStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		PosterStatus(byte value) {
			this.value = value;
		}

		public static PosterStatus parse(byte value) {
			PosterStatus retValue = PosterStatus.状态;
			for (PosterStatus item : PosterStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 海报文件类型：0、海报文件类型；1、Rar；2、Zip；3、Swf；
	 */
	public enum PosterFileType {
		海报文件类型((byte)0),Rar((byte)1),Zip((byte)2),Swf((byte)3);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		PosterFileType(byte value) {
			this.value = value;
		}

		public static PosterFileType parse(byte value) {
			PosterFileType retValue = PosterFileType.海报文件类型;
			for (PosterFileType item : PosterFileType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


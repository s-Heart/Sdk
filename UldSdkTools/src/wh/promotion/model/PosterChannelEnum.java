package wh.promotion.model;

public class PosterChannelEnum {
	
	/**
	 * 状态：0、状态；1、有效；2、无效；
	 */
	public enum PosterChannelStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		PosterChannelStatus(byte value) {
			this.value = value;
		}

		public static PosterChannelStatus parse(byte value) {
			PosterChannelStatus retValue = PosterChannelStatus.状态;
			for (PosterChannelStatus item : PosterChannelStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 注册框类型：0、注册框类型；1、宿主；2、弹出；4、居中；8、右下；16、左下；32、右上；64、自定义；128、中下；
	 */
	public enum RegisterCodeType {
		注册框类型((byte)0),宿主((byte)1),弹出((byte)2),居中((byte)4),右下((byte)8),左下((byte)16),右上((byte)32),自定义((byte)64),中下((byte)128);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		RegisterCodeType(byte value) {
			this.value = value;
		}

		public static RegisterCodeType parse(byte value) {
			RegisterCodeType retValue = RegisterCodeType.注册框类型;
			for (RegisterCodeType item : RegisterCodeType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 标题类型：0、标题类型；1、动态；2、静态；
	 */
	public enum TitleType {
		标题类型((byte)0),动态((byte)1),静态((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		TitleType(byte value) {
			this.value = value;
		}

		public static TitleType parse(byte value) {
			TitleType retValue = TitleType.标题类型;
			for (TitleType item : TitleType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


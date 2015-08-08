package wh.member.model;

public class HBUserEnum {
	
	/**
	 * 处理状态：0、处理状态；1、未处理；2、已处理；4、登录未处理；8、登录已处理；16、登录游戏未处理；32、登录游戏已处理；64、充值ChargedDays未处理；128、充值ChargedDays已处理；129、BBS用户同步未处理；130、BBS用户同步已处理；
	 */
	public enum DealType {
		处理状态((byte)0),未处理((byte)1),已处理((byte)2),登录未处理((byte)4),登录已处理((byte)8),登录游戏未处理((byte)16),登录游戏已处理((byte)32),充值ChargedDays未处理((byte)64),充值ChargedDays已处理((byte)128),BBS用户同步未处理((byte)129),BBS用户同步已处理((byte)130);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		DealType(byte value) {
			this.value = value;
		}

		public static DealType parse(byte value) {
			DealType retValue = DealType.处理状态;
			for (DealType item : DealType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


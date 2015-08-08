package wh.member.model;

public class ManagerEnum {
	
	/**
	 * 性别：0、性别；1、男；2、女；
	 */
	public enum GenderType {
		性别((byte)0),男((byte)1),女((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		GenderType(byte value) {
			this.value = value;
		}

		public static GenderType parse(byte value) {
			GenderType retValue = GenderType.性别;
			for (GenderType item : GenderType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 管理员类型：0、管理员类型；1、客服；2、内容管理员；4、海报管理员；8、游戏管理员；16、用户管理员；32、统计分析管理员；64、管理员；128、超级管理员；256、渠道推广搜索；512、渠道推广联盟；1024、渠道推广垂媒；2048、渠道推广自然；4096、渠道推广全部；8192、返利管理员；16384、GS前台管理员；32768、GS后台管理员；
	 */
	public enum ManagerType {
		管理员类型(0),客服(1),内容管理员(2),海报管理员(4),游戏管理员(8),用户管理员(16),统计分析管理员(32),管理员(64),超级管理员(128),渠道推广搜索(256),渠道推广联盟(512),渠道推广垂媒(1024),渠道推广自然(2048),渠道推广全部(4096),返利管理员(8192),GS前台管理员(16384),GS后台管理员(32768);
		
		private final int value;

		public int getValue() {
			return value;
		}

		ManagerType(int value) {
			this.value = value;
		}

		public static ManagerType parse(int value) {
			ManagerType retValue = ManagerType.管理员类型;
			for (ManagerType item : ManagerType.values()) {
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
	public enum ManagerStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ManagerStatus(byte value) {
			this.value = value;
		}

		public static ManagerStatus parse(byte value) {
			ManagerStatus retValue = ManagerStatus.状态;
			for (ManagerStatus item : ManagerStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 访问权限：0、访问权限；1、查询；2、编辑；4、删除；
	 */
	public enum AccessType {
		访问权限((byte)0),查询((byte)1),编辑((byte)2),删除((byte)4);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		AccessType(byte value) {
			this.value = value;
		}

		public static AccessType parse(byte value) {
			AccessType retValue = AccessType.访问权限;
			for (AccessType item : AccessType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


package wh.member.model;

public class UserMessageEnum {
	
	/**
	 * 已读类型：0、是否已读；1、已读；2、未读；
	 */
	public enum ReadType {
		是否已读((byte)0),已读((byte)1),未读((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		ReadType(byte value) {
			this.value = value;
		}

		public static ReadType parse(byte value) {
			ReadType retValue = ReadType.是否已读;
			for (ReadType item : ReadType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 消息传输方式：0、消息传输方式；1、接收；2、发送；
	 */
	public enum TransferType {
		消息传输方式((byte)0),接收((byte)1),发送((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		TransferType(byte value) {
			this.value = value;
		}

		public static TransferType parse(byte value) {
			TransferType retValue = TransferType.消息传输方式;
			for (TransferType item : TransferType.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	/**
	 * 消息类型：0、消息类型；1、系统；2、个人；
	 */
	public enum MessageType {
		消息类型((byte)0),系统((byte)1),个人((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		MessageType(byte value) {
			this.value = value;
		}

		public static MessageType parse(byte value) {
			MessageType retValue = MessageType.消息类型;
			for (MessageType item : MessageType.values()) {
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
	public enum UserMessageStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		UserMessageStatus(byte value) {
			this.value = value;
		}

		public static UserMessageStatus parse(byte value) {
			UserMessageStatus retValue = UserMessageStatus.状态;
			for (UserMessageStatus item : UserMessageStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


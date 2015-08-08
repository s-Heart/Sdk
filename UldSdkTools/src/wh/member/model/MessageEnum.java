package wh.member.model;

public class MessageEnum {
	
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
	public enum MessageStatus	{
		状态((byte)0),有效((byte)1),无效((byte)2);
		
		private final byte value;

		public byte getValue() {
			return value;
		}

		MessageStatus(byte value) {
			this.value = value;
		}

		public static MessageStatus parse(byte value) {
			MessageStatus retValue = MessageStatus.状态;
			for (MessageStatus item : MessageStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}


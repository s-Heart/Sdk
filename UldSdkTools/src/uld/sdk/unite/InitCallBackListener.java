package uld.sdk.unite;
public interface InitCallBackListener {
	public enum ULDSDKCallBackStatus {
		DEFAULT((byte)0),INIT_NO((byte)1),INIT_SUCCESS((byte)2),INIT_FAILED((byte)3),LOGIN_NO((byte)4),
		LOGIN_SUCCESS((byte)5),LOGIN_FAILED((byte)6);
		private final byte value;

		public byte getValue() {
			return value;
		}

		ULDSDKCallBackStatus(byte value) {
			this.value = value;
		}

		public static ULDSDKCallBackStatus parse(byte value) {
			ULDSDKCallBackStatus retValue = ULDSDKCallBackStatus.DEFAULT;
			for (ULDSDKCallBackStatus item : ULDSDKCallBackStatus.values()) {
				if (value == item.getValue()) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
	
	public void onInitFinished(InitResult initResult);
}

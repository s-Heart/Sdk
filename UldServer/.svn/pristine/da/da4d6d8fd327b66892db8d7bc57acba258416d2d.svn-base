package wh.promotion.model;

import java.io.Serializable;

public class MobileDeviceEnum implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 设备类型：0、设备类型；1、Iphone；2、Samsung；3、Nokia；4、lenovo；5、HTC；6、Motorola；7、BlackBerry；8、Sony；9、LG；10、HuaWei；11、XM；12、ZTE；13、Touch；14、Gionee；
	 * 15、ERICSSON；16、其它；
	 * 
	 * @author JujuLee Email:jujulee@foxmail.com
	 * @date 2012-12-8
	 */
	public enum DeviceType {
		设备类型(0), Iphone(1), Samsung(2), Nokia(3), Lenovo(4), HTC(5), Motorola(6), BlackBerry(7), Sony(8), LG(9), HuaWei(10), XM(
				11), ZTE(12), Touch(13), Gionee(14), ERICSSON(15), 其它(16);

		private final int value;

		public int getValue() {
			return value;
		}

		DeviceType(int value) {
			this.value = value;
		}

		public static DeviceType parse(String value) {
			DeviceType retValue = null;
			for (DeviceType item : DeviceType.values()) {
				if (Integer.toString(item.getValue()).equals(value)) {
					retValue = item;
					break;
				}
			}

			return retValue;
		}
	}
}

package uld.sdk.bll;

import java.util.Date;
import java.util.logging.Level;

import uld.sdk.model.MessageReturn;
import uld.sdk.tools.LogHelper;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;

public class StatisticAnalysis {
	private static StatisticAnalysis instance = new StatisticAnalysis();

	public static StatisticAnalysis getInstance() {
		return instance;
	}

	private RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);

	/**
	 * 统计客户端下载量
	 * 
	 * @param gameId
	 * @param channelId
	 * @param channelSubId
	 * @param mobileDevice
	 * @return
	 */
	public MessageReturn Stat(int gameId, int channelId, int channelSubId, wh.promotion.model.MobileDevice mobileDevice) {
		MessageReturn messageReturn = new MessageReturn();
		try {
			// 判断mobileDeviceName是否在数据库中已经存在，如果存在，则取出数据库中的值，然后进行更新
			wh.promotion.model.MobileDevice tempMobileDevice = wh.promotion.bll.MobileDevice.getInstance().getByName(mobileDevice.getMobileDeviceName(), "", 1, refMyErr);
			if (tempMobileDevice != null) {
				mobileDevice.setMobileDeviceId(tempMobileDevice.getMobileDeviceId());
				mobileDevice.setCreateDate(tempMobileDevice.getCreateDate());
			}

			Date date = new Date();
			if (mobileDevice.getMobileDeviceId() == 0) {
				mobileDevice.setCreateDate(date);
			}
			mobileDevice.setModifyDate(date);
			int mobileDeviceId = wh.promotion.bll.MobileDevice.getInstance().createUpdate(mobileDevice, refMyErr);

			MyErr myErr = refMyErr.argvalue;
			if (myErr.findErr()) {
				messageReturn = new MessageReturn(myErr.getErrNo(), myErr.getErrMsg());
			} else {
				wh.promotion.model.StatisticAnalysis statisticAnalysis = new wh.promotion.model.StatisticAnalysis();

				statisticAnalysis.setChannelId(channelId);
				statisticAnalysis.setChannelSubId(channelSubId);
				if (channelId > 0 || channelSubId > 0) {
					statisticAnalysis.setChannelType((byte) 2);
				} else {
					statisticAnalysis.setChannelType((byte) 1);
				}
				statisticAnalysis.setCreateDate(date);
				statisticAnalysis.setMobileDeviceId(mobileDeviceId);
				statisticAnalysis.setGameId(gameId);

				int statisticAnalysisId = wh.promotion.bll.StatisticAnalysis.getInstance().createUpdate(statisticAnalysis, refMyErr);
				uld.sdk.model.StatisticAnalysis sAnalysis = new uld.sdk.model.StatisticAnalysis(mobileDeviceId, statisticAnalysisId);

				messageReturn.setRetObject(sAnalysis);
			}
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		return messageReturn;
	}

	/**
	 * 记录客户端发生的异常信息
	 * 
	 * @param deviceId
	 *            客户端唯一编号
	 * @param mobileDeviceId
	 *            客户端对应的服务器端的唯一编号
	 * @param e
	 *            客户端对应的异常信息
	 * @return
	 */
	public boolean logErr(String deviceId, int mobileDeviceId, Throwable e) {
		String msg = "客户端设备编号：" + deviceId + "\r\n";
		msg += "客户端设备对应服务端编号：" + mobileDeviceId + "\r\n";
		msg += e.getMessage() + "\r\n";
		LogHelper.log(Level.SEVERE, msg, e);
		return true;
	}
}

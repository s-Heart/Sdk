package com.nearme.gamecenter.open.demo.nearme.gamecenter;

import android.app.Application;

import com.nearme.gamecenter.open.api.GameCenterSDK;
import com.nearme.gamecenter.open.api.GameCenterSettings;

public class DemoApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		initSDK();
	}
	private void initSDK() {
		// 测试用的appkey和secret
		// TODO 这个里的为测试key和secret，请务必替换为正式的！
		GameCenterSettings gameCenterSettings = new GameCenterSettings(
				"c5217trjnrmU6gO5jG8VvUFU0", "e2eCa732422245E8891F6555e999878B") {

			@Override
			public void onForceReLogin() {
				// sdk由于某些原因登出,此方法通知cp,cp需要在此处清理当前的登录状态并重新请求登录.
				// 可以发广播通知页面重新登录
			}
			
			@Override 
			public void onForceUpgradeCancel() {
				// 游戏自升级，后台有设置为强制升级，用户点击取消时的回调函数。
				// 若开启强制升级模式 ，  一般要求不更新则强制退出游戏并杀掉进程。
				// System.exit(0) or kill this process
			}
		};
		// TODO for test old
//		AccountAgent.useNewApi = true;
		GameCenterSettings.isDebugModel = true;// 测试log开关
		GameCenterSettings.isOritationPort = true;// 控制SDK activity的横竖屏 true为竖屏
		GameCenterSDK.init(gameCenterSettings, this);
	}
}

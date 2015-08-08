package uld.sdk.others;

import android.content.Context;
import uld.sdk.LoginActivity;
import uld.sdk.RechargeActivity;
import uld.sdk.RegisterResultActivity;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.UldPlatform;

public class SdkUld {

	private static SdkUld sInstance = null;
	private static int gameId = 0;
	private static int serverId = 0;

	public static SdkUld getInstance() {
		if (sInstance == null) {
			sInstance = new SdkUld();
		}
		return sInstance;
	}

	// 初始化
	public void init(GameInfo gameInfo) {
		gameId = gameInfo.getGameId();
		serverId = gameInfo.getServerId();
	}

	// 登录游戏
	public void login(LoginCallBackListener loginCallBackListener) {
		LoginActivity.show(UldPlatform.sContext, gameId, serverId, loginCallBackListener);
	}

	// 支付金额
	public void recharge(Context context, RechargeCallBackListener rechargeCallBackListener) {
		gameId = UldPlatform.gameInfo.getGameId();
		serverId = UldPlatform.gameInfo.getServerId();
		RechargeActivity.show(context, gameId, serverId, rechargeCallBackListener);
	}

	// 查看账户信息
	public void viewUser(Context context) {
		RegisterResultActivity.show(UldPlatform.sContext);
	}

	// 退出游戏
	public void finishGame(Context context) {
		// uld.sdkui.FinishGameActivity.show(UldPlatform.sContext);
	}

}

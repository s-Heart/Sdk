package uld.sdk;

import uld.sdk.tools.Utility;
import android.os.Bundle;


public class DispathActivity extends BaseActivity{

	private int channelId = 0;
	private int channelSubId = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String channelData = getDataFromAsset("Channel.txt").trim();
		if (!Utility.isEmpty(channelData)) {
			String[] channelDatas = channelData.split("_");
			if (channelDatas.length >= 2) {
				channelId = Utility.getInt(channelDatas[0]);
				channelSubId = Utility.getInt(channelDatas[1]);
			}
		}
		
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void dispathSdk(int channelId, int channelSubId) {
		if (channelSubId == 0) {
			goToUldSdk();
		}else {
			//获取子渠道接入方法名称
			
		}

		
	}
	
	
	private void goToUldSdk() {
		
	}
	
	
}

package uld.sdk;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import wh.game.model.Game;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPasswordActivity extends BaseActivity {
	private Button btnBack = null;
	private TextView tvServiceHotline = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int widthDivideDensity = (int) (outMetrics.widthPixels / outMetrics.scaledDensity);

		if (widthDivideDensity <= valueOfWidthDivideDensity) {
			setContentView(R.layout.activity_forgetpassword_320);
		} else {
			setContentView(R.layout.activity_forgetpassword);
		}

		findViews();

		setListeners();
	}

	private void findViews() {
		btnBack = (Button) findViewById(R.id.btnBack);

		tvServiceHotline = (TextView) findViewById(R.id.tvServiceHotline);
		// 动态获取客服电话已修复 2013年11月20日10:48:52
		// String content = BaseActivity.getContent();
		String content = "";
		if (BaseActivity.getContent().equals("")) {
			MessageHeader messageHeader = new MessageHeader();
			messageHeader.init();
			MessageBody messageBody = new MessageBody();
			messageBody.setClassPathName("wh.game.bll.Game");
			messageBody.setMethodName("get");
			messageBody.setParmList(new Object[] { getGameId() });
			messageBody.setParmTypeList(new Class<?>[] { int.class });
			MessageReturn messageReturn = ThreadManager.sendMessage(
					messageHeader, messageBody);
			if (messageReturn != null) {

				if (messageReturn.getErrNo() == 0) {
					wh.game.model.Game game = (Game) messageReturn
							.getRetObject();
					if (game != null) {
						// BaseActivity.setContent(game.getContent());
						String sss = BaseActivity.getContent();
						Log.d("basecontent", sss);
						content = game.getContent();
					} else {
						showToastThread(messageReturn.getErrMsg(),
								Toast.LENGTH_LONG);
					}

				} else {
					showToastThread(messageReturn.getErrMsg(),
							Toast.LENGTH_LONG);
				}
			} else {
				showToastThread("处理消息失败", Toast.LENGTH_LONG);
			}
		}else {
			//2013年11月20日17:09:38 将客服电话传过来
			content=BaseActivity.getContent();
		}
		String[] contents = content.split("\\|_\\|");
		String servicehotline = contents[0].replace("hotline=", "客服电话:");

		tvServiceHotline.setText(servicehotline);
	}

	private void setListeners() {
		if (btnBack != null) {
			btnBack.setOnClickListener(new BackClickListener());
		}
	}

	class BackClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}
}

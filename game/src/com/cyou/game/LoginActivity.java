package com.cyou.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyouwanwan.sdk.GameLib;
import com.cyouwanwan.sdk.callback.LoginCallback;
import com.cyouwanwan.sdk.util.Utils;

public class LoginActivity extends Activity implements OnClickListener {
	private Button loginBtn;
	private EditText successEditText;
	private EditText failEditText;
	private EditText cancleEditText;
	private TextView resultTextView;
	private boolean isFromReg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_main);

		initView();
	}

	private void initView() {
		loginBtn = (Button) findViewById(R.id.button);
		successEditText = (EditText) findViewById(R.id.success);
		failEditText = (EditText) findViewById(R.id.fail);
		cancleEditText = (EditText) findViewById(R.id.cancle);
		resultTextView = (TextView) findViewById(R.id.result);
		loginBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			GameLib.getInstance(this).login(new LoginCallback() {

				@Override
				public void onLoginSuccess(String token) {
//					if (isFromReg) {
						String successStr = successEditText.getText().toString().trim();
						Log.d("test","token=" + token);
						Log.d("test","successStr=" +successStr);
						if (!Utils.isEmpty(successStr)) {
//							Utils.toast(getApplicationContext(), successStr);
						}
						resultTextView.setText("success!返回的token 为" + token);
//					}else {
//						isFromReg=!isFromReg;
//					}
				}

				@Override
				public void onError() {
					String failStr = failEditText.getText().toString().trim();
					if (!Utils.isEmpty(failStr)) {
//						Utils.toast(getApplicationContext(), failStr);
					}
					resultTextView.setText("登录失败");
				}

				@Override
				public void onCancel() {
//					isFromReg = true;
					String cancleStr = cancleEditText.getText().toString().trim();
					if (!Utils.isEmpty(cancleStr)) {
//						Utils.toast(getApplicationContext(), cancleStr);
					}
					resultTextView.setText("关闭登录框");
//					if (isFromReg) {
//						Log.d("37wanwan-onCancel", "注销操作");
//						isFromReg = !isFromReg;
//					}
				}
			});
			break;

		}
	}

}

package com.nearme.gamecenter.open.demo.nearme.gamecenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.nearme.gamecenter.open.api.ApiCallback;
import com.nearme.gamecenter.open.api.GameCenterSDK;
import com.nearme.gamecenter.open.api.GameCenterSettings;

public class LoginActivity extends BaseActivity {

	private Button mButton;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.act_login);
		setUpViews();

	}

	private void setUpViews() {
		mButton = (Button) findViewById(R.id.nmgc_login_button);
		mButton.setOnClickListener(OCL);
	}

	private void showOritationSelect() {
		new AlertDialog.Builder(mContext)
				.setTitle("您的游戏为横屏？")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						GameCenterSettings.isOritationPort = false;
						startLogin();
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								GameCenterSettings.isOritationPort = true;
								startLogin();
							}
						}).create().show();
	}

	private void startLogin() {
		GameCenterSDK.getInstance().doLogin(new ApiCallback() {

			@Override
			public void onSuccess(String content, int code) {
				final Intent it = new Intent(LoginActivity.this,
						OpenSDKDemoNewActivity.class);
				startActivity(it);
			}

			@Override
			public void onFailure(String content, int code) {
				Util.makeToast(content, LoginActivity.this);
			}
		}, LoginActivity.this);
	}

	private OnClickListener OCL = new OnClickListener() {

		@Override
		public void onClick(View v) {
			showOritationSelect();
		}

	};

	public void onBackPressed() {
		System.exit(0);
	};
}

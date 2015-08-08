package com.lenovo.id.pay.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.lenovo.lsf.open.LenovoGameSdk;
import com.lenovo.mpay.ifmgr.SDKApi;

public class GameLauncherActivity extends Activity {
	private static final int MSG_LOGIN_AUTO_ONEKEY = 2;
	private AlertDialog.Builder alertDialogBuilder;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		initViews();
	}

	/**
	 * UI初始化
	 */
	private void initViews() {

		// 加载游戏资源
		setContentView(R.layout.activity_main);

		// 调用快速登录接口
		getTokenByQuickLogin();

		// 支付接口初始化，设置支付界面为横屏，详情参见文档.建议此方法在进入游戏时调用
		SDKApi.init(GameLauncherActivity.this, 0, Config.appid);

		// 退出游戏应用示例
		alertDialogBuilder = new AlertDialog.Builder(this)
				.setMessage("是否确定退出？").setCancelable(true)
				.setPositiveButton("是", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						GameLauncherActivity.this.finish();
						/*
						 * 退出游戏应用请务必调用下面方法
						 */
						LenovoGameSdk.exit();
					}
				})
				.setNegativeButton("否", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
	}

	private void showView() {

		// 进入商城按钮
		final Button buyBtn = (Button) findViewById(R.id.pay_into);
		buyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 发起进入商城
				Intent intent = new Intent(GameLauncherActivity.this,
						PayActivity.class);
				// intent.putExtra("mIsPreDate", mIsPeDate);
				startActivity(intent);
				finish();
			}
		});

	}

	/**
	 * 后台快捷登录
	 */
	private void getTokenByQuickLogin() {
		Bundle loginOption = new Bundle();
		loginOption.putBoolean(LenovoGameSdk.SHOW_WELCOME, true);

		LenovoGameSdk.quickLogin(this, new LenovoGameSdk.OnAuthenListener() {

			@Override
			public void onFinished(boolean ret, String data) {
				final Message msg = new Message();
				msg.what = MSG_LOGIN_AUTO_ONEKEY;
				msg.obj = data;
				if (ret) {
					new Thread() {
						public void run() {
							myHandler.sendMessage(msg);
						};
					}.start();

				} else {
					// TODO 后台快速登录失败(失败原因开启飞行模式、 网络不通等)
					Log.i(Config.TAG, "login fail");
				}
			}
		}, loginOption);

	}

	/**
	 * 功能： 该方法的作用是Handler对传递过来的不同消息，进行不同的处理。 当传递过来的消息是MSG_LOGIN时，则对登录进行相应的处理；
	 */
	private Handler myHandler = new Handler() {
		private String TAG="Lenovo-simple";

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_LOGIN_AUTO_ONEKEY:
				Log.d(TAG, (String) msg.obj);
				Toast.makeText(GameLauncherActivity.this,
						R.string.login_success, Toast.LENGTH_LONG).show();
				// 登录成功
				setContentView(R.layout.login);
				showView();
				break;
			}
		};
	};

	/**
	 * 返回键
	 */
	@Override
	public void onBackPressed() {
		alertDialogBuilder.show();
	}
}

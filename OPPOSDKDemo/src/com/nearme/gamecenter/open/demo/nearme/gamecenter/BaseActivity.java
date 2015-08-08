package com.nearme.gamecenter.open.demo.nearme.gamecenter;

import com.nearme.gamecenter.open.api.GameCenterSDK;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;

public class BaseActivity extends Activity {
	public static final int DIALOG_TYPE_LOADING = 1001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected Dialog onCreateDialog(int id, Bundle bundle) {
		switch (id) {
		case DIALOG_TYPE_LOADING:
			ProgressDialog d = new ProgressDialog(this);
			d.setMessage(bundle.getString("title"));
			return d;
		default:
			return super.onCreateDialog(id);
		}
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog, Bundle bundle) {
		switch (id) {
		case DIALOG_TYPE_LOADING:
			ProgressDialog d = (ProgressDialog) dialog;
			d.setMessage(bundle.getString("title"));
		default:
			return;
		}
	}
	@SuppressWarnings("deprecation")
	protected void showLoading(String title) {
		Bundle b = new Bundle();
		b.putString("title", title);
		showDialog(DIALOG_TYPE_LOADING, b);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		GameCenterSDK.setmCurrentContext(this);
	}
}

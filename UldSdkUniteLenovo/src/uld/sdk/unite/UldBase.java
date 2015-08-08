package uld.sdk.unite;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.widget.Toast;

public class UldBase {

	private ProgressDialog progressDialog;

	protected void showLoading() {
		progressDialog = new ProgressDialog(UldPlatform.sContext);
		progressDialog.setMessage("正在加载...");
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	protected void showLoading(String tips) {
		progressDialog = new ProgressDialog(UldPlatform.sContext);
		progressDialog.setMessage(tips);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	protected void hideLoading() {
		if (progressDialog != null) {
			progressDialog.cancel();
			progressDialog = null;
		}
	}

	public void showToast(CharSequence text, int duration) {
		Toast.makeText(UldPlatform.sContext, text, duration).show();
	}

	public void showToastThread(CharSequence text, int duration) {
		Looper.prepare();
		Toast.makeText(UldPlatform.sContext, text, duration).show();
		Looper.loop();
	}

	public String getMetaData(Context context, String metaName) {
		String str = "";

		ApplicationInfo info;
		try {
			info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			str = info.metaData.getString(metaName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}
}

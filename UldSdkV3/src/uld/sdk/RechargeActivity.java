package uld.sdk;

import java.math.BigDecimal;
import java.util.Date;


import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import uld.sdk.R;
import wh.order.model.OrderEnum;
import wh.order.model.OrderEnum.PayType;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RechargeActivity extends BaseActivity {

	private Button btnBack = null;
	// private Button btnAliPay = null;
	// private Button btnYD = null;
	// private Button btnLT = null;
	// private Button btnDX = null;
	private Intent rechargeIntent = null;

	// private Button btnYbekt = null;
	// private Button btnZyykt = null;
	// private Button btnTxykt = null;
	// private Button btnPaypal = null;

	private RelativeLayout rlAlif = null;
	private ImageView imageAlif = null;
	
	private RelativeLayout rlAliQianbao = null;
	private ImageView imageAliQianbao = null;
	private View vAliQianBao = null; 

	private RelativeLayout rlAli = null;
	private ImageView imgaeAli = null;
	private RelativeLayout rlYinlian = null;
	private ImageView imgaeYinlian = null;
	private RelativeLayout rlYD = null;
	private ImageView imgaeYD = null;
	private RelativeLayout rlLT = null;
	private ImageView imgaeLT = null;
	private RelativeLayout rlDX = null;
	private ImageView imgaeDX = null;

	private RelativeLayout rlJwykt = null;
	private ImageView imgaeJwykt = null;
	private RelativeLayout rlSdk = null;
	private ImageView imgaeSdk = null;
	private RelativeLayout rlWyykt = null;
	private ImageView imgaeWyykt = null;
	private RelativeLayout rlWmykt = null;
	private ImageView imgaeWmykt = null;
	private RelativeLayout rlZtk = null;
	private ImageView imgaeZtk = null;
	private RelativeLayout rlJyykt = null;
	private ImageView imgaeJyykt = null;
	private RelativeLayout rlTxykt = null;
	private ImageView imgaeTxykt = null;
	private RelativeLayout rlYbekt = null;
	private ImageView imgaeYbekt = null;
	private RelativeLayout rlZyykt = null;
	private ImageView imgaeZyykt = null;
	
	private boolean needQianbao=false;

	private static RechargeCallBackListener rechargeCallBackListener = null;

	public static void show(Context context, int gameId, int serverId, RechargeCallBackListener rechargeCallBackListener) {
		BaseActivity.setGameId(gameId);
		BaseActivity.setServerId(serverId);

		RechargeActivity.rechargeCallBackListener = rechargeCallBackListener;

		Intent intent = new Intent(context, RechargeActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getDataFromIntent();

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int widthDivideDensity = (int) (outMetrics.widthPixels / outMetrics.scaledDensity);

		if (getWindowManager().getDefaultDisplay().getWidth() <= 240) {
			setContentView(R.layout.activity_recharge_240);
		} else if (widthDivideDensity <= valueOfWidthDivideDensity) {
			setContentView(R.layout.activity_recharge_320);
		} else {
			setContentView(R.layout.activity_recharge);
		}

		findViews();

		initialViews();
		
		showQianbaoMethod();

		setListeners();
	}

	private void showQianbaoMethod() {
		if(!needQianbao){
			rlAliQianbao.setVisibility(View.GONE);
			vAliQianBao.setVisibility(View.GONE);
		}
	}

	private void initialViews() {

	}

	private void getDataFromIntent() {
		Intent userIntent = getIntent();
		if (null == userIntent) {
			return;
		}
		Bundle userBundle = userIntent.getExtras();
		if (null == userBundle) {
			return;
		}

		int gameId = Utility.getInt(userBundle.getString("GameId"));
		if (gameId > 0) {
			BaseActivity.setGameId(gameId);
		}

		String playerId = userBundle.getString("PlayerId");
		BaseActivity.setPlayerId(playerId);

		int userId = Utility.getInt(userBundle.getString("UserId"));

		if (userId > 0) {
			BaseActivity.setUserId(userId);
		} else if (BaseActivity.getUserId() <= 0) {
			finish();
		}
	}

	private void findViews() {
		btnBack = (Button) findViewById(R.id.btnBack);

		rlAlif = (RelativeLayout) findViewById(R.id.rlalif);
		imageAlif = (ImageView) findViewById(R.id.imageAlif);
		
		rlAliQianbao = (RelativeLayout) findViewById(R.id.rlaliqianbao);
		imageAliQianbao = (ImageView) findViewById(R.id.imageAliQianbao);
		vAliQianBao = (View)findViewById(R.id.vAliQianBao);
		
		rlAli = (RelativeLayout) findViewById(R.id.rlali);
		imgaeAli = (ImageView) findViewById(R.id.imageAli);

		rlYinlian = (RelativeLayout) findViewById(R.id.rlYinlian);
		imgaeYinlian = (ImageView) findViewById(R.id.imageYinlian);

		rlYD = (RelativeLayout) findViewById(R.id.rlYD);
		imgaeYD = (ImageView) findViewById(R.id.imageYD);

		rlLT = (RelativeLayout) findViewById(R.id.rlLT);
		imgaeLT = (ImageView) findViewById(R.id.imageLT);

		rlDX = (RelativeLayout) findViewById(R.id.rlDX);
		imgaeDX = (ImageView) findViewById(R.id.imageDX);

		rlJwykt = (RelativeLayout) findViewById(R.id.rlJwykt);
		imgaeJwykt = (ImageView) findViewById(R.id.imageJwykt);

		rlSdk = (RelativeLayout) findViewById(R.id.rlSdk);
		imgaeSdk = (ImageView) findViewById(R.id.imageSdk);

		rlWyykt = (RelativeLayout) findViewById(R.id.rlWyykt);
		imgaeWyykt = (ImageView) findViewById(R.id.imageWyykt);

		rlWmykt = (RelativeLayout) findViewById(R.id.rlWmykt);
		imgaeWmykt = (ImageView) findViewById(R.id.imageWmykt);

		rlZtk = (RelativeLayout) findViewById(R.id.rlZtk);
		imgaeZtk = (ImageView) findViewById(R.id.imageZtk);

		rlJyykt = (RelativeLayout) findViewById(R.id.rlJyykt);
		imgaeJyykt = (ImageView) findViewById(R.id.imageJyykt);

		rlYbekt = (RelativeLayout) findViewById(R.id.rlYbekt);
		imgaeYbekt = (ImageView) findViewById(R.id.imageYbekt);

		rlZyykt = (RelativeLayout) findViewById(R.id.rlZyykt);
		imgaeZyykt = (ImageView) findViewById(R.id.imageZyykt);

		rlTxykt = (RelativeLayout) findViewById(R.id.rlTxykt);
		imgaeTxykt = (ImageView) findViewById(R.id.imageTxykt);
	}

	private void setListeners() {
		btnBack.setOnClickListener(new BackClickListener());

		rlAlif.setOnClickListener(new AliPayfClickListener());
		rlAlif.setOnTouchListener(new RelativeLayoutOnTouchListener());
		
		rlAliQianbao.setOnClickListener(new AliPayQianbaoClickListener());
		rlAliQianbao.setOnTouchListener(new RelativeLayoutOnTouchListener());

		rlAli.setOnClickListener(new AliPayClickListener());
		rlAli.setOnTouchListener(new RelativeLayoutOnTouchListener());

		rlYinlian.setOnClickListener(new YinlianClickListener());
		rlYinlian.setOnTouchListener(new RelativeLayoutOnTouchListener());
		//  加入银联后取消
		// rlYinlian.setVisibility(View.GONE);

		rlYD.setOnClickListener(new YDClickListener());
		rlYD.setOnTouchListener(new RelativeLayoutOnTouchListener());

		rlLT.setOnClickListener(new LTClickListener());
		rlLT.setOnTouchListener(new RelativeLayoutOnTouchListener());

		rlDX.setOnClickListener(new DXClickListener());
		rlDX.setOnTouchListener(new RelativeLayoutOnTouchListener());

		rlJwykt.setOnClickListener(new JwyktClickListener());
		rlJwykt.setOnTouchListener(new RelativeLayoutOnTouchListener());
		rlSdk.setOnClickListener(new SdkClickListener());
		rlSdk.setOnTouchListener(new RelativeLayoutOnTouchListener());
		rlWyykt.setOnClickListener(new WyyktClickListener());
		rlWyykt.setOnTouchListener(new RelativeLayoutOnTouchListener());
		rlWmykt.setOnClickListener(new WmyktClickListener());
		rlWmykt.setOnTouchListener(new RelativeLayoutOnTouchListener());
		rlZtk.setOnClickListener(new ZtkClickListener());
		rlZtk.setOnTouchListener(new RelativeLayoutOnTouchListener());
		rlJyykt.setOnClickListener(new JyyktClickListener());
		rlJyykt.setOnTouchListener(new RelativeLayoutOnTouchListener());
		rlYbekt.setOnClickListener(new YbektClickListener());
		rlYbekt.setOnTouchListener(new RelativeLayoutOnTouchListener());
		rlZyykt.setOnClickListener(new ZyyktClickListener());
		rlZyykt.setOnTouchListener(new RelativeLayoutOnTouchListener());
		rlTxykt.setOnClickListener(new TxyktClickListener());
		rlTxykt.setOnTouchListener(new RelativeLayoutOnTouchListener());
		// rlPaypal.setOnClickListener(new PaypalClickListener());

	}

	class RelativeLayoutOnTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {

			ImageView selectedItemArrow = null;
			if (v.getId() == R.id.rlaliqianbao) {
				selectedItemArrow = imageAliQianbao;
			} else if(v.getId()==R.id.rlalif){
				selectedItemArrow = imageAlif;
			}else if (v.getId() == R.id.rlali) {
				selectedItemArrow = imgaeAli;
			} else if (v.getId() == R.id.rlYD) {
				selectedItemArrow = imgaeYD;
			} else if (v.getId() == R.id.rlYinlian) {
				selectedItemArrow = imgaeYinlian;
			} else if (v.getId() == R.id.rlLT) {
				selectedItemArrow = imgaeLT;
			} else if (v.getId() == R.id.rlDX) {
				selectedItemArrow = imgaeDX;
			} else if (v.getId() == R.id.rlJwykt) {
				selectedItemArrow = imgaeJwykt;
			} else if (v.getId() == R.id.rlSdk) {
				selectedItemArrow = imgaeSdk;
			} else if (v.getId() == R.id.rlWyykt) {
				selectedItemArrow = imgaeWyykt;
			} else if (v.getId() == R.id.rlWmykt) {
				selectedItemArrow = imgaeWmykt;
			} else if (v.getId() == R.id.rlZtk) {
				selectedItemArrow = imgaeZtk;
			} else if (v.getId() == R.id.rlJyykt) {
				selectedItemArrow = imgaeJyykt;
			} else if (v.getId() == R.id.rlYbekt) {
				selectedItemArrow = imgaeYbekt;
			} else if (v.getId() == R.id.rlZyykt) {
				selectedItemArrow = imgaeZyykt;
			} else if (v.getId() == R.id.rlTxykt) {
				selectedItemArrow = imgaeTxykt;
			}

			int EvtNum = event.getAction();
			switch (EvtNum) {
			case MotionEvent.ACTION_DOWN: {
				selectedItemArrow.setImageDrawable(getResources().getDrawable(R.drawable.uld_icon_arrow_white));
				return false;
			}
			case MotionEvent.ACTION_MOVE | MotionEvent.ACTION_OUTSIDE: {
				selectedItemArrow.setImageDrawable(getResources().getDrawable(R.drawable.uld_icon_arrow));
				return false;
			}
			case MotionEvent.ACTION_CANCEL: {
				selectedItemArrow.setImageDrawable(getResources().getDrawable(R.drawable.uld_icon_arrow));
				return false;
			}
			case MotionEvent.ACTION_UP: {
				selectedItemArrow.setImageDrawable(getResources().getDrawable(R.drawable.uld_icon_arrow));
				return false;
			}
			default:
				break;
			}
			return false;
		}
	}

	class BackClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	// 快捷支付
		class AliPayfClickListener implements OnClickListener {

			@Override
			public void onClick(View v) {
				startRechargeConfirmActivity(PayType.快捷支付);
			}

		}
	
	
	// 钱包支付
	class AliPayQianbaoClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.钱包支付);
		}

	}

	class AliPayClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.支付宝);
		}
	}

	class YinlianClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// 银联支付--网上银行
			startRechargeConfirmActivity(PayType.网上银行);
		}
	}

	class YDClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.神州行);
		}
	}

	class LTClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.联通);
		}
	}

	class DXClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.电信);
		}
	}

	class JwyktClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.骏网一卡通支付);
		}
	}

	class SdkClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.盛大卡);
		}
	}

	class WyyktClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.网易一卡通);
		}
	}

	class WmyktClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.完美一卡通支付);
		}
	}

	class ZtkClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.征途卡支付);
		}
	}

	class JyyktClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.久游一卡通支付);
		}
	}

	class YbektClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.易宝E卡通支付);
		}
	}

	class ZyyktClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.纵游一卡通);
		}
	}

	class TxyktClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			startRechargeConfirmActivity(PayType.天下一卡通);
		}
	}

	class PaypalClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			showToast("敬请期待", Toast.LENGTH_LONG);
			// startRechargeConfirmActivity(PayType.PayPal);
		}
	}

	/**
	 * 启动充值确认窗口
	 * 
	 * @param payType
	 *            支付类型：0、支付类型；1、网上银行；2、支付宝；3、手机卡；4、声讯；5、点卡；6、PayPal；7、神州行；8、联通；9
	 *            、电信；10、钱包；65、骏网一卡通支付；66、盛大卡；67、网易一卡通；68、完美一卡通支付；69、征途卡支付；70、
	 *            久游一卡通支付；71、易宝E卡通支付；72、纵游一卡通；73、天下一卡通；74、G币返还；
	 */
	private void startRechargeConfirmActivity(wh.order.model.OrderEnum.PayType payType) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putByte("PayType", payType.getValue());
		intent.putExtras(bundle);
		intent.setClass(RechargeActivity.this, RechargeConfirmActivity.class);
		startActivityForResult(intent, BaseActivity.REQUESTCODE_RECHARGE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == BaseActivity.REQUESTCODE_RECHARGE) {
			rechargeIntent = data;
			finish();
		}
	}

	@Override
	public void finish() {
		RechargeResult rechargeResult = new RechargeResult();
		rechargeResult.setOrderId(String.valueOf(0));
		rechargeResult.setOrderType(3);
		rechargeResult.setOrderMsg("尚未充值");

		if (rechargeIntent != null) {
			Bundle bundle = rechargeIntent.getExtras();
			if (bundle != null) {
				rechargeResult.setOrderId(bundle.getString("OrderId"));
				rechargeResult.setPayAccount(Utility.getInt(bundle.getString("PayAccount")));
				rechargeResult.setOrderType(1);
				rechargeResult.setOrderMsg("充值已提交");

				// 支付宝值生成订单 但是没有得到订单处理
				if (rechargeResult.getOrderId().equals("0")) {
					rechargeResult.setOrderType(3);
					rechargeResult.setOrderMsg("尚未充值");
					rechargeResult.setPayAccount(0);
				}

			}
		}
		rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
		super.finish();
	}

}

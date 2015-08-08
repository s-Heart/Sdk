package uld.sdk;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import uld.product.alipay.BaseHelper;
import uld.product.alipay.MobileSecurePayHelper;
import uld.product.alipay.MobileSecurePayer;
import uld.product.alipay.PartnerConfig;
import uld.product.alipay.ResultChecker;
import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.RSA;
import uld.sdk.tools.Utility;
import wh.member.model.User;
import wh.order.model.OrderChannel;
import wh.order.model.OrderEnum;
import wh.order.model.OrderEnum.PayType;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.alipay.android.app.IAlixPay;
import com.lthj.unipay.plugin.l;
import com.unionpay.upomp.lthj.util.PluginHelper;

public class RechargeConfirmActivity extends BaseActivity {

	private Button btnBack = null;
	private TextView tvRechargeTitle = null;
	private TextView tvServiceHotline = null;
	private EditText txtCardNo = null;
	private EditText txtCardPwd = null;
	private TextView tvMoney = null;
	private Spinner spMoney = null;
	private TextView tvRechargeGameTip = null;
	private Button btnRecharge = null;
	private LinearLayout llCardNoLayout = null;
	private LinearLayout llCardPwdLayout = null;

	// guojunbing add new view
	// activity_rechargeconfirm 中添加的一个“元”和“自定义金额”的textview
	// 是提示为了界面友好，不新建对象，只是在布局文件中加了一个textview

	private EditText etcustommoney = null; // 自定义输入金额的文本框
	private LinearLayout llCustomPay = null; // 控制自定义layout

	private PayType payType;
	private int rechargeValue = 0;
	private Dictionary<PayType, String> rechargeDictionary = new Hashtable<PayType, String>();
	private List<Integer> moneyList = new ArrayList<Integer>();
	private ArrayAdapter<Integer> adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int widthDivideDensity = (int) (outMetrics.widthPixels / outMetrics.scaledDensity);

		if (widthDivideDensity <= valueOfWidthDivideDensity) {
			setContentView(R.layout.activity_rechargeconfirm_320);
		} else {
			setContentView(R.layout.activity_rechargeconfirm);
		}

		initRechargeDictionary();

		findViews();

		getDataFromIntent();

		initViews();

		setMoneyList(payType);

		initSpinner(payType);

		setListeners();
	}

	private void initViews() {
		switch (payType) {
		case 快捷支付:
		case 钱包支付:
		case 支付宝:
		case 网上银行:
			llCardNoLayout.setVisibility(View.GONE);
			llCardPwdLayout.setVisibility(View.GONE);
			tvMoney.setText(R.string.uld_rechargemoneypay);
			break;
		default:
			llCustomPay.setVisibility(View.GONE);
			break;
		}
	}

	private void initRechargeDictionary() {
		if (rechargeDictionary.size() == 0) {
			rechargeDictionary.put(PayType.PayPal, getString(R.string.uld_paypal));
			// 网上银行-银联
			rechargeDictionary.put(PayType.网上银行, getString(R.string.uld_ylzf));
			rechargeDictionary.put(PayType.支付宝, getString(R.string.uld_alipay));
			rechargeDictionary.put(PayType.钱包支付, getString(R.string.uld_alipay_qianbao));
			rechargeDictionary.put(PayType.快捷支付, getString(R.string.uld_alipay_f));
			rechargeDictionary.put(PayType.电信, getString(R.string.uld_zgdx));
			rechargeDictionary.put(PayType.联通, getString(R.string.uld_zglt));
			rechargeDictionary.put(PayType.神州行, getString(R.string.uld_zgydk));
			rechargeDictionary.put(PayType.骏网一卡通支付, getString(R.string.uld_jwykt));
			rechargeDictionary.put(PayType.盛大卡, getString(R.string.uld_sdk));
			rechargeDictionary.put(PayType.网易一卡通, getString(R.string.uld_wyykt));
			rechargeDictionary.put(PayType.完美一卡通支付, getString(R.string.uld_wmykt));
			rechargeDictionary.put(PayType.征途卡支付, getString(R.string.uld_ztk));
			rechargeDictionary.put(PayType.久游一卡通支付, getString(R.string.uld_jyykt));
			rechargeDictionary.put(PayType.易宝E卡通支付, getString(R.string.uld_ybekt));
			rechargeDictionary.put(PayType.纵游一卡通, getString(R.string.uld_zyykt));
			rechargeDictionary.put(PayType.天下一卡通, getString(R.string.uld_txykt));
		}
	}

	private void findViews() {
		btnBack = (Button) findViewById(R.id.btnBack);
		tvRechargeTitle = (TextView) findViewById(R.id.tvRechargeTitle);
		txtCardNo = (EditText) findViewById(R.id.txtCardNo);
		txtCardPwd = (EditText) findViewById(R.id.txtCardPwd);
		spMoney = (Spinner) findViewById(R.id.spMoney);
		tvRechargeGameTip = (TextView) findViewById(R.id.tvRechargeGameTip);
		btnRecharge = (Button) findViewById(R.id.btnRecharge);
		tvMoney = (TextView) findViewById(R.id.tvMoney);
		llCardNoLayout = (LinearLayout) findViewById(R.id.llCardNo);
		llCardPwdLayout = (LinearLayout) findViewById(R.id.llCardPwd);

		llCustomPay = (LinearLayout) findViewById(R.id.llCustomPay);

		etcustommoney = (EditText) findViewById(R.id.etcustommoney);

		tvServiceHotline = (TextView) findViewById(R.id.tvServiceHotline);
		// String servicehotline ="";
		// String servicehotline ="";
		// 更改客服热线与
		StringBuffer servicehotline = new StringBuffer();
		String[] homeUrls = getContent().split("\\|_\\|");
		for (int i = 0; i < homeUrls.length; i++) {
			if (homeUrls[i].contains("hotline")) {
				servicehotline.append(homeUrls[i]);
				Log.d("test", homeUrls[i]);
			}
		}
		String hotline = servicehotline.toString().replace("hotline=", "客服热线:");
		tvServiceHotline.setText(hotline);

	}

	private void getDataFromIntent() {
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				payType = PayType.parse(bundle.getByte("PayType"));
				// 2014-1-2 17:06:03
				// 如果卡型为盛大卡，这里将控件的输入类型变为text
				if (payType == PayType.盛大卡) {
					txtCardNo.setInputType(InputType.TYPE_CLASS_TEXT);
				}

				tvRechargeTitle.setText("\"" + rechargeDictionary.get(payType) + "\"");
			}
		}
	}

	private void setMoneyList(PayType payType) {
		String cardNoTipExt = "";
		String cardPwdTipExt = "";

		switch (payType) {
		case 网上银行:
			moneyList.add(2000);
			moneyList.add(1000);
			moneyList.add(500);
			moneyList.add(300);
			moneyList.add(100);
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(20);
			moneyList.add(10);
			break;
		case 支付宝:
		case 钱包支付:
		case 快捷支付:
			moneyList.add(2000);
			moneyList.add(1000);
			moneyList.add(500);
			moneyList.add(300);
			moneyList.add(100);
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(20);
			moneyList.add(10);
			// moneyList.add(1);
			break;
		case PayPal:
			moneyList.add(2000);
			moneyList.add(1000);
			moneyList.add(500);
			moneyList.add(300);
			moneyList.add(100);
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(20);
			moneyList.add(10);
			// moneyList.add(1);

			break;
		case 电信:
			moneyList.add(100);
			moneyList.add(50);
			cardNoTipExt = "(19位)";
			cardPwdTipExt = "(18位)";

			break;
		case 联通:
			moneyList.add(500);
			moneyList.add(300);
			moneyList.add(100);
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(20);
			cardNoTipExt = "(15位)";
			cardPwdTipExt = "(19位)";

			break;
		case 神州行:
			moneyList.add(500);
			moneyList.add(300);
			moneyList.add(100);
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(20);
			moneyList.add(10);
			cardNoTipExt = "(17位)";
			cardPwdTipExt = "(18位)";

			break;
		case 骏网一卡通支付:
			moneyList.add(100);
			moneyList.add(30);
			moneyList.add(15);
			moneyList.add(10);
			cardNoTipExt = "(16位)";
			cardPwdTipExt = "(16位)";

			break;
		case 盛大卡:
			moneyList.add(1000);
			moneyList.add(350);
			moneyList.add(100);
			moneyList.add(45);
			moneyList.add(30);
			moneyList.add(10);
			cardNoTipExt = "(15位)";
			cardPwdTipExt = "(8或9位)";

			break;
		case 网易一卡通:
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(20);
			moneyList.add(15);
			moneyList.add(10);
			cardNoTipExt = "(13位)";
			cardPwdTipExt = "(9位)";

			break;
		case 完美一卡通支付:
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(20);
			moneyList.add(15);
			cardNoTipExt = "(10位)";
			cardPwdTipExt = "(15位)";

			break;
		case 征途卡支付:
			moneyList.add(468);
			moneyList.add(300);
			moneyList.add(208);
			moneyList.add(180);
			moneyList.add(150);
			moneyList.add(120);
			moneyList.add(68);
			moneyList.add(60);
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(20);
			moneyList.add(18);
			moneyList.add(15);
			moneyList.add(10);
			cardNoTipExt = "(16位)";
			cardPwdTipExt = "(8位)";

			break;
		case 久游一卡通支付:
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(10);
			cardNoTipExt = "(13位)";
			cardPwdTipExt = "(10位)";

			break;
		case 易宝E卡通支付:
			moneyList.add(100);
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(25);
			moneyList.add(20);
			moneyList.add(15);
			moneyList.add(10);
			cardNoTipExt = "(12或17位)";
			cardPwdTipExt = "(10或18位)";
			break;
		case 纵游一卡通:
			moneyList.add(100);
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(15);
			moneyList.add(10);
			cardNoTipExt = "(15位)";
			cardPwdTipExt = "(15位)";
			break;
		case 天下一卡通:
			moneyList.add(100);
			moneyList.add(50);
			moneyList.add(30);
			moneyList.add(15);
			moneyList.add(10);
			cardNoTipExt = "(15位)";
			cardPwdTipExt = "(8位)";
			break;
		default:
			break;
		}

		txtCardNo.setHint(txtCardNo.getHint() + cardNoTipExt);
		txtCardPwd.setHint(txtCardPwd.getHint() + cardPwdTipExt);

	}

	private void initSpinner(PayType payType) {
		adapter = new ArrayAdapter<Integer>(this, R.layout.activity_rechargeview, moneyList);
		adapter.setDropDownViewResource(R.layout.activity_rechargeitem);
		spMoney.setAdapter(adapter);

		switch (payType) {
		case 电信:
			spMoney.setSelection(0);
			break;
		case 钱包支付:
		case 快捷支付:
		case 支付宝:
			spMoney.setSelection(4);
			break;
		case 联通:
		case 神州行:
		case 盛大卡:
			spMoney.setSelection(2);
			break;
		case 征途卡支付:
			spMoney.setSelection(8);
			break;
		case 网上银行:
			spMoney.setSelection(8);
			break;
		default:
			break;
		}

		rechargeValue = Integer.parseInt(spMoney.getItemAtPosition(spMoney.getSelectedItemPosition()).toString());
		setRechargeGameTip(rechargeValue);
	}

	private void setRechargeGameTip(Integer rechargeValue) {

		double payTypeRate = 1;
		// switch (payType) {
		// case 网上银行:
		// case G币返还:
		// case 钱包:
		// case 支付宝:
		// case PayPal:
		// payTypeRate = 1;
		// break;
		// case 神州行:
		// payTypeRate = 0.9;
		// break;
		// default:
		// payTypeRate = 0.8;
		// break;
		// }

		String rechargeGameTip = (int) (payTypeRate * rechargeValue * getRechargeRate()) + getRechargeUnit();
		tvRechargeGameTip.setText(rechargeGameTip);
	}

	private void setListeners() {
		btnBack.setOnClickListener(new BackClickListener());

		txtCardNo.setOnFocusChangeListener(new CardNoFocusChangeListener());
		txtCardPwd.setOnFocusChangeListener(new CardPwdFocusChangeListener());

		spMoney.setOnItemSelectedListener(new SpMoneyItemSelectedListener());
		spMoney.setOnTouchListener(new SpMoneyTouchListener());
		spMoney.setOnFocusChangeListener(new SpMoneyFocusChangeListener());

		btnRecharge.setOnClickListener(new RechargeClickListener());
		etcustommoney.addTextChangedListener(new etTextWatcher());
		etcustommoney.setOnEditorActionListener(new etOnEditorActionListener());
	}

	protected void closedone() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etcustommoney.getWindowToken(), 0);

	}

	class etTextWatcher implements TextWatcher {

		@Override
		public void afterTextChanged(Editable s) {
			int Value = Utility.getInt(s.toString());
			if (Value > 2000) {
				showToast("充值金额必须小于2000元", Toast.LENGTH_LONG);
				etcustommoney.setText("2000");
				rechargeValue = 2000;
			} else {
				rechargeValue = Value;
			}
			setRechargeGameTip(rechargeValue);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

	}

	class etOnEditorActionListener implements OnEditorActionListener {

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if (actionId == EditorInfo.IME_ACTION_DONE) {
				etcustommoney.setText(String.valueOf(rechargeValue));
				closedone();
				return true;
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

	class CardNoFocusChangeListener implements OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (!hasFocus) {
				int cardNoLength = 0;
				int cardNoLength2 = 0;
				switch (payType) {
				case 电信:
					cardNoLength = 19;
					break;
				case 联通:
				case 盛大卡:
				case 天下一卡通:
				case 纵游一卡通:
					cardNoLength = 15;
					break;
				case 神州行:
					cardNoLength = 17;
					break;
				case 骏网一卡通支付:
				case 征途卡支付:
					cardNoLength = 16;
					break;
				case 久游一卡通支付:
				case 网易一卡通:
					cardNoLength = 13;
					break;
				case 易宝E卡通支付:
					cardNoLength = 12;
					cardNoLength2 = 17;
					break;
				case 完美一卡通支付:
					cardNoLength = 10;
					break;
				default:
					break;
				}

				boolean isValidCardNo = false;
				if ((txtCardNo.getText().length() == cardNoLength) || (cardNoLength2 > 0 && txtCardNo.getText().length() == cardNoLength2)) {
					isValidCardNo = true;
				}

				if (isValidCardNo) {
					txtCardNo.setTextColor(getResources().getColor(R.color.uld_okmsgcolor));
				} else {
					CheckEditText(txtCardNo, "卡号输入不正确");
				}
			} else {
				txtCardNo.setTextColor(getResources().getColor(R.color.uld_tvcolor));
			}
		}
	}

	class CardPwdFocusChangeListener implements OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (!hasFocus) {
				int cardPwdLength = 0;
				int cardPwdLength2 = 0;
				switch (payType) {
				case 电信:
				case 神州行:
					cardPwdLength = 18;
					break;
				case 联通:
					cardPwdLength = 19;
					break;
				case 骏网一卡通支付:
					cardPwdLength = 16;
					break;
				case 盛大卡:
					cardPwdLength = 8;
					cardPwdLength2 = 9;
					break;
				case 征途卡支付:
				case 天下一卡通:
					cardPwdLength = 8;
					break;
				case 久游一卡通支付:
					cardPwdLength = 10;
					break;
				case 易宝E卡通支付:
					cardPwdLength = 10;
					cardPwdLength2 = 18;
					break;
				case 网易一卡通:
					cardPwdLength = 9;
					break;
				case 完美一卡通支付:
				case 纵游一卡通:
					cardPwdLength = 15;
					break;
				default:
					break;
				}

				boolean isValidPwd = false;
				if ((txtCardPwd.getText().length() == cardPwdLength)
						|| (cardPwdLength2 > 0 && txtCardPwd.getText().length() == cardPwdLength2)) {
					isValidPwd = true;
				}

				if (isValidPwd) {
					txtCardPwd.setTextColor(getResources().getColor(R.color.uld_okmsgcolor));
				} else {
					CheckEditText(txtCardPwd, "密码输入不正确");
				}
			} else {
				txtCardPwd.setTextColor(getResources().getColor(R.color.uld_tvcolor));
			}

		}
	}

	class SpMoneyItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			rechargeValue = Integer.parseInt(arg0.getItemAtPosition(arg2).toString());
			setRechargeGameTip(rechargeValue);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			arg0.setVisibility(View.VISIBLE);
		}
	}

	class SpMoneyTouchListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			v.setVisibility(View.VISIBLE);
			return false;
		}
	}

	class SpMoneyFocusChangeListener implements OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			v.setVisibility(View.VISIBLE);
		}
	}

	class RechargeClickListener implements OnClickListener {
		boolean isQianbao = false;

		@Override
		public void onClick(View v) {
			int cardNoLength = 0;
			int cardNoLength2 = 0;
			int cardPwdLength = 0;
			int cardPwdLength2 = 0;
			switch (payType) {
			case 电信:
				cardNoLength = 19;
				cardPwdLength = 18;
				break;
			case 联通:
				cardNoLength = 15;
				cardPwdLength = 19;
				break;
			case 神州行:
				cardNoLength = 17;
				cardPwdLength = 18;
				break;
			case 网上银行:
				yinLianConfirm();
				return;
			case 支付宝:
				alipayConfirm();
				return;
			case 钱包支付:
				isQianbao = true;
				alipayFConfirm(isQianbao);
				return;
			case 快捷支付:
				isQianbao = false;
				alipayFConfirm(isQianbao);
				return;
			case 骏网一卡通支付:
				cardNoLength = 16;
				cardPwdLength = 16;
				break;
			case 盛大卡:
				cardNoLength = 15;
				cardPwdLength = 8;
				cardPwdLength2 = 9;
				break;
			case 网易一卡通:
				cardNoLength = 13;
				cardPwdLength = 9;
				break;
			case 完美一卡通支付:
				cardNoLength = 10;
				cardPwdLength = 15;
				break;
			case 征途卡支付:
				cardNoLength = 16;
				cardPwdLength = 8;
				break;
			case 久游一卡通支付:
				cardNoLength = 13;
				cardPwdLength = 10;
				break;
			case 易宝E卡通支付:
				cardNoLength = 12;
				cardNoLength2 = 17;
				cardPwdLength = 10;
				cardPwdLength2 = 18;
				break;
			case 纵游一卡通:
				cardNoLength = 15;
				cardPwdLength = 15;
				break;
			case 天下一卡通:
				cardNoLength = 15;
				cardPwdLength = 8;
				break;
			default:
				break;
			}

			boolean isValidCard = false;
			if ((txtCardNo.getText().length() == cardNoLength) || (cardNoLength2 > 0 && txtCardNo.getText().length() == cardNoLength2)) {
				isValidCard = true;
			}

			if (isValidCard) {
				txtCardNo.setTextColor(getResources().getColor(R.color.uld_okmsgcolor));
			} else {
				CheckEditText(txtCardNo, "卡号输入不正确");
				return;
			}

			boolean isValidPwd = false;
			if ((txtCardPwd.getText().length() == cardPwdLength) || (cardPwdLength2 > 0 && txtCardPwd.getText().length() == cardPwdLength2)) {
				isValidPwd = true;
			}

			if (isValidPwd) {
				txtCardPwd.setTextColor(getResources().getColor(R.color.uld_okmsgcolor));
			} else {
				CheckEditText(txtCardPwd, "密码输入不正确");
				return;
			}

			dialog = showDialog();
			new Thread() {
				public void run() {
					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();
					MessageBody messageBody = new MessageBody("uld.sdk.bll.User", "payCard",
							new Class<?>[] { int.class, String.class, String.class, PayType.class, int.class, int.class, String.class,
									int.class, String.class, int.class, int.class }, new Object[] { BaseActivity.getUserId(),
									txtCardNo.getText().toString(), txtCardPwd.getText().toString(), payType, BaseActivity.getGameId(),
									BaseActivity.getServerId(), BaseActivity.getPlayerId(), rechargeValue, "", getChannelId(), 7 });
					MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
					dialog.dismiss();
					if (!messageReturn.findErr()) {
						if (messageReturn.getRetObject() != null) {
							BaseActivity.setCurrentOrderId(Utility.getInt(messageReturn.getRetObject()));
						} else {
							showToastThread("充值提交失败，请重新提交", Toast.LENGTH_LONG);
						}
					} else {
						showToastThread(messageReturn.getErrMsg(), Toast.LENGTH_LONG);
					}

					if (BaseActivity.getCurrentOrderId() > 0) {
						Intent intent = new Intent();
						Bundle bundle = new Bundle();
						bundle.putInt("OrderId", BaseActivity.getCurrentOrderId());
						bundle.putInt("RechargeValue", rechargeValue);
						bundle.putByte("PayType", payType.getValue());
						intent.putExtras(bundle);
						intent.setClass(RechargeConfirmActivity.this, RechargeWaitingActivity.class);
						startActivityForResult(intent, BaseActivity.REQUESTCODE_RECHARGE);
					}
				}
			}.start();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == BaseActivity.REQUESTCODE_RECHARGE) {
			setResult(resultCode, data);
			finish();
		} else if (data != null) {
			Bundle bundle = data.getExtras();
			byte[] xml = bundle.getByteArray("xml");
			// 自行解析并输出
			String Sxml;
			try {
				Sxml = new String(xml, "utf-8");
				// Log.d("yinlian", "这是支付成功后，回调返回的报文，需自行解析" + Sxml);
				int startIndex = Sxml.indexOf("<merchantOrderId>");
				int endIndex = Sxml.indexOf("</merchantOrderId>");
				String strOrderId = "";
				if (startIndex > 0 && endIndex > 0) {
					strOrderId = Sxml.substring(startIndex, endIndex);
					strOrderId = strOrderId.replaceAll("<merchantOrderId>", "");
					strOrderId = strOrderId.replaceAll("X", "");
				}

				if (Sxml.indexOf("<respCode>0000</respCode>") > 0 && strOrderId.equals(String.valueOf(BaseActivity.getCurrentOrderId()))) {
					// 充值成功
					Intent intent = new Intent();
					Bundle bundle2 = new Bundle();
					bundle2.putInt("OrderId", BaseActivity.getCurrentOrderId());
					bundle2.putInt("RechargeValue", rechargeValue);
					bundle2.putByte("PayType", payType.getValue());
					intent.putExtras(bundle2);
					intent.setClass(RechargeConfirmActivity.this, RechargeWaitingActivity.class);
					startActivityForResult(intent, BaseActivity.REQUESTCODE_RECHARGE);
				}
			} catch (Exception e) {
			}
		}
	}

	// ---------------------------------------快捷支付Start-----------------------------------------//
	private static final int 处理快捷支付 = 1;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				String ret = (String) msg.obj;

				Log.e("test", ret); // strRet范例：resultStatus={9000};memo={};result={partner="2088201564809153"&seller="2088201564809153"&out_trade_no="050917083121576"&subject="123456"&body="2010新款NIKE 耐克902第三代板鞋 耐克男女鞋 386201 白红"&total_fee="0.01"&notify_url="http://notify.java.jpxx.org/index.jsp"&success="true"&sign_type="RSA"&sign="d9pdkfy75G997NiPS1yZoYNCmtRbdOP0usZIMmKCCMVqbSG1P44ohvqMYRztrB6ErgEecIiPj9UldV5nSy9CrBVjV54rBGoT6VSUF/ufjJeCSuL510JwaRpHtRPeURS1LXnSrbwtdkDOktXubQKnIMg2W0PreT1mRXDSaeEECzc="}
				switch (msg.what) {
				case 处理快捷支付: {
					//
					closeProgress();

					BaseHelper.log("test", ret);

					// 处理交易结果
					try {
						// 获取交易状态码，具体状态代码请参看文档
						String tradeStatus = "resultStatus={";
						int imemoStart = ret.indexOf("resultStatus=");
						imemoStart += tradeStatus.length();
						int imemoEnd = ret.indexOf("};memo=");
						tradeStatus = ret.substring(imemoStart, imemoEnd);

						// 先验签通知
						ResultChecker resultChecker = new ResultChecker(ret);
						int retVal = resultChecker.checkSign();
						Log.d("test result checker", String.valueOf(retVal));
						// 验签失败
						if (retVal == ResultChecker.RESULT_CHECK_SIGN_FAILED) {
							// BaseHelper.showDialog(
							// this,
							// "提示",
							// getResources().getString(
							// R.string.check_sign_failed),
							// android.R.drawable.ic_dialog_alert);
							Toast.makeText(RechargeConfirmActivity.this, "验签失败", Toast.LENGTH_LONG).show();
						} else {// 验签成功。验签成功后再判断交易状态码
							if (tradeStatus.equals("9000"))// 判断交易状态码，只有9000表示交易成功
								Toast.makeText(RechargeConfirmActivity.this, "支付成功", Toast.LENGTH_LONG).show();
							else
								Toast.makeText(RechargeConfirmActivity.this, "支付失败。交易状态码:" + tradeStatus, Toast.LENGTH_LONG).show();
						}

					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(RechargeConfirmActivity.this, "提示" + ret, Toast.LENGTH_LONG).show();
					}
				}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	private ProgressDialog mProgress = null;

	void closeProgress() {
		try {
			if (mProgress != null) {
				mProgress.dismiss();
				mProgress = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void alipayFConfirm(boolean isQianbao) {
		MobileSecurePayHelper mspHelper = new MobileSecurePayHelper(this);
		final String actionString = mspHelper.getMspAction(isQianbao);
		Log.d("test actionString", "" + actionString);
		if (TextUtils.isEmpty(actionString)) {
			return;
		}

		// 先去ulaoda服务器生成订单。
		final wh.order.model.Order order = new wh.order.model.Order();
		order.setGameId(BaseActivity.getGameId());
		order.setServerId(BaseActivity.getServerId());
		order.setPaySourceType(OrderEnum.PaySourceType.Android客户端.getValue());

		order.setOrderType(OrderEnum.OrderType.已提交.getValue());
		order.setAccountType(OrderEnum.AccountType.D币.getValue());
		order.setChargeType(OrderEnum.ChargeType.充值游戏.getValue());
		order.setCreateDate(new Date());
		order.setModifyDate(new Date());

		order.setPayType(OrderEnum.PayType.支付宝.getValue());
		order.setStatus((byte) 1);
		order.setPayAccount(BigDecimal.valueOf(rechargeValue));
		order.setRealPayAccount(BigDecimal.valueOf(rechargeValue));
		dialog = showDialog();
		new Thread() {

			public void run() {
				// 生成订单
				MessageHeader messageHeader = new MessageHeader();
				messageHeader.init();

				MessageBody messageBody = new MessageBody("uld.sdk.bll.UserQianbao", "createOrder", new Class<?>[] {
						wh.order.model.Order.class, String.class, String.class, int.class, int.class }, new Object[] { order,
						String.valueOf(BaseActivity.getUserId()), "", getChannelId(), 7 });
				MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
				dialog.dismiss();

				if (!messageReturn.findErr()) {
					if (messageReturn.getRetObject() != null) {
						Map<String, Object> retMap = (Map<String, Object>) messageReturn.getRetObject();
						order.setOrderId((Integer) retMap.get("orderid"));

						// 从这里获取订单编号
						int orderId = (Integer) retMap.get("orderid");
						PartnerConfig.PARTNER = (String) retMap.get("partner");
						PartnerConfig.SELLER = (String) retMap.get("seller");
						PartnerConfig.RSA_PRIVATE = (String) retMap.get("privatekey");
						PartnerConfig.RSA_ALIPAY_PUBLIC = (String) retMap.get("publickey");
					}
				} else {
					Log.d("test", "生成订单失败");
					return;
				}

				if (order.getOrderId() <= 0) {
					Log.d("test", "其他错误");
					return;
				}

				String content = getOrderInfo(order);
				String sign = RSA.sign(content, PartnerConfig.RSA_PRIVATE);
				sign = URLEncoder.encode(sign);
				content += "&sign=\"" + sign + "\"&sign_type=\"RSA\"";
				MobileSecurePayer msp = new MobileSecurePayer();

				Log.d("test", "seller:" + PartnerConfig.SELLER);
				Log.d("test", "partner:" + PartnerConfig.PARTNER);
				Log.d("test", "private:" + PartnerConfig.RSA_PRIVATE);
				Log.d("test", "public:" + PartnerConfig.RSA_ALIPAY_PUBLIC);
				msp.pay(content, mHandler, 处理快捷支付, RechargeConfirmActivity.this, actionString);
			}
		}.start();

	}

	private String getOrderInfo(final wh.order.model.Order order) {
		StringBuffer orderInfo = new StringBuffer();
		orderInfo.append("partner=\"" + PartnerConfig.PARTNER);
		orderInfo.append("\"&seller=\"" + PartnerConfig.SELLER);
		orderInfo.append("\"&out_trade_no=\"" + order.getOrderId());
		orderInfo.append("\"&subject=\"" + "游老大充值");
		orderInfo.append("\"&body=\"" + "游戏币");
		orderInfo.append("\"&total_fee=\"" + order.getPayAccount());
		orderInfo.append("\"&notify_url=\"" + "http://payunite.ulaoda.com/AlipayNotifyBack.ashx");
		orderInfo.append("\"");

		return new String(orderInfo);
	}

	// ---------------------------------------快捷支付End-----------------------------------------//

	protected void alipayConfirm() {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putInt("RechargeValue", rechargeValue);
		intent.putExtras(bundle);
		intent.setClass(RechargeConfirmActivity.this, RechargeConfirmAliActivity.class);
		startActivityForResult(intent, BaseActivity.REQUESTCODE_RECHARGE);
	}

	protected void yinLianConfirm() {

		// 提交订单
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.init();
		dialog = showDialog();
		MessageBody messageBody = new MessageBody("uld.sdk.bll.User", "payYinLian", new Class<?>[] { Integer.class, Integer.class,
				Integer.class, Integer.class, String.class, Integer.class, String.class, int.class, int.class }, new Object[] {
				BaseActivity.getUserId(), (int) payType.getValue(), BaseActivity.getGameId(), BaseActivity.getServerId(),
				BaseActivity.getPlayerId(), rechargeValue, "", getChannelId(), 7 });

		MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
		dialog.dismiss();

		if (!messageReturn.findErr()) {
			if (messageReturn.getRetObject() != null) {
				String recMes = messageReturn.getRetObject().toString();

				// <merchantOrderId>2777XXXXXXXX</merchantOrderId>

				int startIndex = recMes.indexOf("<merchantOrderId>");
				int endIndex = recMes.indexOf("</merchantOrderId>");
				String strOrderId = "";
				if (startIndex > 0 && endIndex > 0) {
					strOrderId = recMes.substring(startIndex, endIndex);
					strOrderId = strOrderId.replaceAll("<merchantOrderId>", "");
					strOrderId = strOrderId.replaceAll("X", "");
				}

				if (uld.sdk.tools.Utility.getInt(strOrderId) > 0) {
					BaseActivity.setCurrentOrderId(uld.sdk.tools.Utility.getInt(strOrderId));

					// 跳转到银联界面
					byte[] to_upomp = recMes.getBytes();
					Bundle mbundle = new Bundle();
					// to_upomp为商户提交的XML
					mbundle.putByteArray("xml", to_upomp);

					mbundle.putString("action_cmd", "cmd_pay_plugin");
					// 更换参数调起测试与生产插件,value为true是测试插件 ，为false是生产插件
					mbundle.putBoolean("test", false);
					PluginHelper.LaunchPlugin(this, mbundle);
				} else {
					showToast("充值提交失败，请重新提交", Toast.LENGTH_LONG);
				}
			}
		} else {
			showToast(messageReturn.getErrMsg(), Toast.LENGTH_LONG);
		}
	}
}

package com.gionee.gsp.base;

import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.gionee.gsp.GnCommplatform;
import com.gionee.gsp.GnException;
import com.gionee.gsp.service.account.sdk.listener.IGnUiListener;
import com.gionee.pay.gsp.PayCallback;
import com.gionee.pay.third.GnCreateOrderUtils;

/**
 * @author chenls 开发者不用关注
 * 
 */
public class AmigoPayer {

    private static final String TAG = "AmigoPayer";

    public static final String STATE_CODE = "stateCode";
    // 金立支付服务升级
    public static final String RESULT_CODE_UPDATE = "6000";
    // 非金立手机升级的返回码
    public static final String RESULT_CODE_UPDATE_IS_NOT_GIONEE = "6003";

    // 创建订单成功的返回码
    public static final String CODE_ORDER_CREATE_SUCCESS = "200010000";

    // 是否需要重新调起支付
    protected boolean mIsNeedRePay;
    // 升级返回码
    protected String mResultCodeUpdate = "";

    // 标记是有账号还是无账号
    protected boolean mIsHaveAccount;

    // 用于升级后自动调用收银台的广播
    private Receiver mReceiver = new Receiver();

    // 订单信息
    private String mOrderInfo;

    private MyPayCallback mMyPayCallback;

    private Bitmap mIcon;

    private final int mGspFlag = 1;

    private Activity mActivity;

    private GnCommplatform mGnCommplatform;

    // 这里修改一下，不要跟默认的1111重复，免得出问题
    public final static int LOGIN_REQUEST_CODE = 3333;
    public final static int UPGRADE_USING_ACCOUNT_CODE = 2222;

    public static class ResultCode {
        public static final int LOGIN_SUCCESS = 1001;
        public static final int UPGRADE_USING_ACCOUNT_SUCCESS = 1007;
    }

    public AmigoPayer(Activity activity) {
        this.mActivity = activity;
        this.mGnCommplatform = GnCommplatform.getInstance(mActivity);
    }

    /**
     * 调起收银台
     * 
     * @param orderInfo
     * @throws GnException
     */
    public void pay(String orderInfo, MyPayCallback myPayCallback, Bitmap icon) throws GnException {
        this.mOrderInfo = orderInfo;
        mMyPayCallback = myPayCallback;
        mIcon = icon;
        mGnCommplatform.pay(mActivity, mOrderInfo, myPayCallback, mGspFlag, icon);
    }

    public abstract class MyPayCallback implements PayCallback {
        @Override
        public void payEnd(String stateCode) {
            // 支付服务正在进行升级操作或异常,监听升级后包的变化，升级成功后，自动调起收银台
            mResultCodeUpdate = stateCode;
            if (RESULT_CODE_UPDATE.equals(mResultCodeUpdate)
                    || RESULT_CODE_UPDATE_IS_NOT_GIONEE.equals(mResultCodeUpdate)) {
                Log.d(TAG, GnCreateOrderUtils.getThreadName() + "start listen");
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.intent.action.PACKAGE_ADDED");
                filter.addDataScheme("package");
                try {
                    mActivity.registerReceiver(mReceiver, filter);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                try {
                    mActivity.unregisterReceiver(mReceiver);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 返回一个当前应用的图标，会在支付的收银台显示该图标
     * 
     * @param iconId
     * @return
     */
    public Bitmap getBitmap(int iconId) {
        Resources res = mActivity.getResources();

        Bitmap bmp = BitmapFactory.decodeResource(res, iconId);

        return bmp;

    }

    /**
     * 在不能静默安装的手机，升级成功后自动调起收银台
     */
    public void onResume() {
        if (mIsNeedRePay) {
            mIsNeedRePay = false;
            try {
                mGnCommplatform.pay(mActivity, mOrderInfo, mMyPayCallback, mGspFlag, mIcon);
            } catch (GnException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    protected void onDestroy() {
        try {
            mActivity.unregisterReceiver(mReceiver);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 只在支付升级的时候有用
     * 
     * @author chenls
     * 
     */
    protected class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Log.d(TAG, "listening" + intent.getAction());
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                String packagename = intent.getDataString().substring(8);
                Log.d(TAG, "packagename :" + packagename);
                // 升级成功后，自动调起收银台
                if (packagename.equals("com.gionee.pay")) {
                    try {
                        context.unregisterReceiver(mReceiver);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (GnCreateOrderUtils.isNull(mResultCodeUpdate)) {
                        return;
                    }

                    // 自动启动收银台流程
                    if (RESULT_CODE_UPDATE.equals(mResultCodeUpdate)) {
                        try {
                            mGnCommplatform.pay(mActivity, mOrderInfo, mMyPayCallback, mGspFlag, mIcon);
                        } catch (GnException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else if (RESULT_CODE_UPDATE_IS_NOT_GIONEE.equals(mResultCodeUpdate)) {
                        mIsNeedRePay = true;
                    }

                    // 重置返回码
                    mResultCodeUpdate = "";
                }
            }
        }
    }

    public boolean checkIsLogin() throws GnException {
        // 是否未登陆账号
        if (!mGnCommplatform.isAccountLogin(mActivity)) {
            mGnCommplatform.logoutAccount(mActivity);
            return false;
        }
        return true;
    }


    public boolean mIsNeedReCreateOrder = false;

    /**
     * 检查支付的前置条件
     * 
     * @return
     * @throws GnException
     */
    public boolean check() throws GnException {
        if (!checkIsLogin()) {
            mIsNeedReCreateOrder = true;
            return !mIsNeedReCreateOrder;
        }
        mIsNeedReCreateOrder = false;
        return !mIsNeedReCreateOrder;
    }
    
    
  
   class GnUiListener implements IGnUiListener {

        @Override
        public void onError(Exception e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onComplete(JSONObject jsonObject) {
            try {
                // 检查支付的前置条件,有正式账号登录
//                if (com.gionee.gsp.AmigoPayer.check(new GnUiListener())) {
//                    createOrder();
//                }
            }  catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onCancel() {

        }

    }

    /**
     * 读取apiKey
     * 
     * @param context
     * @param apiKey
     * @return
     */
    public static String readApiKey(Context context, String apiKey) {
        SharedPreferences preference = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return preference.getString("apiKey", apiKey);
    }

    /**
     * 更新apiKey
     * 
     * @param context
     * @param apiKey
     */
    public static void writeApiKey(Context context, String apiKey) {
        SharedPreferences preference = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putString("apiKey", apiKey);
        editor.commit();
    }

}

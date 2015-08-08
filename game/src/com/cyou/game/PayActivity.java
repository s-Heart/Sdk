package com.cyou.game;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyouwanwan.sdk.GameLib;
import com.cyouwanwan.sdk.callback.PayCallback;

public class PayActivity extends Activity implements OnClickListener {
  private Button button;
  private EditText productIdText;
  private EditText productNameText;
  private EditText orderIdText;
  private EditText priceText;
  private EditText quantityText;
  private String price;
  private String quantity;
  private TextView resultTextView;
  private Context mContext;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.pay_main);
    this.mContext = this;
    initView();
  }

  private void initView() {
    button = (Button) findViewById(R.id.button);
    productIdText = (EditText) findViewById(R.id.productId);
    productNameText = (EditText) findViewById(R.id.productName);
    priceText = (EditText) findViewById(R.id.price);
    quantityText = (EditText) findViewById(R.id.quantity);
    orderIdText = (EditText) findViewById(R.id.orderId);
    resultTextView = (TextView) findViewById(R.id.result);
    button.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch(v.getId()){
    case R.id.button:
    	String productId = productIdText.getText().toString().trim();
    	String productName = productNameText.getText().toString().trim();
    	String orderId = orderIdText.getText().toString().trim();
    	price = priceText.getText().toString().trim();
    	quantity = quantityText.getText().toString().trim();
    	if(!isNotEmpty(productId)&&!isNotEmpty(productName)&&!isNotEmpty(orderId)&&!isNotEmpty(price)){
			toPay();
    	}else{
    		resultTextView.setText("物品ID,物品名称,订单ID 不能为空");
    	}
      break;

    }
  }

private void toPay() {
	String productId = productIdText.getText().toString();
	String productName = productNameText.getText().toString();
	int priceText = Integer.valueOf(price);
	int quantityText = Integer.valueOf(quantity);
	String orderId = orderIdText.getText().toString();
	/**
	 * productId :道具ID
	 * productName:道具名称
	 * priceText : 道具价格(单价),此价格为厂商和平台约定的价格,如果错误,会支付失败,此DEMO 写的是1改为别的值可能发生错误
	 * quantityText : 购买道具数量
	 * orderId :订单ID ,注意,必须全局唯一,不能相同
	 */
	GameLib.getInstance(this).pay(productId,productName,priceText,quantityText,orderId,new PayCallback() {

	    @Override
	    public void onPayError() {
	       resultTextView.setText("支付页面打开失败");
	    }
	    @Override
	    public void onPayCancel() {
	      resultTextView.setText("关闭支付框");
	    }
	  });
}


public static boolean isNotEmpty(String str) {
	return TextUtils.isEmpty(str);
}

//public static boolean isNotEmpty(String str) {
//	if(str != null){
//		String s = str.trim();
//		if(s.length() > 0){
//			return true;
//		}else{
//			return false;
//		}
//	}else{
//		return false;
//	}
//}

}

package com.cyou.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.cyouwanwan.sdk.GameLib;

public class MainActivity extends Activity implements OnClickListener {
  private Button loginBtn;
  private Button shoppingBtn;
  private Button logoutBtn;
  
  private Context mContext;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);
    mContext = this;
    initView();
  }

  private void initView() {
    loginBtn = (Button) findViewById(R.id.login);
    shoppingBtn = (Button) findViewById(R.id.shopping);
    logoutBtn = (Button) findViewById(R.id.logout);
    
    loginBtn.setOnClickListener(this);
    shoppingBtn.setOnClickListener(this);
    logoutBtn.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch(v.getId()){
    case R.id.login:
      Intent login  = new Intent(mContext,LoginActivity.class);
      mContext.startActivity(login);
      break;
    case R.id.logout:
      Intent logout  = new Intent(mContext,LogoutActivity.class);
      mContext.startActivity(logout);
      break;
    case R.id.shopping:
      Intent pay  = new Intent(mContext,PayActivity.class);
      mContext.startActivity(pay);
      break;
    }
  }
  
  @Override
  protected void onDestroy() {
    Log.d("cyou","-----onDestroy----");
    GameLib.exit();
    super.onDestroy();
  }
}

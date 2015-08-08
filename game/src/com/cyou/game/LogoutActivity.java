package com.cyou.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyouwanwan.sdk.GameLib;
import com.cyouwanwan.sdk.callback.LogoutCallback;
import com.cyouwanwan.sdk.util.Utils;

public class LogoutActivity extends Activity implements OnClickListener {
  private Button button;
  private EditText successEditText;
  private TextView resultTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.logout_main);

    initView();
  }

  private void initView() {
    button = (Button) findViewById(R.id.button);
    successEditText = (EditText) findViewById(R.id.success);
    resultTextView = (TextView) findViewById(R.id.result);
    button.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch(v.getId()){
    case R.id.button:
      GameLib.getInstance(this).logout(new LogoutCallback() {

        @Override
        public void onLogoutSuccess() {
           String successStr = successEditText.getText().toString().trim();
           if(!Utils.isEmpty(successStr)){
             Utils.toast(getApplicationContext(),successStr);
           }
           resultTextView.setText("注销成功");
        }

      });
      break;

    }
  }

}
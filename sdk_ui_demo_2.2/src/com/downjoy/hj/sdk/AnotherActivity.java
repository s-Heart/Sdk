package com.downjoy.hj.sdk;

import com.downjoy.Downjoy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AnotherActivity extends Activity {

    private Downjoy downjoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.flags = params.flags
                | WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setAttributes(params);
        // 获取Downjoy的实例，前提是Downjoy已经被初始化过，并且没有被destroy掉。
        downjoy = Downjoy.getInstance();
    }

    @Override
    protected void onResume() {
        // 调用Downjoy的resume()方法
        if (downjoy != null) {
            downjoy.resume(AnotherActivity.this);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        // 调用Downjoy的pause()方法
        if (downjoy != null) {
            downjoy.pause();
        }
        super.onPause();
    }
}

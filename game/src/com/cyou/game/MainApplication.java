package com.cyou.game;

import android.app.Application;

import com.cyouwanwan.sdk.GameLib;

public class MainApplication extends Application {

  @Override
  public void onCreate() {
	  /**
	   * 初始化SDK，设置游戏在字符无平台上注册时得到的id和私密串 需要在app启动时进行设定，并且只能调用一次，多次调用将会 引发异常。
	   * @param context 应用的上下文Context对象，不可使用getApplicationContext()获取
	   * @param gameId 游戏在平台上分配的唯一标识
	   * @param gameSecret 游戏平台给游戏分配的一个私密字符串， 只有游戏开发商和平台知道，用于通信加密校验
	   * @param vendorName 开发商名称,最好是英文字母,否则会因为编码问题产生乱码
	   * @param distributionChannel 渠道名称 
	   * @throws IllegalStateException 多次调用会抛出此异常
	   */
	  GameLib.initialize("1","3049safdlkjwqeroufsafdler","cyouAndroid","androidTest1");
    super.onCreate(); 
  }
}

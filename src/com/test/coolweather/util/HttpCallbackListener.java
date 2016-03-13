package com.test.coolweather.util;

/**
 * 回调接口
 * 回调服务器返回的结果
 * @author sungang
 *
 */
public interface HttpCallbackListener {
	//服务器返回成功
	void onFinish(String response);
	//服务器返回失败
	void onError(Exception e);
}

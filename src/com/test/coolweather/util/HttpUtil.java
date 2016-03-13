package com.test.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 向服务器发送请求工具类
 * @author sungang
 *
 */
public class HttpUtil {
	public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
		// 通过子线程发送网络请求，防止主线程由于网络原因而阻塞
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpURLConnection conn = null;
				try {
					URL url = new URL(address);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(8000);
					conn.setReadTimeout(8000);
					InputStream inputStream = conn.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					StringBuilder response = new StringBuilder();
					String line = "";
					while((line = reader.readLine()) != null){
						response.append(line);
					}
					
					if(listener != null){
						listener.onFinish(response.toString());
					}
					
				} catch (Exception e) {
					if(listener != null){
						listener.onError(e);
					}
				}
			}
		}).start();
	}
	
}

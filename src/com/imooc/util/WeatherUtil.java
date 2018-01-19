package com.imooc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherUtil {
	
	public static String sendGet (String url,String param){
		String result = "";
		BufferedReader in = null;
		try{
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			//打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			//设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1))");
			//建立实际连接
			connection.connect();
			
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine())!=null) {
				result += line;
			}
		}catch (Exception e) {
			System.out.println("发送GET请求出现异常！"+e);
			e.printStackTrace();
		}
		return result;
	}
}

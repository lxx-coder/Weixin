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
			//�򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			//����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1))");
			//����ʵ������
			connection.connect();
			
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine())!=null) {
				result += line;
			}
		}catch (Exception e) {
			System.out.println("����GET��������쳣��"+e);
			e.printStackTrace();
		}
		return result;
	}
}

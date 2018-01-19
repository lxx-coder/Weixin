package com.imooc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class WeatherUtil {
	
	public static String getWeather(String city) throws UnsupportedEncodingException, DocumentException{
		String nameCode = URLEncoder.encode(city,"utf-8");
		String string = sendGet("http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather", 
				"theCityCode="+nameCode+"&theUserID=");
		Document document = DocumentHelper.parseText(string);
		Element root = document.getRootElement();
		StringBuffer sBuffer = new StringBuffer();
		List<Element> list = root.elements();
		for(Element e:list){
			if(e.getText().equals("��ѯ���Ϊ��")){
				sBuffer.append("��ѯ���Ϊ��");
			}else{
				sBuffer.append(e.getText());
			sBuffer.append("\n");
			}
		}
		return sBuffer.toString();
	}
	
	private static String sendGet (String url,String param){
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

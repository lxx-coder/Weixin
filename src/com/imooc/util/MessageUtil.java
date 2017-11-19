package com.imooc.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import com.imooc.po.textMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";

	
	/**
	 * xmlתΪMap����
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) 
			throws IOException, DocumentException{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	public static String textMessageToXml(textMessage textMessage){
		/**
		 * ���ı���Ϣ����ת��Ϊxml
		 */
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);		
	}
	
	public static String initText(String toUserName,String fromUserName,String content){
		textMessage text = new textMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}
		
	/**
	 * ���˵�
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ���Ĺ�ע�����ǻ���һЩ����˼�Ĳ�����\n\n");
		sb.append("�������������֣������ϲ���ʶ\n");
//		sb.append("2��Ľ��������\n\n");
//		sb.append("�ظ��������˵���");
		return sb.toString();
	}
	
	public static String sayHello(String content){
		String[] helloBox = new String[]{
				"�ߣ��Ҳ�����ʶ�㣬�һ�֪����ĺܶ�С���ܣ�������ϴ�ּ������ϴ�֡�",
				"������Ŀ�У������ѧϰ����ûɶ��������",
				"����ʶ���ʱ�䲻��������֪�������һ������/��Ů[��Ц]",
				"�������������׹��[����]",
				"��ã�����ֻ�Ƿ���֮�����ò��ż�΢�Ű�",
				"Ŷ��"+content+",���������ˣ����Ǹ���������ҵĺ��ӣ�",
				content+"�����кö໰�����˵�������´���˵��",
				"ִ���࿴���ۣ���������ҭ",
				content+"��������˵�ң���Ҫ������",
				"ɶ�������������ֳ����ˣ�",
				"�ϸ����ˣ��ϸ�����"
		};
//		Random random = new Random();
		int s = (int)(System.currentTimeMillis()%10);
		return helloBox[s];
	}
	
	public static String firstMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("���׿γ̽���΢�Ź��ںſ�������Ҫ�漰���ںŽ��ܡ��༭ģʽ���ܡ�����ģʽ���ܵȡ�");
		return sb.toString();
	}
	
	public static String secondMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("Ľ�����γ̺���ǰ�˿�����PHP��Html5��Android��iOS��Swift��ITǰ�ؼ������ԣ�"
				+ "���������γ̡�ʵ�ð������߼������������ͣ��ʺϲ�ͬ�׶ε�ѧϰ��Ⱥ��"
				+ "�Դ��ɻ�������Ƶ����ʽΪƽ̨�ص㣬Ϊ��Уѧ����ְ�������ṩ��һ��Ѹ���������ܡ���ͬ���������ѧϰƽ̨��");
		return sb.toString();
	}
}

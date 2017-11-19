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
	 * xml转为Map集合
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
		 * 将文本消息对象转换为xml
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
	 * 主菜单
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，我们会有一些有意思的操作：\n\n");
		sb.append("请输入您的名字，看我认不认识\n");
//		sb.append("2，慕课网介绍\n\n");
//		sb.append("回复？调出菜单。");
		return sb.toString();
	}
	
	public static String sayHello(String content){
		String[] helloBox = new String[]{
				"哼，我不仅认识你，我还知道你的很多小秘密，比如上洗手间从来不洗手。",
				"在我心目中，你除了学习，就没啥不良癖好了",
				"我认识你的时间不长，但我知道你就是一个渣男/腐女[奸笑]",
				"久仰大名，如雷贯耳[无语]",
				"你好，我们只是泛泛之交，用不着加微信吧",
				"哦，"+content+",我想起来了，你是隔壁王叔叔家的孩子？",
				content+"，我有好多话想对你说，还是下次再说吧",
				"执手相看泪眼，竟无语凝噎",
				content+"，你老是说我，我要打死你",
				"啥，今天刘池莉又长胖了？",
				"严哥来了，严哥来了"
		};
//		Random random = new Random();
		int s = (int)(System.currentTimeMillis()%10);
		return helloBox[s];
	}
	
	public static String firstMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("本套课程介绍微信公众号开发，主要涉及公众号介绍、编辑模式介绍、开发模式介绍等。");
		return sb.toString();
	}
	
	public static String secondMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，"
				+ "包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。"
				+ "以纯干货、短视频的形式为平台特点，为在校学生、职场白领提供了一个迅速提升技能、共同分享进步的学习平台。");
		return sb.toString();
	}
}

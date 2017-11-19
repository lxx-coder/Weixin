package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.dom4j.DocumentException;

import com.imooc.po.textMessage;
import com.imooc.util.CheckUtil;
import com.imooc.util.MessageUtil;

public class WeixinServlet extends HttpServlet {
	HashSet<String> userName = new HashSet<String>();
	
	public WeixinServlet() {
		userName.add("张楠");
		userName.add("刘池莉");
		userName.add("周辉");
		userName.add("许姚三川");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter out = resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try{
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				if(userName.contains(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.sayHello(content));
				}else{
					String greeting = "初次见面，幸会幸会";
					userName.add(content);
					message = MessageUtil.initText(toUserName, fromUserName, greeting);
				}
//				else if("?".equals(content)||"？".equals(content)){
//					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
//				}
				
//				textMessage text = new textMessage();
//				text.setFromUserName(toUserName);
//				text.setToUserName(fromUserName);
//				text.setMsgType("text");
//				text.setCreateTime(new Date().getTime());
//				text.setContent("您发送的消息是： "+content);
//				message = MessageUtil.textMessageToXml(text);
			}else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
				String eventType = map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}
			}
			System.out.println(message);
			
			out.print(message);
		}catch(DocumentException e){
			e.printStackTrace();
		}finally{
			out.close();
		}
			
	}
}

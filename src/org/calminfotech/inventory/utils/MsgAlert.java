/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.inventory.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author megaweb
 */
public class MsgAlert {

	private boolean msgExist;
	private String msgInfo;
	private List<Map<String, String>> messages;
	public static final int ERROR = 1;
	public static final int SUCCESS = 2;
	public static final int INFO = 3;
	public static final int NOTICE = 4;
	public static final int WARNING = 6;

	public MsgAlert() {
		this.messages = new ArrayList();
	}

	public MsgAlert(String msgInfo, int type) {
		this.msgExist = true;
		this.messages = new ArrayList();
		save(msgInfo, type);
	}

	private void save(String msg, int type) {

		if (msgExist == false) {
			msgExist = true;
		}
		String prt = "";
		String alertTypPrt = "alert-";
		Map<String, String> messageInfo = new HashMap();
		switch (type) {

		case ERROR:
			prt = "Error: ";
			messageInfo.put("cssClass", alertTypPrt + "danger");
			break;

		case SUCCESS:
			prt = "Success: ";
			messageInfo.put("cssClass", alertTypPrt + "success");
			break;
		case INFO:
			prt = "Message: ";
			messageInfo.put("cssClass", alertTypPrt + "info");
			break;

		case NOTICE:
			prt = "";
			messageInfo.put("cssClass", alertTypPrt + "info");
			break;
		case WARNING:
			prt = "Warning: ";
			messageInfo.put("cssClass", alertTypPrt + "warning");
			break;
		}

		messageInfo.put("msgInfo", prt + msg);
		messages.add(messageInfo);
	}

	public void add(String msg, int type) {
		this.save(msg, type);
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public boolean isMsgExist() {
		return msgExist;
	}

	public void setMsgExist(boolean msgExist) {
		this.msgExist = msgExist;
	}

	public List<Map<String, String>> getMessages() {
		return messages;
	}

	public void setMessages(List<Map<String, String>> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return this.messages.toString();
	}
}

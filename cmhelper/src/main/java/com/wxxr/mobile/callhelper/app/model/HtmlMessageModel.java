package com.wxxr.mobile.callhelper.app.model;

import java.io.Serializable;

import com.wxxr.mobile.core.annotation.BindableBean;

/**
 * 
 * @author fudapeng
 *
 */

@BindableBean(pkg="com.wxxr.mobile.callhelper.app.bean",className="HtmlMessageBean")
public class HtmlMessageModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long msgId;
	private HtmlContent htmlMessage;
	private String remoteURL;
	private long readTime;
	public Long getMsgId() {
		return msgId;
	}
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	public HtmlContent getHtmlMessage() {
		return htmlMessage;
	}
	public void setHtmlMessage(HtmlContent htmlMessage) {
		this.htmlMessage = htmlMessage;
	}
	public String getRemoteURL() {
		return remoteURL;
	}
	public void setRemoteURL(String remoteURL) {
		this.remoteURL = remoteURL;
	}
	public long getReadTime() {
		return readTime;
	}
	public void setReadTime(long readTime) {
		this.readTime = readTime;
	}
	
}

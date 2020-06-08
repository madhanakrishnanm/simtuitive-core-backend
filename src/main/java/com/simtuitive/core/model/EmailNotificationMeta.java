package com.simtuitive.core.model;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailNotificationMeta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String from;
	
	public String[] to;	
	
	private String[] cc;

	public String subject;
	
	public String text;

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	/**
	 * @return the cc
	 */
	public String[] getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	public EmailNotificationMeta(String from, String[] to, String[] cc, String subject, String text) {
		super();
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.subject = subject;
		this.text = text;
	}
	

	public EmailNotificationMeta() {

		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmailNotificationMeta [from=" + from + ", to=" + Arrays.toString(to) + ", cc=" + Arrays.toString(cc)
				+ ", subject=" + subject + ", text=" + text + "]";
	}
	
	
}

package com.event;

import java.util.Date;

/**
 * 
 * @author Ravi Ranjan
 * 
 * Event class will represent each event in the file
 *
 */

public class Event {
	private int eventPriority;
	private String eventName;
	private Date eventTime;
	
	
	
	public int getEventPriority() {
		return eventPriority;
	}
	public void setEventPriority(int eventPriority) {
		this.eventPriority = eventPriority;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
}

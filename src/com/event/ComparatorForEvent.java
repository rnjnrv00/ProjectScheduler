package com.event;

import java.util.Comparator;

/**
 * 
 * @author Ravi Ranjan
 * 
 * comparatorForEvent will provide comparator to sort event listS
 *
 */

public class ComparatorForEvent implements Comparator<Event>{


	public int compare(Event o1,Event o2) {
		if(o1.getEventTime().before(o2.getEventTime())) {
			return -1;
		}else if(o1.getEventTime().after(o2.getEventTime())) {
			return 1;
		}else {
			if(o1.getEventPriority() > o2.getEventPriority()) {
				return 1;
			}else if(o1.getEventPriority() < o2.getEventPriority()) {
				return -1;
			}else {
				return 0;
			}
		}
	}
}

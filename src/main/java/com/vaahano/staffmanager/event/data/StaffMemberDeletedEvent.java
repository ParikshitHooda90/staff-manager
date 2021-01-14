package com.vaahano.staffmanager.event.data;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
@Getter
public class StaffMemberDeletedEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = -8733450929356625240L;
	
	private String businessUnit;
	private String staffId; 
	
	public StaffMemberDeletedEvent(Object source, String staffId, String businessUnit ) {
		super(source);
		this.staffId  = staffId;
		this.businessUnit = businessUnit;
				
	}
}

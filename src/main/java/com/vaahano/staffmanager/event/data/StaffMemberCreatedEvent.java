package com.vaahano.staffmanager.event.data;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class StaffMemberCreatedEvent extends  ApplicationEvent {
	
	private String staffId;
	private String businessUnit;
	
	public StaffMemberCreatedEvent(Object source, String staffId, String businessUnit) {
		super(source);
		this.staffId = staffId;
		this.businessUnit = businessUnit;
	}

	
}

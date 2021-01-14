package com.vaahano.staffmanager.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.vaahano.staffmanager.event.data.StaffMemberCreatedEvent;
import com.vaahano.staffmanager.event.data.StaffMemberDeletedEvent;

@Component
public class StaffMemberCRUDEventPublisher {
	
	@Autowired 
	private ApplicationEventPublisher publisher;
	
	public void publishStaffMemberCreatedEvent(String staffId, String businessUnit) {
		StaffMemberCreatedEvent event = new StaffMemberCreatedEvent(this, staffId, businessUnit);
		publisher.publishEvent(event);
	}
	
	public void publishStaffMemberRemovedEvent(String staffId, String businessUnit) {
		StaffMemberDeletedEvent event = new StaffMemberDeletedEvent(this, staffId, businessUnit);
		publisher.publishEvent(event);
	}
}

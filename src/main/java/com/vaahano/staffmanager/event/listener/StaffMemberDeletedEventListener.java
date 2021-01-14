package com.vaahano.staffmanager.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.vaahano.staffmanager.event.data.StaffMemberDeletedEvent;
import com.vaahano.staffmanager.service.api.BusinessUnitService;

public class StaffMemberDeletedEventListener implements ApplicationListener<StaffMemberDeletedEvent> {
	
	@Autowired BusinessUnitService businessUnitService; 
	
	@Override
	public void onApplicationEvent(StaffMemberDeletedEvent event) {
		businessUnitService.removeStaffMemberFromBusinessUnit(event.getBusinessUnit(),
				event.getStaffId());
		
	}

}

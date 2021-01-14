package com.vaahano.staffmanager.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vaahano.staffmanager.event.data.StaffMemberCreatedEvent;
import com.vaahano.staffmanager.service.api.BusinessUnitService;

@Component
public class StaffMemberCreatedListener implements ApplicationListener<StaffMemberCreatedEvent>{
	
	@Autowired BusinessUnitService businessUnitService;
	
	@Override
	public void onApplicationEvent(StaffMemberCreatedEvent event) {
		businessUnitService.addStaffMemberToBusinessUnit(event.getBusinessUnit(), event.getStaffId());
	}

}

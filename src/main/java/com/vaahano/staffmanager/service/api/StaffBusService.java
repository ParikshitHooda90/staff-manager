package com.vaahano.staffmanager.service.api;

import org.springframework.stereotype.Service;

@Service
public interface StaffBusService {
	
	void assignBusToStaffMember(String businessUnit, String staffId, String busId);
	
	// return busId assigned to this staff member.
	String getAssignedBusToStaffMember(String businessUnit, String staffId);
}

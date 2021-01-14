package com.vaahano.staffmanager.service.api;

import org.springframework.stereotype.Service;

import com.vaahano.staffmanager.exception.StaffManagerException;

@Service
public interface StaffBusService {
	
	void assignBusToStaffMember( String staffId, String busId) throws StaffManagerException;
	
	// return busId assigned to this staff member.
	String getAssignedBusToStaffMember( String staffId) throws StaffManagerException;
}

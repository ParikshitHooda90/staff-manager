package com.vaahano.staffmanager.service.api;

import java.util.Optional;

import com.vaahano.staffmanager.exception.StaffManagerException;

public interface StaffBusService {
	
	void assignBusToStaffMember( String staffId, String busId) throws StaffManagerException;
	
	// return busId assigned to this staff member.
	 Optional<String>  getAssignedBusToStaffMember( String staffId) throws StaffManagerException;
}

package com.vaahano.staffmanager.service.api;

import org.springframework.stereotype.Service;

import com.vaahano.staffmanager.exception.StaffManagerExeption;

@Service
public interface StaffBusService {
	
	void assignBusToStaffMember( String staffId, String busId) throws StaffManagerExeption;
	
	// return busId assigned to this staff member.
	String getAssignedBusToStaffMember( String staffId) throws StaffManagerExeption;
}

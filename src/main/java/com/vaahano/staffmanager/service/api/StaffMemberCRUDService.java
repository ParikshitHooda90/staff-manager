package com.vaahano.staffmanager.service.api;

import com.vaahano.staffmanager.bean.CreateStaffMember;
import com.vaahano.staffmanager.bean.StaffMemberResponse;
import com.vaahano.staffmanager.exception.StaffManagerExeption;


public interface StaffMemberCRUDService {
	
	void createStaffMember(CreateStaffMember member) throws StaffManagerExeption;
	
	StaffMemberResponse getStaffMember(String staffId) throws StaffManagerExeption;
	
	
}

package com.vaahano.staffmanager.service.api;

import com.vaahano.staffmanager.bean.CreateStaffMember;
import com.vaahano.staffmanager.bean.StaffMemberLoginResponse;
import com.vaahano.staffmanager.bean.StaffMemberResponse;
import com.vaahano.staffmanager.exception.StaffManagerException;


public interface StaffMemberCRUDService {
	
	void createStaffMember(CreateStaffMember member) throws StaffManagerException;
	
	StaffMemberResponse getStaffMember(String staffId) throws StaffManagerException;
	
	void deleteStaffMember(String staffId) throws StaffManagerException;
	
	StaffMemberLoginResponse doLogin(String staffId, String password) throws StaffManagerException;
}
package com.vaahano.staffmanager.service.api;

import com.vaahano.staffmanager.bean.CreateStaffMember;
import com.vaahano.staffmanager.bean.StaffLoginResponse;
import com.vaahano.staffmanager.bean.StaffLogoutResponse;
import com.vaahano.staffmanager.bean.StaffMemberResponse;
import com.vaahano.staffmanager.exception.StaffManagerException;


public interface StaffMemberService {
	
	void createStaffMember(CreateStaffMember member) throws StaffManagerException;
	
	StaffMemberResponse getStaffMember(String staffId) throws StaffManagerException;
	
	void deleteStaffMember(String staffId) throws StaffManagerException;
	
	StaffLoginResponse doLogin(String staffId, String password) throws StaffManagerException;
	
	StaffLogoutResponse doLogout(String staffId, String authToken) throws StaffManagerException;

}
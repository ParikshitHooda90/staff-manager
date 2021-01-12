package com.vaahano.staffmanager.service.api;

import org.springframework.stereotype.Service;

import com.vaahano.staffmanager.bean.CreateStaffMember;
import com.vaahano.staffmanager.bean.StaffMember;

@Service
public interface StaffMemberCRUDService {
	
	void createStaffMember(CreateStaffMember member);
	
	StaffMember getStaffMember(String businessUnit, String staffId);
	
	
}

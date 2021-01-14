package com.vaahano.staffmanager.service.api;

public interface BusinessUnitService {
	
	void createBusinessUnit(String businessUnit, String country, String state);
	
	void addStaffMemberToBusinessUnit(String businessUnit, String staffId);
	
	void removeStaffMemberFromBusinessUnit(String businessUnit, String staffId);
	
}

package com.vaahano.staffmanager.service.api;

import java.util.List;

public interface BusinessUnitService {
	
	void createBusinessUnit(String businessUnit, String country, String state);
	
	List<String> getAllBusinessUnits();
	
	void addStaffMemberToBusinessUnit(String businessUnit, String staffId);
	
	void removeStaffMemberFromBusinessUnit(String businessUnit, String staffId);
	
	List<String> getAllStaffMemberIdsForBusinessUnit(String businessUnit);
}

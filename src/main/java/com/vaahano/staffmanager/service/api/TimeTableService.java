package com.vaahano.staffmanager.service.api;

import com.vaahano.staffmanager.bean.TimeTableResponse;

public interface TimeTableService {
	
	public TimeTableResponse getBusinessUnitTimeTableForCity(String businessUnit, String city);
}

package com.vaahano.staffmanager.bean;

import lombok.Data;

@Data
public class StaffMemberLoginResponse {
	
	private boolean isValidated;
	private String authToken;
}

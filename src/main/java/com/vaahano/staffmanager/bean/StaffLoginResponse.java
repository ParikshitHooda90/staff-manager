package com.vaahano.staffmanager.bean;

import lombok.Data;

@Data
public class StaffLoginResponse {
	
	private boolean isValidated;
	private String authToken;
}

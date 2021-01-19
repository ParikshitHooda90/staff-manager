package com.vaahano.staffmanager.bean;

import lombok.Data;

@Data
public class StaffLoginRequest {
	
	private String staffId;
	private String password;
}

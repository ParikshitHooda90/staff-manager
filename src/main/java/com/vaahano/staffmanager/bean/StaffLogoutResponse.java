package com.vaahano.staffmanager.bean;

import lombok.Data;

@Data
public class StaffLogoutResponse {
	private String staffId;
	private boolean successful;
}

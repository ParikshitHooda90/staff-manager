package com.vaahano.staffmanager.bean;

import lombok.Data;

@Data
public class CreateStaffMember {
	
	private String name;
	private String phoneNumber;
	private String businessUnit;
	private String designation;
	private String role;
}

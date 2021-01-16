package com.vaahano.staffmanager.db.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("staff_member")
public class StaffMember {
	
	@Id
	private String id;
	private String name;
	private String password;
	private String phoneNumber;
	private String businessUnit;
	private String designation;
	private String role;
	private String assignedBus;
	private String busLastAssignedOn;
	private String busLastAssignedBy;
	
	
}

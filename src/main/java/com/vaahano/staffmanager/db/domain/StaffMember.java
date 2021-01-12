package com.vaahano.staffmanager.db.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("staff_member")
public class StaffMember {
	
	@Id
	private Long id;
	private String name;
	private String phoneNumber;
	private String businessUnit;
	private String designation;
	private String role;
	
	
}

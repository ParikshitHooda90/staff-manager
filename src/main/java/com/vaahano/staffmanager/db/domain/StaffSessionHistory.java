package com.vaahano.staffmanager.db.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("staff_session_history")
public class StaffSessionHistory {
	private String staffId;
	private String authToken;
	private String designation;
	private String role;
	private String loginTime;
	private String logoutTime;
}

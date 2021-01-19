package com.vaahano.staffmanager.db.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("staff_session_active")
public class StaffSessionActive {
	
	/** It will keep all logged in staff users.
	 * This entry will be removed on logout and entry will be transferred in history collection.
	 * make sure that one staff credentials can be logged in by multiple 
	 * people simuntaneously as of now*/
	
	@Id
	private String authToken;
	private String staffId;
	private String loginTime;
}

package com.vaahano.staffmanager.db.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("business-unit")
public class BusinessUnit {
	
	@Id
	String businessUnit;//PRTC, PEPSU, PUNBUS
	String country; // india
	String state; // punjab
//	String subBusinessUnit; // punjab-interstate, punjab-in-state
	List<String> staffIds;
}

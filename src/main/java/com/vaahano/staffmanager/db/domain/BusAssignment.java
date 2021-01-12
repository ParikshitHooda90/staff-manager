package com.vaahano.staffmanager.db.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document("bus-assignment")
public class BusAssignment {
	
	@Id
	private String id;
	private String staffId;
	private String businessUnit;
	private String busId;
	private LocalDateTime assignedOn;
	
}

package com.vaahano.staffmanager.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StaffLoginResponse  extends MetaResponse {
	
	private boolean isValidated;
	private String authToken;
}

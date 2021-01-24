package com.vaahano.staffmanager.bean;

import lombok.Data;

@Data
public class MetaResponse {
	private boolean isSuccessful;
	private String errorMessage;
}

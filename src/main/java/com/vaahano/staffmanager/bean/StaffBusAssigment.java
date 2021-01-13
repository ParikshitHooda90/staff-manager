package com.vaahano.staffmanager.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class StaffBusAssigment implements Serializable {
	
	private static final long serialVersionUID = -2872571979148599175L;
	
	private String busId;
	private String staffId;
}

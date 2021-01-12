package com.vaahano.staffmanager.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaahano.staffmanager.bean.CreateStaffMember;

@RestController
@RequestMapping("/staff")
public class StaffCRUDController {
	
	@PostMapping("/create")
	private void createStaffMember(@RequestBody CreateStaffMember request) {
		
	}
}

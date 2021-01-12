package com.vaahano.staffmanager.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaahano.staffmanager.bean.AssignedBus;
import com.vaahano.staffmanager.bean.ConductorBusAssigment;
import com.vaahano.staffmanager.service.api.StaffBusService;

@RestController
@RequestMapping("/conductor")
public class BusAssignmentController {
	
	@Autowired StaffBusService staffBusService;
	
	@GetMapping("{businessUnit}/{staffId}/assignedBus")
	private ResponseEntity<AssignedBus> getAssignedBusOfConductor(@PathVariable String staffId,@PathVariable String businessUnit) {
		
		String busId = staffBusService.getAssignedBusToStaffMember(businessUnit, staffId);
		AssignedBus  bus = new AssignedBus();
		bus.setBusId(busId);
		return ResponseEntity.status(HttpStatus.OK).body(bus);
	}
	
	@PutMapping
	private void assignBusToStaffMember(@RequestBody ConductorBusAssigment request) {
		String staffId = request.getStaffId();
		String busId = request.getBusId();
	}
}

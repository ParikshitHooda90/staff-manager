package com.vaahano.staffmanager.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaahano.staffmanager.bean.AssignedBus;
import com.vaahano.staffmanager.bean.CreateStaffMember;
import com.vaahano.staffmanager.bean.StaffBusAssigment;
import com.vaahano.staffmanager.bean.StaffMemberResponse;
import com.vaahano.staffmanager.exception.StaffManagerException;
import com.vaahano.staffmanager.service.api.BusinessUnitService;
import com.vaahano.staffmanager.service.api.StaffBusService;
import com.vaahano.staffmanager.service.api.StaffMemberCRUDService;

@RestController
@RequestMapping("/staff")
public class StaffMemberController {
	
	@Autowired StaffBusService staffBusService;
	@Autowired StaffMemberCRUDService staffCrudService;
	@Autowired BusinessUnitService businessUnitService;

	@PostMapping("/create")
	private ResponseEntity<String> createStaffMember(@RequestBody CreateStaffMember request) throws StaffManagerException {
		staffCrudService.createStaffMember(request);
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}
	
	@GetMapping("/{staffId}")
	private StaffMemberResponse getStaffMember(@PathVariable String staffId) throws StaffManagerException {
		return staffCrudService.getStaffMember(staffId);
	}
	
	@GetMapping("/{staffId}/assignedBus")
	private ResponseEntity<AssignedBus> getAssignedBusOfConductor(@PathVariable String staffId) throws StaffManagerException {
		
		String busId = staffBusService.getAssignedBusToStaffMember(staffId);
		AssignedBus  bus = new AssignedBus();
		bus.setBusId(busId);
		return ResponseEntity.status(HttpStatus.OK).body(bus);
	}
	
	@PutMapping("/{staffId}/assignBus")
	private ResponseEntity<String> assignBusToStaffMember(@RequestBody StaffBusAssigment request) throws StaffManagerException {
		String staffId = request.getStaffId();
		String busId = request.getBusId();
		
		staffBusService.assignBusToStaffMember(staffId, busId);
		
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}
	
	@DeleteMapping("/{staffId}")
	private ResponseEntity<String> deleteStaffMember(@PathVariable String staffId) throws StaffManagerException {
		staffCrudService.deleteStaffMember(staffId);
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}
	
	@GetMapping("{businessUnit}/getAll")
	private List<String> getAllStaffMembersForBusinessUnit(@PathVariable String businessUnit){
		return businessUnitService.getAllStaffMemberIdsForBusinessUnit(businessUnit);
	}
	
}

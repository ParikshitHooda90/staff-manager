package com.vaahano.staffmanager.rest;

import java.util.List;
import java.util.Optional;

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
import com.vaahano.staffmanager.bean.StaffLoginRequest;
import com.vaahano.staffmanager.bean.StaffLoginResponse;
import com.vaahano.staffmanager.bean.StaffMemberResponse;
import com.vaahano.staffmanager.exception.StaffManagerException;
import com.vaahano.staffmanager.service.api.BusinessUnitService;
import com.vaahano.staffmanager.service.api.StaffBusService;
import com.vaahano.staffmanager.service.api.StaffMemberService;

@RestController
@RequestMapping("/staff")
public class StaffMemberController {
	
	@Autowired StaffBusService staffBusService;
	@Autowired StaffMemberService staffMemberService;
	@Autowired BusinessUnitService businessUnitService;

	@PostMapping("/create")
	 ResponseEntity<String> createStaffMember(@RequestBody CreateStaffMember request) throws StaffManagerException {
		staffMemberService.createStaffMember(request);
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}
	
	@GetMapping("/{staffId}")
	 StaffMemberResponse getStaffMember(@PathVariable String staffId) throws StaffManagerException {
		return staffMemberService.getStaffMember(staffId);
	}
	
	@GetMapping("/{staffId}/assignedBus")
	 ResponseEntity<AssignedBus> getAssignedBusOfConductor(@PathVariable String staffId) throws StaffManagerException {
		
		Optional<String> busId = staffBusService.getAssignedBusToStaffMember(staffId);
		
		if(busId.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new AssignedBus());
		}else {
			AssignedBus  bus = new AssignedBus();
			bus.setBusId(busId.get());
			return ResponseEntity.status(HttpStatus.OK).body(bus);
		}
		
	}
	
	@PutMapping("/assignBus")
	 ResponseEntity<String> assignBusToStaffMember(@RequestBody StaffBusAssigment request) throws StaffManagerException {
		String staffId = request.getStaffId();
		String busId = request.getBusId();
		
		staffBusService.assignBusToStaffMember(staffId, busId);
		
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}
	
	@DeleteMapping("/{staffId}")
	 ResponseEntity<String> deleteStaffMember(@PathVariable String staffId) throws StaffManagerException {
		staffMemberService.deleteStaffMember(staffId);
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}
	
	@GetMapping("/{businessUnit}/getAll")
	 List<String> getAllStaffMembersForBusinessUnit(@PathVariable String businessUnit){
		return businessUnitService.getAllStaffMemberIdsForBusinessUnit(businessUnit);
	}
	
	@PostMapping("/login")
	StaffLoginResponse login(@RequestBody StaffLoginRequest request) {
		try {
			return staffMemberService.doLogin(request.getStaffId(), request.getPassword());
		} catch (StaffManagerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

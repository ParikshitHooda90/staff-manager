package com.vaahano.staffmanager.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaahano.staffmanager.db.domain.BusAssignment;
import com.vaahano.staffmanager.db.repository.ConductorBusAssignmentRepository;
import com.vaahano.staffmanager.service.api.StaffBusService;

public class StaffBusServiceImpl implements StaffBusService {
	
	@Autowired ConductorBusAssignmentRepository assignmentRepository;

	@Override
	public void assignBusToStaffMember(String businessUnit, String staffId, String busId) {
		// if already assigned, override
		// else insert
		Optional<BusAssignment> opt = assignmentRepository.findById(staffId);
		if(opt.isEmpty()) {
			BusAssignment doc = new BusAssignment();
			doc.setId(makeBusAssignmentId(businessUnit, staffId));
			doc.setStaffId(staffId);
			doc.setBusId(busId);
			doc.setBusinessUnit(businessUnit);
			doc.setAssignedOn(LocalDateTime.now());
			assignmentRepository.insert(doc);
		}else {
			BusAssignment doc = opt.get();
			doc.setBusId(busId);
			doc.setAssignedOn(LocalDateTime.now());
			assignmentRepository.save(doc);
		}
	}

	@Override
	public String getAssignedBusToStaffMember(String businessUnit, String staffId) {
		Optional<BusAssignment> opt = assignmentRepository.findById(makeBusAssignmentId(businessUnit, staffId));
		if(opt.isEmpty()) {
			// no bus assigned to staff member.
		}
		return opt.get().getBusId();
	}
	
	private String makeBusAssignmentId(String businessUnit, String staffId) {
		return businessUnit+":"+staffId;
	}

}

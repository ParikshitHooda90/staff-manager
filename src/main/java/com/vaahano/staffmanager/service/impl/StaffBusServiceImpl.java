package com.vaahano.staffmanager.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaahano.staffmanager.db.domain.StaffMember;
import com.vaahano.staffmanager.db.repository.StaffMembersRepository;
import com.vaahano.staffmanager.exception.StaffManagerExeption;
import com.vaahano.staffmanager.service.api.StaffBusService;

public class StaffBusServiceImpl implements StaffBusService {
	
	@Autowired StaffMembersRepository staffRepository;

	@Override
	public void assignBusToStaffMember( String staffId, String busId) throws StaffManagerExeption {
		// if already assigned, override
		// else insert
		Optional<StaffMember> opt = staffRepository.findById(staffId);
	
		if(opt.isEmpty()) {
			throw new StaffManagerExeption(StaffManagerExeption.ExceptionMessage.NO_STAFF_MEMBER_EXISTS);
		}else {
			StaffMember doc = opt.get();
			doc.setAssignedBus(busId);
			doc.setBusLastAssignedOn(LocalDateTime.now().toString());
			staffRepository.save(doc);
		}
	}

	@Override
	public String getAssignedBusToStaffMember(String staffId) throws StaffManagerExeption {
		Optional<StaffMember> opt = staffRepository.findById(staffId);
		if(opt.isEmpty()) {
			throw new StaffManagerExeption(StaffManagerExeption.ExceptionMessage.NO_STAFF_MEMBER_EXISTS);
		}else {
			StaffMember staff = opt.get();
			if(null != staff.getAssignedBus()) {
				return staff.getAssignedBus();
			}else {
				throw new StaffManagerExeption(StaffManagerExeption.ExceptionMessage.NO_BUS_ASSIGNED_TO_STAFF_MEMBER);
			}
		}
	}
	
	private String makeBusAssignmentId(String businessUnit, String staffId) {
		return businessUnit+":"+staffId;
	}

}

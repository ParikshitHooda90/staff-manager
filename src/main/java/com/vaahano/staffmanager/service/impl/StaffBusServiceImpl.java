package com.vaahano.staffmanager.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaahano.staffmanager.bean.StaffPerson;
import com.vaahano.staffmanager.client.BusManagerClient;
import com.vaahano.staffmanager.db.domain.StaffMember;
import com.vaahano.staffmanager.db.repository.StaffMembersRepository;
import com.vaahano.staffmanager.exception.StaffManagerException;
import com.vaahano.staffmanager.service.api.StaffBusService;

@Service
public class StaffBusServiceImpl implements StaffBusService {
	
	@Autowired StaffMembersRepository staffRepository;
	@Autowired BusManagerClient busManagerClient;

	@Override
	public void assignBusToStaffMember( String staffId, String busId) throws StaffManagerException {
		// if already assigned, override else insert
		Optional<StaffMember> opt = staffRepository.findById(staffId);
		String conductorName = null;
		if(opt.isEmpty()) {
			throw new StaffManagerException(StaffManagerException.ExceptionMessage.NO_STAFF_MEMBER_EXISTS);
		}else {
			StaffMember doc = opt.get();
			conductorName = doc.getName();
			doc.setAssignedBus(busId);
			doc.setBusLastAssignedOn(LocalDateTime.now().toString());
			staffRepository.save(doc);
		}
		
		// tell bus-manager about this.
		StaffPerson staff = new StaffPerson();
		staff.setName(conductorName);
		staff.setStaffId(staffId);
		busManagerClient.updateConductorOfBus(busId, staff);
	}

	@Override
	public String getAssignedBusToStaffMember(String staffId) throws StaffManagerException {
		Optional<StaffMember> opt = staffRepository.findById(staffId);
		if(opt.isEmpty()) {
			throw new StaffManagerException(StaffManagerException.ExceptionMessage.NO_STAFF_MEMBER_EXISTS);
		}else {
			StaffMember staff = opt.get();
			if(null != staff.getAssignedBus()) {
				return staff.getAssignedBus();
			}else {
				throw new StaffManagerException(StaffManagerException.ExceptionMessage.NO_BUS_ASSIGNED_TO_STAFF_MEMBER);
			}
		}
	}

}

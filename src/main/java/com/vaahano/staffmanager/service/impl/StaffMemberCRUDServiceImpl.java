package com.vaahano.staffmanager.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaahano.staffmanager.bean.CreateStaffMember;
import com.vaahano.staffmanager.bean.StaffMemberResponse;
import com.vaahano.staffmanager.db.domain.StaffMember;
import com.vaahano.staffmanager.db.repository.StaffMembersRepository;
import com.vaahano.staffmanager.event.StaffMemberCRUDEventPublisher;
import com.vaahano.staffmanager.exception.StaffManagerException;
import com.vaahano.staffmanager.service.api.StaffMemberCRUDService;

@Service
public class StaffMemberCRUDServiceImpl implements StaffMemberCRUDService {

	@Autowired StaffMembersRepository staffRepository;
	@Autowired StaffMemberCRUDEventPublisher staffMemberEventPublisher;
	
	@Override
	public void createStaffMember(CreateStaffMember member) throws StaffManagerException {
		StaffMember mem = new StaffMember();
		mem.setBusinessUnit(member.getBusinessUnit());
		mem.setId(member.getStaffId());
		mem.setDesignation(member.getDesignation());
		mem.setRole(member.getRole());
		mem.setPhoneNumber(member.getPhoneNumber());
		staffRepository.insert(mem);
		
		staffMemberEventPublisher.publishStaffMemberCreatedEvent(member.getStaffId(), member.getBusinessUnit());
	}

	@Override
	public StaffMemberResponse getStaffMember(String staffId) throws StaffManagerException {
		Optional<StaffMember> opt = staffRepository.findById(staffId);
		StaffMember staff = opt.get();
		StaffMemberResponse response = new StaffMemberResponse();
		response.setBusinessUnit(staff.getBusinessUnit());
		response.setDesignation(staff.getDesignation());
		response.setRole(staff.getRole());
		response.setName(staff.getName());
		response.setStaffId(staff.getId());
		return response;
	}

	@Override
	public void deleteStaffMember(String staffId) throws StaffManagerException {
		Optional<StaffMember> opt = staffRepository.findById(staffId);
		StaffMember staff = opt.get();
		String businessUnit = staff.getBusinessUnit();
		staffRepository.delete(staff);
		
		staffMemberEventPublisher.publishStaffMemberRemovedEvent(staffId, businessUnit);

	}

}

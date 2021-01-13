package com.vaahano.staffmanager.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaahano.staffmanager.bean.CreateStaffMember;
import com.vaahano.staffmanager.bean.StaffMemberResponse;
import com.vaahano.staffmanager.db.domain.StaffMember;
import com.vaahano.staffmanager.db.repository.StaffMembersRepository;
import com.vaahano.staffmanager.exception.StaffManagerExeption;
import com.vaahano.staffmanager.service.api.StaffMemberCRUDService;

@Service
public class StaffMemberCRUDServiceImpl implements StaffMemberCRUDService {

	@Autowired StaffMembersRepository staffRepository;
	
	@Override
	public void createStaffMember(CreateStaffMember member) throws StaffManagerExeption {
		StaffMember mem = new StaffMember();
		mem.setBusinessUnit(member.getBusinessUnit());
		mem.setId(member.getId());
		mem.setDesignation(member.getDesignation());
		mem.setRole(member.getRole());
		mem.setPhoneNumber(member.getPhoneNumber());
		staffRepository.insert(mem);
		
	}

	@Override
	public StaffMemberResponse getStaffMember(String staffId) throws StaffManagerExeption {
		Optional<StaffMember> opt = staffRepository.findById(staffId);
		StaffMember staff = opt.get();
		StaffMemberResponse response = new StaffMemberResponse();
		response.setBusinessUnit(staff.getBusinessUnit());
		response.setId(staff.getId());
		response.setDesignation(staff.getDesignation());
		response.setRole(staff.getRole());
		return response;
	}

}

package com.vaahano.staffmanager.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaahano.staffmanager.bean.CreateStaffMember;
import com.vaahano.staffmanager.bean.StaffMemberLoginResponse;
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
		mem.setId(member.getStaffId());
		mem.setName(member.getName());
		mem.setPassword(member.getPassword());
		mem.setBusinessUnit(member.getBusinessUnit());
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

	private boolean validateStaffMemberCredentials(String staffId, String password) throws StaffManagerException {
		validateStaffId(staffId);
		
		Optional<StaffMember> opt = staffRepository.findById(staffId);
		StaffMember staff = opt.get();
		// exists in DB now match password
		if(password.equals(staff.getPassword())){
			return true;
		}
		return false;
	}
	
	private void validateStaffId(String staffId) throws StaffManagerException{
		if(null == staffId || staffId.isBlank() || staffId.isBlank() || staffId.length() < 5) {
			throw new StaffManagerException(StaffManagerException.ExceptionMessage.ILLFORMATED_STAFF_ID);
		}
	}

	@Override
	public StaffMemberLoginResponse doLogin(String staffId, String password) throws StaffManagerException {
		StaffMemberLoginResponse res = null;
		if(validateStaffMemberCredentials(staffId, password)) {
			res = new StaffMemberLoginResponse();
			res.setValidated(true);
			res.setAuthToken(UUID.randomUUID().toString());
			
		}else {
			res = new StaffMemberLoginResponse();
			res.setValidated(false);
		}
		// log this transaction into DB async, useful for reporting ?
		return res;
	}

}

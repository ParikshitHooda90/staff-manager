package com.vaahano.staffmanager.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.vaahano.staffmanager.FileResourceUtil;
import com.vaahano.staffmanager.bean.CreateStaffMember;
import com.vaahano.staffmanager.bean.StaffLoginResponse;
import com.vaahano.staffmanager.bean.StaffLogoutResponse;
import com.vaahano.staffmanager.bean.StaffMemberResponse;
import com.vaahano.staffmanager.db.domain.StaffMember;
import com.vaahano.staffmanager.db.domain.StaffSessionActive;
import com.vaahano.staffmanager.db.repository.StaffMembersRepository;
import com.vaahano.staffmanager.db.repository.StaffSessionActiveRepository;
import com.vaahano.staffmanager.event.StaffMemberCRUDEventPublisher;
import com.vaahano.staffmanager.exception.StaffManagerException;
import com.vaahano.staffmanager.service.api.StaffMemberService;

@Service
public class StaffMemberServiceImpl implements StaffMemberService, CommandLineRunner {

	@Autowired StaffMembersRepository staffRepository;
	@Autowired StaffMemberCRUDEventPublisher staffMemberEventPublisher;
	@Autowired StaffSessionActiveRepository sessionActiveRepository;
	
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
	public StaffLoginResponse doLogin(String staffId, String password) throws StaffManagerException {
		StaffLoginResponse res = null;
		
		String authToken = UUID.randomUUID().toString();
		if(validateStaffMemberCredentials(staffId, password)) {
			res = new StaffLoginResponse();
			res.setValidated(true);
			res.setAuthToken(authToken);
			
		}else {
			res = new StaffLoginResponse();
			res.setValidated(false);
		}
		// log this transaction into DB async, useful for reporting ?
		StaffSessionActive  doc = new StaffSessionActive();
		doc.setAuthToken(authToken);
		doc.setStaffId(staffId);
		doc.setLoginTime(LocalDateTime.now().toString());
		sessionActiveRepository.insert(doc);
		
		return res;
	}

	@Override
	public StaffLogoutResponse doLogout(String staffId, String authToken) throws StaffManagerException {
		
		// log this transaction into DB async, useful for reporting ?
		StaffLogoutResponse res = new StaffLogoutResponse();
		res.setStaffId(staffId);
		res.setSuccessful(false);
		
		Optional<StaffSessionActive> opt = sessionActiveRepository.findById(authToken);
		
		if(opt.isPresent() && opt.get().getStaffId().equals(staffId) ) {
			// auth token is of same staff member
			res.setSuccessful(true);
		
			sessionActiveRepository.delete(opt.get());
			return res;
			
		}else if(opt.isPresent() && !opt.get().getStaffId().equals(staffId)){
			// auth token is valid but belongs to some other staff user which is provided in request.
			return res;
		} else{
			
			return res;
		}
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		staffRepository.deleteAll();
		
		String content = FileResourceUtil.getContent("staffdb/StaffMembers.json");
		Gson gson = new Gson();
		List<Map<String,String>> list = gson.fromJson(content, List.class);
		list.stream().forEach(e -> {
			StaffMember mem = new StaffMember();
			mem.setId(e.get("staffId"));
			mem.setBusinessUnit(e.get("businessUnit"));
			mem.setName(e.get("name"));
			mem.setDesignation(e.get("designation"));
			mem.setRole(e.get("role"));
			mem.setPassword(e.get("password"));
			mem.setPhoneNumber(e.get("phoneNumber"));
			
			staffRepository.insert(mem);
		});
	}

	
	

}

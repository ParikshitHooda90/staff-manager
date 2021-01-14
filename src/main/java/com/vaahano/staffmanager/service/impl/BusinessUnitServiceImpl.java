package com.vaahano.staffmanager.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.vaahano.staffmanager.db.domain.BusinessUnit;
import com.vaahano.staffmanager.db.repository.BusinessUnitRepository;
import com.vaahano.staffmanager.service.api.BusinessUnitService;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService{
	
	@Autowired BusinessUnitRepository businessUnitRepository;
	
	@Override
	public void createBusinessUnit(String businessUnit, String country, String state) {
		BusinessUnit unit = new BusinessUnit();
		unit.setBusinessUnit(businessUnit);
		unit.setCountry(country);
		unit.setState(state);
		unit.setStaffIds(new ArrayList<String>());
		businessUnitRepository.insert(unit);
		
	}

	@Override
	public void addStaffMemberToBusinessUnit(String businessUnit, String staffId) {
		Optional<BusinessUnit> opt = businessUnitRepository.findById(businessUnit);
		BusinessUnit unit = opt.get();
		unit.getStaffIds().add(staffId);
		businessUnitRepository.save(unit);
	}

	@Override
	public void removeStaffMemberFromBusinessUnit(String businessUnit, String staffId) {
		Optional<BusinessUnit> opt = businessUnitRepository.findById(businessUnit);
		BusinessUnit unit = opt.get();
		unit.getStaffIds().remove(staffId);
		businessUnitRepository.save(unit);
		
	}
	
	@Override
	public List<String> getAllStaffMemberIdsForBusinessUnit(String businessUnit) {
		/* return immutable list of staffIds */
		Optional<BusinessUnit> opt = businessUnitRepository.findById(businessUnit);
		BusinessUnit unit = opt.get();
		return ImmutableList.copyOf(unit.getStaffIds());
	}

}

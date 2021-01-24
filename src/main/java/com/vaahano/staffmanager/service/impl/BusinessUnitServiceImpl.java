package com.vaahano.staffmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.vaahano.staffmanager.FileResourceUtil;
import com.vaahano.staffmanager.db.domain.BusinessUnit;
import com.vaahano.staffmanager.db.repository.BusinessUnitRepository;
import com.vaahano.staffmanager.service.api.BusinessUnitService;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService, CommandLineRunner{
	
	@Autowired BusinessUnitRepository businessUnitRepository;
	
	@Override
	public void createBusinessUnit(String businessUnit, String country, String state) {
		
		BusinessUnit unit = new BusinessUnit();
		unit.setBusinessUnit(businessUnit.toUpperCase());
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

	@Override
	public List<String> getAllBusinessUnits() {
		List<BusinessUnit> businessUnits = businessUnitRepository.findAll();
		return businessUnits.stream()
				.map(bu -> bu.getBusinessUnit())
				.collect(Collectors.toList());
	}

	@Override
	public void run(String... args) throws Exception {
		
		businessUnitRepository.deleteAll();
		
		String fileContent = FileResourceUtil.getContent("staffdb/BusinessUnits.json");
		Gson gson = new Gson();
		
		List<Map<String,String>> list = gson.fromJson(fileContent, List.class);
		
		for( Map<String,String> map : list) {
			BusinessUnit unit = new BusinessUnit();
			unit.setBusinessUnit(map.get("businessUnit"));
			unit.setCountry(map.get("country"));
			unit.setState(map.get("state"));
			businessUnitRepository.insert(unit);
		}
		
	}

}

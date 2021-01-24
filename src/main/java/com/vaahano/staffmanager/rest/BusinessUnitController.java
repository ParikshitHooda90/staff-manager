package com.vaahano.staffmanager.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaahano.staffmanager.bean.BusinessUnitRequest;
import com.vaahano.staffmanager.service.api.BusinessUnitService;

@RestController
@RequestMapping("/bu")
public class BusinessUnitController {
	
	@Autowired BusinessUnitService buService;
	
	@PostMapping("/create")
	void createBusinessUnit(@RequestBody BusinessUnitRequest req) {
		buService.createBusinessUnit(req.getBusinessUnit(), 
				req.getCountry(), 
				req.getState());
	}
	
	@GetMapping("/getAll")
	List<String> getAllBusinessUnits(){
		return buService.getAllBusinessUnits();
	}
}

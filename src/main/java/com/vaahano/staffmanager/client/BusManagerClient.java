package com.vaahano.staffmanager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaahano.staffmanager.bean.StaffPerson;

@RestController
@FeignClient("bus-manager")
public interface BusManagerClient {
	
	@PutMapping("/bus/{busId}/conductor/update")
	void updateConductorOfBus(@PathVariable String busId, @RequestBody StaffPerson staffPerson);
}

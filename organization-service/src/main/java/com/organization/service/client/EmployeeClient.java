package com.organization.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.organization.service.model.EmployeeDto;

@FeignClient(name = "employee-service")
public interface EmployeeClient {
	
	@GetMapping("/organization/{organizationId}")
	List<EmployeeDto> findByOrganization(@PathVariable("organizationId") Long organizationId);
}

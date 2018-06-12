package com.department.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.department.service.model.EmployeeDto;

@FeignClient(name = "employee-service")
public interface EmployeeClient {
	@GetMapping("/department/{departmentId}")
	List<EmployeeDto> findByDepartment(@PathVariable("departmentId") Long departmentId);
}

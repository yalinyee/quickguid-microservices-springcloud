package com.employee.service.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.employee.service.model.EmployeeDto;


public class EmployeeRepository {

	private List<EmployeeDto> employees = new ArrayList<>();
	
	public EmployeeDto add(EmployeeDto employee) {
		employee.setId((long) (employees.size()+1));
		employees.add(employee);
		return employee;
	}
	
	public EmployeeDto findById(Long id) {
		Optional<EmployeeDto> employee = employees.stream().filter(a -> a.getId().equals(id)).findFirst();
		if (employee.isPresent())
			return employee.get();
		else
			return null;
	}
	
	public List<EmployeeDto> findAll() {
		return employees;
	}
	
	public List<EmployeeDto> findByDepartment(Long departmentId) {
		return employees.stream().filter(a -> a.getDepartmentId().equals(departmentId)).collect(Collectors.toList());
	}
	
	public List<EmployeeDto> findByOrganization(Long organizationId) {
		return employees.stream().filter(a -> a.getOrganizationId().equals(organizationId)).collect(Collectors.toList());
	}
	
}

package com.department.service.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.department.service.model.DepartmentDto;


public class DepartmentRepository {

	private List<DepartmentDto> departments = new ArrayList<>();
	
	public DepartmentDto add(DepartmentDto department) {
		department.setId((long) (departments.size()+1));
		departments.add(department);
		return department;
	}
	
	public DepartmentDto findById(Long id) {
		Optional<DepartmentDto> department = departments.stream().filter(a -> a.getId().equals(id)).findFirst();
		if (department.isPresent())
			return department.get();
		else
			return null;
	}
	
	public List<DepartmentDto> findAll() {
		return departments;
	}
	
	public List<DepartmentDto> findByOrganization(Long organizationId) {
		return departments.stream().filter(a -> a.getOrganizationId().equals(organizationId)).collect(Collectors.toList());
	}
	
}

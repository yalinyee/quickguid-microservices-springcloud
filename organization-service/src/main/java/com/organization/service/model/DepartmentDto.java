package com.organization.service.model;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDto {

	private Long id;
	private String name;
	private List<EmployeeDto> employees = new ArrayList<>();

	public DepartmentDto() {
		
	}

	public DepartmentDto(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

}

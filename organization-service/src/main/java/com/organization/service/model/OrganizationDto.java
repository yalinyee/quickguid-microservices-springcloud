package com.organization.service.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationDto {
	
	private Long id;
	private String name;
	private String address;
	private List<DepartmentDto> departments = new ArrayList<>();
	private List<EmployeeDto> employees = new ArrayList<>();

	public OrganizationDto() {

	}
	
	public OrganizationDto(String name, String address) {
		this.name = name;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<DepartmentDto> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentDto> departments) {
		this.departments = departments;
	}

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}

package com.organization.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organization.service.model.OrganizationDto;
import com.organization.service.repository.OrganizationRepository;

@RestController
public class OrganizationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@PostMapping
	public OrganizationDto add(@RequestBody OrganizationDto organization) {
		LOGGER.info("Organization add: {}", organization);
		return organizationRepository.add(organization);
	}
	
	@GetMapping
	public List<OrganizationDto> findAll() {
		LOGGER.info("Organization find");
		return organizationRepository.findAll();
	}

	@GetMapping("/{id}/with-departments")
	public OrganizationDto findByIdWithDepartments(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		OrganizationDto organization = organizationRepository.findById(id);
		return organization;
	}
	
	@GetMapping("/{id}/with-employees")
	public OrganizationDto findByIdWithEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		OrganizationDto organization = organizationRepository.findById(id);
		return organization;
	}
}

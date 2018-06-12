package com.organization.service.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.organization.service.model.OrganizationDto;

public class OrganizationRepository {

	private List<OrganizationDto> OrganizationDtos = new ArrayList<>();
	
	public OrganizationDto add(OrganizationDto OrganizationDto) {
		OrganizationDto.setId((long) (OrganizationDtos.size()+1));
		OrganizationDtos.add(OrganizationDto);
		return OrganizationDto;
	}
	
	public OrganizationDto findById(Long id) {
		Optional<OrganizationDto> OrganizationDto = OrganizationDtos.stream().filter(a -> a.getId().equals(id)).findFirst();
		if (OrganizationDto.isPresent())
			return OrganizationDto.get();
		else
			return null;
	}
	
	public List<OrganizationDto> findAll() {
		return OrganizationDtos;
	}
	
}

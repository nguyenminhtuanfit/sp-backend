package com.sp.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sp.app.model.Appliance;
import com.sp.app.repository.rsql.ApplianceRepository;

@Service
public class ApplianceService {

	@Autowired
	private ApplianceRepository applianceRepository;
	
	public Appliance save(Appliance appliance) {
		return applianceRepository.save(appliance);
	}
	
	public List<Appliance> search(Specification<Appliance> appliance) {
		return applianceRepository.findAll(appliance);
	}
	
	public Page<Appliance> findAll(Pageable pageable) {
		return applianceRepository.findAll(pageable);
	}
	
	public void deleteById(Long id) {
		applianceRepository.deleteById(id);
	}
	
	public void delete(Set<String> setStatus) {
		List<Appliance> appliances = applianceRepository.findByStatusIn(setStatus);
		for (Appliance appliance : appliances) {
			applianceRepository.deleteById(appliance.getId());
		}
	}
	
	public Boolean isDuplicatedAppliance(String serialNumber, String brand, String model) {
		return applianceRepository.findBySerialNumberAndBrandAndModel(serialNumber, brand, model).size() > 0;
	}
}

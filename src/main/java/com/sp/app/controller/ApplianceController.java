package com.sp.app.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sp.app.exception.DuplicatedApplianceException;
import com.sp.app.model.Appliance;
import com.sp.app.service.ApplianceService;
import com.sp.app.util.rsql.CustomRsqlVisitor;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;


@RestController
public class ApplianceController {

	@Autowired
	private ApplianceService applianceService;
	
	@PostMapping("/appliances")
	public Appliance create(@RequestBody Appliance appliance) {
		if(applianceService.isDuplicatedAppliance(appliance.getSerialNumber(), appliance.getBrand(), appliance.getModel())) {
			throw new DuplicatedApplianceException(String.format("Appliance with serial number = %s,"
					+ " branch = %s, model = %s is duplicated", appliance.getSerialNumber(), appliance.getBrand(), appliance.getModel())); 
		}
		return applianceService.save(appliance);
	}
	
	@PutMapping("/appliances/{id}")
	public Appliance update(@RequestBody Appliance appliance, @PathVariable("id") Long id) {
		appliance.setId(id);
		return applianceService.save(appliance);
	}
	
	@DeleteMapping("/appliances")
	public void deleteByStatus(@RequestParam String status) {
		applianceService.delete(new HashSet<String>( Arrays.asList(status.split(","))));
	}
	
	@DeleteMapping("/appliances/{id}")
	public void delete(@PathVariable("id") Long id){
		applianceService.deleteById(id);
	}
	
	@GetMapping("/appliances")
	public Page<Appliance> search(@RequestParam(value = "q", required = false) String search, @PageableDefault(page=0, size = 10) Pageable pageable) {
		if(StringUtils.isEmpty(search)) {
			return applianceService.findAll(pageable);
		}else {
			Node rootNode = new RSQLParser().parse(search);
			Specification<Appliance> applianceSpec = rootNode.accept(new CustomRsqlVisitor<Appliance>());
			List<Appliance> appliances =  applianceService.search(applianceSpec);
			return new PageImpl<Appliance>(appliances, pageable, appliances.size());
		}
	}
}


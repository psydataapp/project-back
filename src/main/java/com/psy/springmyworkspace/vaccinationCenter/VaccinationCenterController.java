package com.psy.springmyworkspace.vaccinationCenter;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccinationCenterController {
	private VaccinationCenterRepository repo;
	
	public VaccinationCenterController(VaccinationCenterRepository repo) {
		this.repo = repo;
	}
	@Cacheable(cacheNames="vaccination-center", key="2")
	@RequestMapping(value="/opendata/vaccinationcenter", method = RequestMethod.GET)
	public List<VaccinationCenter> getListOrderByDataType() {
		Order[] orders = { new Order(Sort.Direction.ASC, "id") };
	
		return repo.findAll(PageRequest.of(0, 284, Sort.by(orders))).toList();
	}
}

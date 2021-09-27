package com.psy.springmyworkspace.covid19;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
@RestController
public class Covid19Controller {

	private Covid19Repository repo;
	
	public Covid19Controller(Covid19Repository repo) {
		this.repo = repo;
	}
	
	@Cacheable(cacheNames="covid19", key="0")
	@RequestMapping(value="/opendata/covid19", method = RequestMethod.GET)
	public List<Covid19> getListOrderByDataType() {
		Order[] orders = { new Order(Sort.Direction.DESC, "seq"), new Order(Sort.Direction.ASC, "createDt") };

		return repo.findAll(PageRequest.of(0, 1, Sort.by(orders))).toList();
	}
}

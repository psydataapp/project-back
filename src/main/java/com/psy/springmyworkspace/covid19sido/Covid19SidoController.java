package com.psy.springmyworkspace.covid19sido;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
@RestController
public class Covid19SidoController {

	private Covid19SidoRepository repo;
	
	public Covid19SidoController(Covid19SidoRepository repo) {
		this.repo = repo;
	}
	@Cacheable(cacheNames="covid19-sido", key="1")
	@RequestMapping(value="/opendata/covid19sido", method = RequestMethod.GET)
	public List<Covid19Sido> getListOrderByDataType() {
		Order[] orders = { new Order(Sort.Direction.DESC, "seq"), new Order(Sort.Direction.ASC, "createDt") };

		return repo.findAll(PageRequest.of(0, 133, Sort.by(orders))).toList();
	}
}

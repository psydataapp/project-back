package com.psy.springmyworkspace.community;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityController {
	private CommunityRepository repo;
	
	@Autowired
	public CommunityController (CommunityRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping(value="/community")
	public List<Community> getCommunityList() {
		return repo.findAll(Sort.by("id").descending());
	}
	
	@GetMapping(value="/community/paging")
	public Page<Community> getCommunityListPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}
	
	@PostMapping(value ="/community")
	public Community addCommunity(@RequestBody Community community, HttpServletResponse res) {
		if(community.getTitle() == null || community.getTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(community.getContent() == null || community.getContent().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		community.setCreatedTime(new Date().getTime() );
		return repo.save(community);
	}
	
	@GetMapping(value = "/community/{id}")
	public Community getCommunity(@PathVariable int id, HttpServletResponse res) {

		Optional<Community> community = repo.findById(id);
		if (community.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		return community.get();
	}
	
	@DeleteMapping(value = "/community/{id}")
	public boolean removeCommunity(@PathVariable int id, HttpServletResponse res) {

		Optional<Community> community = repo.findById(id);
		if (community.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		repo.deleteById(id);
		return true;
	}
	
	@PutMapping(value = "/community/{id}")
	public Community modifyCommunity(@PathVariable int id, @RequestBody Community community, HttpServletResponse res) {

		Optional<Community> findedCommunity = repo.findById(id);
		if (findedCommunity.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if (community.getTitle() == null && community.getTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		if (community.getContent() == null && community.getContent().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		
		Community toUpdateCommunity = findedCommunity.get();
		toUpdateCommunity.setTitle(community.getTitle());
		toUpdateCommunity.setContent(community.getContent());
		toUpdateCommunity.setTime(community.getTime());
		toUpdateCommunity.setImg(community.getImg());
		
		return repo.save(toUpdateCommunity);
	}
	

}

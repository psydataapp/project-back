package com.psy.springmyworkspace.vaccinationCenter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class VaccinationCenterService {
	VaccinationCenterRepository repo;
	
	@Autowired
	public VaccinationCenterService(VaccinationCenterRepository repo) {
		this.repo = repo;
	}
	
	@CacheEvict(cacheNames="vaccination-center", key="2")
	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void requestVaccinationCenter() throws IOException {
		getVaccinationCenter("centers");
	}
	
	public void getVaccinationCenter(String center) throws IOException {
		String serviceKey = "4xEmD9%2B%2BLp9Fu4gyx39cMubj7O3NgdEvWopZWRJdwXI%2F57PnaYnq5tbbrEnnBcdy9ICFSppjOIKfrDdlbmYXcQ%3D%3D";

		StringBuilder builder = new StringBuilder();
		builder.append("https://api.odcloud.kr/api/15077586/v1");
		builder.append("/" + center);
		builder.append("?page=1");
		builder.append("&perPage=284");
		builder.append("&serviceKey=" + serviceKey);
		
		System.out.println(builder.toString());
		
		
		URL url = new URL(builder.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		byte[] result = con.getInputStream().readAllBytes();
		
		String jsonData = new String(result, "UTF-8");  
//		System.out.println(data);
		
		VaccinationCenterResponse response = new Gson().fromJson(jsonData, VaccinationCenterResponse.class);
//		System.out.println(response);
		
		for(VaccinationCenterResponse.Data data : response.getData()) {
			repo.save(new VaccinationCenter(data));
		}
		
	}
}

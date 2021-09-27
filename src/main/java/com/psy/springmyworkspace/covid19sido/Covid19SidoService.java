package com.psy.springmyworkspace.covid19sido;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class Covid19SidoService {
	Covid19SidoRepository repo;
	
	@Autowired
	public Covid19SidoService(Covid19SidoRepository repo) {
		this.repo = repo;
	}
	
	@CacheEvict(cacheNames="covid19-sido", key="1")
	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void requestCovid19SidoData() throws IOException {
//		System.out.println("------");
		getCovid19SidoData("getCovid19SidoInfStateJson");

	}
	
	public void getCovid19SidoData(String itemcode) throws IOException {
		String serviceKey = "4xEmD9%2B%2BLp9Fu4gyx39cMubj7O3NgdEvWopZWRJdwXI%2F57PnaYnq5tbbrEnnBcdy9ICFSppjOIKfrDdlbmYXcQ%3D%3D";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String today = format.format(date);
		int startDt = Integer.parseInt(today) - 6;
//		System.out.println(today);
//		System.out.println(startDt);

		
		StringBuilder builder = new StringBuilder();	
		builder.append("http://openapi.data.go.kr/openapi/service/rest/Covid19");
		builder.append("/" + itemcode);
		builder.append("?serviceKey=" + serviceKey);
		builder.append("&pageNo=1");
		builder.append("&numOfRows=133");
		builder.append("&startCreateDt=" + startDt);
		builder.append("&endCreateDt=" + today);
		
		System.out.println(builder.toString());	
		
		URL url = new URL(builder.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		byte[] result = con.getInputStream().readAllBytes();

		String data = new String(result, "UTF-8");
//		System.out.println(data);
		
		JSONObject jObject = XML.toJSONObject(data);
//		System.out.println(jObject);
		
		Covid19SidoResponse response = new Gson().fromJson(jObject.toString(), Covid19SidoResponse.class);
//		System.out.println(response);
		
		for(Covid19SidoResponse.Item item : response.getResponse().getBody().getItems().getItem()) {
			repo.save(new Covid19Sido(item));
		}
//		}
	}
}

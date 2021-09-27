package com.psy.springmyworkspace.covid19;

import java.util.List;

import lombok.Data;

@Data
public class Covid19Response {
	private Response response;
	
	@Data
	public class Response {
		private Body body; 
	}
	
	@Data
	public class Body {
		private Items items;
	}
	
	@Data
	public class Items {
		private List<Item> item;
	}
	
	@Data
	public class Item {
		private String seq;
		private String createDt;
		
		private String decideCnt;
		private String clearCnt;
		private String deathCnt;
		private String examCnt;
	}
}

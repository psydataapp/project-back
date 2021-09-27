package com.psy.springmyworkspace.vaccinationCenter;

import java.util.List;

@lombok.Data
public class VaccinationCenterResponse {
	private List<Data> data;
	
	@lombok.Data
	public class Data {
		private String address;
		private String centerName;
		private String lat;
		private String lng;
		private String phoneNumber;
		private int id;
	}
	
}


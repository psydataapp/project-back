package com.psy.springmyworkspace.vaccinationCenter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;


@lombok.Data
@Entity
@NoArgsConstructor
public class VaccinationCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String address;
	private String centerName;
	private String lat;
	private String lng;
	private String phoneNumber;
	
	public VaccinationCenter(VaccinationCenterResponse.Data data) {
		this.id = data.getId();
		this.address = data.getAddress();
		this.centerName = data.getCenterName();
		this.lat = data.getLat();
		this.lng = data.getLng();
		this.phoneNumber = data.getPhoneNumber();
	}
}

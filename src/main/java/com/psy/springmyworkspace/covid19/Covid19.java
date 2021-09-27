package com.psy.springmyworkspace.covid19;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@IdClass(Covid19Id.class)
public class Covid19 {
	
	@Id
	private String seq;
	@Id
	private String createDt;
	
	private String decideCnt;
	private String clearCnt;
	private String deathCnt;
	private String examCnt;
	
	public Covid19(Covid19Response.Item item) {
		this.seq = item.getSeq();
		this.createDt = item.getCreateDt();
		this.decideCnt = item.getDecideCnt();
		this.clearCnt = item.getClearCnt();
		this.deathCnt = item.getDeathCnt();
		this.examCnt = item.getExamCnt();
	}
	 
}

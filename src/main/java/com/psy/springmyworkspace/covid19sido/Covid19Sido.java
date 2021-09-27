package com.psy.springmyworkspace.covid19sido;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@IdClass(Covid19SidoId.class)
public class Covid19Sido {
	
	@Id
	private String seq;
	@Id
	private String createDt;
	
	private String incDec;
	private String gubun;
	private String defCnt;
	
	
	public Covid19Sido(Covid19SidoResponse.Item item) {
		this.seq = item.getSeq();
		this.createDt = item.getCreateDt();
		this.incDec = item.getIncDec();
		this.gubun = item.getGubun();
		this.defCnt = item.getDefCnt();
		
			}
	 
}

package com.project.care.dto;

import lombok.Data;

@Data
public class ResHosDTO {

	private String rhseq;	 //예약번호
	private String resdate;	 //예약일자
	private String visitseq; //방문여부 번호
	private String uniqueness; //특이사항
	private String hpseq;	//병원번호
	private String uaseq;	//회원반려동물번호
	private String pseq;	//방문목적번호
	private String picture;
	private String loginId;
}

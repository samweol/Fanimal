package com.project.care.dto;

import lombok.Data;

@Data
public class CompanyDTO {

	//병원 기본정보
	private String id;
	private String name;
	private String tel;
	private String address;
	private Double xcoor;
	private Double ycoor;
	private String business;
	private String password;
	private String email;
	
	//병원 상세정보
	private String hosname;
	private String license;
	private String info;
	private String starttime;
	private String endtime;
	private String stat;
	
	private String cseq;
}

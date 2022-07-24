package com.project.care.dto;

import lombok.Data;

@Data
public class AniResDTO {

	private String rhseq;
	private String name; //이름
	private String hpname;
	private String resdate; //날짜
	private String uniqueness; //증상
	
	private String purpose; //목적
	private String hosname; //병원명
	
	private String pic;
	
	private String cnt;
}

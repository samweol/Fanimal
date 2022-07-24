package com.project.care.dto;

import lombok.Data;

@Data
public class HosReviewDTO {
	
	private String hrseq;
	private String id;
	private String nickname;
	private String review;
	private String star;
	private String redate;
	private String hpseq;
	
	private String cnt; //데이터를 불러올 개수 
	
}

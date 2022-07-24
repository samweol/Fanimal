package com.project.care.dto;

import lombok.Data;

@Data
public class AnimalDTO {

	
	//번호(aseq), 이름(name), 성별(gender), 나이(age), 품종번호(kseq), 생일(birth)
	
	private int aseq;
	private String name;
	private String gender;
	private String age;
	private String kseq;
	private String birth;
	
	
}

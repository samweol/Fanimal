package com.project.care.dto;

import lombok.Data;

@Data
public class AniInfoDTO {
	//번호(aiseq), 동물번호(aseq), 사진(pic), 중성화여부(neutral), 사망여부(state), 몸무게(weight)
	
	private int aiseq;
	private String aseq;
	private String pic;
	private String neutral;
	private String state;
	private String weight;
}

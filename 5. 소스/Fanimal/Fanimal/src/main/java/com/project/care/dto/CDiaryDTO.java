package com.project.care.dto;

import java.sql.Date;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CDiaryDTO {
	
	private String cdSeq;
	private String title;
	private String content;
	private String datetune; 
	private String id;
	private String pSeq;
	private String aSeq;
	private String picture;
	
	// 동물 정보
	private String animalName;
	private String animalPic;
	private String age; 
	
	// 의료정보
	private String pname; // 제품명
	private String component; // 성분명
	private String pDate; //투약일수
	private String usage; //용법
	private String amount; //용량
	
	private String purpose; //진단or수술or접종 
	private String explain;
	
	private String surgery;
	private String vaccination;
	
	private String resdate;//예약날짜==처방전발급날짜
	
}

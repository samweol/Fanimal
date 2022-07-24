package com.project.care.dto;

import lombok.Data;

@Data
public class WDiaryDTO {


//산책 일기 테이블
private String wseq;
private String datetime;
private String place;
private String pic;
private String title;
private String content;
private String id;


//반려동물 관련 변수
private String seq; //산책일기 반려동물 번호
private String useq; 
private String aseq; 


//반려동물 테이블
private String name; //반려동물 이름
private String gender; //반려동물 성별
private String age; //반려동물 나이
private String kseq; //품종번호
private String birth; //생일
private String dogpic; //반려동물 사진
private String dogweight; //반려동물 몸무게


//품종
private String tseq ;//타입번호
private String kind; //품종

	
}

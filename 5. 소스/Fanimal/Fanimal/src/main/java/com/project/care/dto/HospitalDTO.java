package com.project.care.dto;

import lombok.Data;

/**
 * 병원세부정보 테이블 및 기업 테이블의 일부 정보를 담는 DTO입니다.
 * @author 고민지
 */
@Data
public class HospitalDTO {
	
	//병원세부정보
	private String hpseq; 		//병원세부정보
	private String hosname;		//기업이름 
	private String license;		//인허가번호
	private String info;		//소개
	private String starttime;	//운영시작시간
	private String endtime;		//운영종료시간
	private String statseq;		//승인상태번호
	private String cseq;		//기업번호

	//기업
	private String tel;			//전화번호
	private String address;		//주소
	private String xcoor;		//x좌표
	private String ycoor;		//y좌표
	
	//그외
	private String avgStar;	//평균별점
	
}

package com.project.care.dto;

import lombok.Data;

/**
 * 병원 문의게시판
 * @author 고민지
 *
 */
@Data
public class QuesDTO {
	
	//병원 문의게시판
	private String hqseq; 
	private String cseq;		//기업번호
	private String title;  		//제목
	private String content;  	//내용 
	private String id;			//작성자id
	private String postdate;	//작성일
	private String secret;		//비밀글
	private String attachFile;  //첨부파일
	
	
	//그 외
	private String nickname;		//작성자
	private String answer;		//답변여부
	private String type; 	//0 공지, 1 문의
}

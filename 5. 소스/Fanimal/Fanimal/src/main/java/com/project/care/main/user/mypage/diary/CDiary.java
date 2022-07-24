package com.project.care.main.user.mypage.diary;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CDiary {	
	
	//변수타입 전부 string으로 바꿈
	private String cdSeq;
	private String title;
	private String content;
	private String datetune; 
	private String id;
	private String pSeq;
	private String aSeq;
	private String picture;
	
}

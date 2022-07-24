package com.project.care.dto;

import lombok.Data;

@Data
public class CalendarDTO {
	
	//일정
	private String sseq;
	private String memo;
	private String datetime;
	private String end;
	private String id;
	private String stseq;
	private boolean allday;
			
	//병원 일정
	private String hname;
	private String resdate;
	
}

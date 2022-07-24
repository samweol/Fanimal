package com.project.care.dto;

import lombok.Data;

/**
 * 병원 문의게시판 탐색시 필요한 정보
 * @author 고민지
 *
 */
@Data
public class QuesHospitalDTO {

	private String hpseq; 		//병원세부정보
	private String hosname;		//병원이름
	private String cseq;		//기업번호
	private String id;			//기업아이디
	
	private String qBegin;		//시작번호
	private String qEnd;		//끝번호
	private String qSearchKey;
	private String qSearchValue;
	private String qPage;
	private int qBeginPage;
	private int qEndPage;
}

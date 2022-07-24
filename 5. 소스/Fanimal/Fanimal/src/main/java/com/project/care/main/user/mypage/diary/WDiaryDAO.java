package com.project.care.main.user.mypage.diary;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.dto.CDiaryDTO;
import com.project.care.dto.WDiaryDTO;


public class WDiaryDAO {

	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public WDiaryDAO() {
		conn = DBUtil.open();
	}
	
	
	
	
	//산책읽기 등록
	public int insertWDiary(WDiaryDTO wd, String aseq) {
		int result =0;
		
		System.out.println("insertWDiary aseq :" + aseq);
		
		try {
			String sql = "insert into  tblwalkdiary "
					+ "(wseq, datetime, place, pic, title, content, id)"
					+ " values (seqWdiary.nextVal, ?, ?, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, wd.getDatetime());
			pstat.setString(2, wd.getPlace());
			pstat.setString(3, wd.getPic());
			pstat.setString(4, wd.getTitle());
			pstat.setString(5, wd.getContent());
			pstat.setString(6, wd.getId());
	
			
			result = pstat.executeUpdate();	
			
			System.out.println("insertWDiary res : " +result);
			
			if(result > 0) {//성공적으로insert 되었을때 매핑 테이블에도 값 넣어줘야함****주의
				insertMappingWDiary(aseq);	
			}
			
			
			return 1;		
			
		} catch (Exception e) {
			System.out.println("insertWdiary");
			e.printStackTrace();
		}
		
		return -1;
	}
	
	
	//산책일기 매핑 테이블 인서트
	public int insertMappingWDiary(String aseq) {
		int result =0;
		try {
			String sql = "insert into tblwalkani values (SEQWKAN.nextval, seqwdiary.currval, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, aseq);
	
			//System.out.println(result);
			return result =pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("insertMappingWDiary");
			e.printStackTrace();
		}
		
		return -1;
	}


	
	
	
	
	
	

	
	//글번호에 해당하는 산책일기 가져오기
	public WDiaryDTO viewCDairy(String wseq) {
	try {
			
			CDiaryDTO cdDto = new CDiaryDTO();
			
			String sql = "select*from tblwalkdiary w inner join  tblwalkani ani on ani.wseq = w.wseq join tblanimal a on a.aseq = ani.aseq where  w.wseq = ?"; 
			
	
			pstat = conn.prepareStatement(sql);
		
			pstat.setString(1, wseq);
			
			rs = pstat.executeQuery();

			WDiaryDTO cdto = new WDiaryDTO();
			
			if (rs.next()) {
		
				//산책일기 테이블 정보
				cdto.setWseq(rs.getString("wseq"));
				cdto.setDatetime(rs.getString("datetime"));
				cdto.setPlace(rs.getString("place"));
				cdto.setPic(rs.getString("pic"));
				cdto.setTitle(rs.getString("title"));
				cdto.setContent(rs.getString("content"));
				cdto.setId(rs.getString("id"));
				
				//반려동물 정보
				cdto.setName(rs.getString("name"));
				
				
			}

			return cdto;
			
		} catch (Exception e) {
			System.out.println("cdto에 안들어감");
			return null;
		}
	}

	
	public int delinfo(String wseq) {
		try {
			String sql = "delete from tblwalkani where wseq=?";
		
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, wseq);

			
			return pstat.executeUpdate();		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	


	public int del(String wseq) {
		try {
			String sql = "delete from tblwalkdiary where wseq=?";
		
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, wseq);

			
			return pstat.executeUpdate();		
			
			
		} catch (Exception e) {
			System.out.println("산책일기 딜리트 안됨");
			e.printStackTrace();
		}
		
		return -1;
	}
	
}

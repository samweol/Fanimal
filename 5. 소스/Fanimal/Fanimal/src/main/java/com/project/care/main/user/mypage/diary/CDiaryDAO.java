package com.project.care.main.user.mypage.diary;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.dto.CDiaryDTO;
import com.project.care.dto.WDiaryDTO;



public class CDiaryDAO {

	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public CDiaryDAO() {
		conn = DBUtil.open();
	}
	
	// 케어일기 상세 조회 
	public CDiaryDTO viewCDairy(String cdSeqString) {
		
		try {
			
			CDiaryDTO cdDto = new CDiaryDTO();
			
			String sql = "select * from vwCdiary where cdseq = ?"; 
			
			int cdSeq = Integer.parseInt(cdSeqString);
			
			pstat = conn.prepareStatement(sql);
		
			pstat.setInt(1, cdSeq);
			
			rs = pstat.executeQuery();

			CDiaryDTO cdto = new CDiaryDTO();
			
			if (rs.next()) {
		
				cdto.setCdSeq(rs.getString(1));
				cdto.setTitle(rs.getString(2));
				cdto.setContent(rs.getString(3));
				cdto.setDatetune(rs.getString(4));
				cdto.setId(rs.getString("id"));
				cdto.setPSeq(rs.getString("pseq"));
				cdto.setASeq(rs.getString("aseq"));
				cdto.setPicture(rs.getString("picture"));
				
				//동물 정보
				cdto.setAnimalName(rs.getString(9));
				cdto.setAnimalPic(rs.getString("pic"));
				cdto.setAge(rs.getString("age"));
				
				// 의료 정보 
				cdto.setPname(rs.getString("pname"));
				cdto.setComponent(rs.getString("component"));
				cdto.setPDate(rs.getString("pdate"));
				cdto.setUsage(rs.getString("usage"));
				cdto.setAmount(rs.getString("amount"));
				cdto.setPurpose(rs.getString("purpose"));
				cdto.setExplain(rs.getString("explain"));
				cdto.setSurgery(rs.getString("surgery"));
				cdto.setVaccination(rs.getString("vaccination"));
				
			}

			return cdto;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	// 케어일기 등록
	public int insertCDiary(CDiaryDTO cd) {

		try {
			String sql = "insert into tblcdiary "
					+ "(cdseq, title, content, datetune, id, pseq, aseq, picture)"
					+ " values (seqCdiary.nextVal, ?, ?, ?, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cd.getTitle());
			pstat.setString(2, cd.getContent());
			pstat.setString(3, cd.getDatetune());
			pstat.setString(4, cd.getId());
			pstat.setString(5, cd.getPSeq());
			pstat.setString(6, cd.getASeq());
			pstat.setString(7, cd.getPicture());
				
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("케어일기인서트안됨");
			e.printStackTrace();
		}
		
		return -1;
	}

	
	
	public ArrayList<CDiaryDTO> clist(String uaseq) {
		
		try {
			
			String sql ="select  c.cdseq, c.picture ,c.datetune from tblcdiary c inner join tblanimal a on a.aseq = c.aseq inner join tbluserani ua on ua.aseq = a.aseq where uaseq=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, uaseq);
			
			
			rs = pstat.executeQuery();
			

			
			ArrayList<CDiaryDTO> clist = new ArrayList<CDiaryDTO>();

			
			
			while (rs.next()) {
				CDiaryDTO dto = new CDiaryDTO(); //순서주의***
				
				dto.setCdSeq(rs.getString(1));
				dto.setPicture(rs.getString(2));
				dto.setDatetune(rs.getString(3));

				clist.add(dto);

				}
			
				return clist;	
			
		}catch(Exception e) {
			System.out.println("clist에서 출력안됨");
			e.printStackTrace();
			return null;
		
			}
	}

	
	
	
	public ArrayList<WDiaryDTO> wlist(String uaseq) {
		try {
			
			String sql ="select  w.wseq, w.pic from tblwalkdiary w inner join tblwalkani wa on wa.wseq = w.wseq inner join tblanimal a on a.aseq = wa.aseq inner join tbluserani ua on a.aseq = ua.aseq where uaseq = ?";
			
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, uaseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<WDiaryDTO> wlist = new ArrayList<WDiaryDTO>();

		
			while (rs.next()) {
				
				WDiaryDTO dto = new WDiaryDTO(); //순서주의***
				
				dto.setWseq(rs.getString(1));
				dto.setPic(rs.getString(2));

				wlist.add(dto);

			}
			
			System.out.println(wlist);
			return wlist;	
			
			
		}catch(Exception e) {
			System.out.println("wlist에서 출력안됨");
			
			e.printStackTrace();
			return null;
		}
	}
	
	
	//프로필 띄우기
	public ArrayList<WDiaryDTO> dlist(String uaseq) {
		try {
			
			String sql ="select name,gender,age,pic,weight, a.aseq from tblanimal a inner join tbluserani ua on ua.aseq = a.aseq inner join tblaniinfo ai on ai.aseq = a.aseq where uaseq=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, uaseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<WDiaryDTO> dlist = new ArrayList<WDiaryDTO>();
			
			if(rs.next()) {
				
				WDiaryDTO dto = new WDiaryDTO(); //순서주의***
				
				dto.setName(rs.getString(1));
				dto.setGender(rs.getString(2));	
				dto.setAge(rs.getString(3));
				dto.setDogpic(rs.getString(4));
				dto.setDogweight(rs.getString(5));
				dto.setUseq(rs.getString(6));
				
				
				
				dlist.add(dto);
				System.out.println(dlist);

			}
			
			return dlist;	
			
			
		}catch(Exception e) {
			System.out.println("dlist에서 출력안댐");
			
			e.printStackTrace();
			return null;
		}
	}
	
	
	//uaseq에 해당하는 사용자가 -> 얼만큼의 진료확인서를 가지고 있는지 확인하는 쿼리
	public int plist(String uaseq) {
		
		try {
		String sql ="select count(*) from tbluserani u inner join tblreshos r on r.uaseq = u.aseq inner join tblpresc p on p.rhseq = r.rhseq where u.uaseq=?";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, uaseq);
		
		rs = pstat.executeQuery(); //반환값 1개존재
		
		int count=0;
		
		if(rs.next()) {
			
			count=Integer.parseInt(rs.getString(1));
		
		}
		
		System.out.println(count);
		return count;	
		
		
		}catch(Exception e) {
		System.out.println("plist에서 출력안댐");
		
		e.printStackTrace();
		return -1;
	}
	
}
	//진료확인서 pseq,aseq가져오기..
	public ArrayList<CDiaryDTO> pslist(String uaseq) {
		
		try {
		String sql ="select p.pseq, u.aseq ,resdate from tbluserani u inner join tblreshos r on r.uaseq = u.aseq inner join tblpresc p on p.rhseq = r.rhseq where u.uaseq=?";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, uaseq);
		
		
		rs = pstat.executeQuery();
		

		
		ArrayList<CDiaryDTO> pslist = new ArrayList<CDiaryDTO>();
		
	
		while(rs.next()) {
			
			CDiaryDTO dto = new CDiaryDTO(); //순서주의***
			
			dto.setPSeq(rs.getString(1));
			dto.setASeq(rs.getString(2));
			dto.setPDate(rs.getString(3).substring(0,11));
			

			pslist.add(dto);
			
		
		}
		
		System.out.println(pslist);
		return pslist;	
		
		
		}catch(Exception e) {
		System.out.println("plist에서 출력안댐");
		
		e.printStackTrace();
		return null;
	}
	}
	
	
	//디비에서 선택한 일기 삭제하기 
	public int del(String cdSeq) {
	
		try {
			String sql = "delete from tblcdiary where cdseq=?";
		
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cdSeq);

			
			return pstat.executeUpdate();		
			
			
		} catch (Exception e) {
			System.out.println("케어일기 딜리트 안됨");
			e.printStackTrace();
		}
		
		return -1;
	}
}
package com.project.care.main.company.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.dto.DosageDTO;
import com.project.care.dto.PidDTO;
import com.project.care.dto.componentDTO;
import com.project.care.dto.prescriptionDTO;
import com.project.care.dto.proIngDTO;
import com.project.care.dto.productDTO;

public class prescriptionDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public prescriptionDAO() {

		conn = DBUtil.open();
	}

	// 성분명 리스트
	public ArrayList<componentDTO> comtype() {

		try {

			conn = DBUtil.open();

			String sql = "select * from tblIngred";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<componentDTO> list = new ArrayList<componentDTO>();

			while (rs.next()) {

				componentDTO tdto = new componentDTO();

				tdto.setIseq(rs.getString("iseq"));
				tdto.setComponent(rs.getString("component"));

				list.add(tdto);
			}
			

			rs.close();
			stat.close();
			conn.close();

			return list;

		} catch (Exception e) {
			System.out.println("prescriptionDAO.protype");
			e.printStackTrace();
		}

		return null;
	}

	// 제품명 -> ajax
	
	  public ArrayList<productDTO> protype(String iseq) {
	 
	  try {
	  
	  conn = DBUtil.open();
	 
	  String sql = "select p.pseq, p.pname from tblProduct p inner join tblproing pi on p.pseq = pi.pseq where pi.iseq = ?";
	  
	  pstat = conn.prepareStatement(sql);
	  pstat.setString(1, iseq);
	  
	  rs = pstat.executeQuery();
	  
	  ArrayList<productDTO> list = new ArrayList<productDTO>();
	  
	  
	  while (rs.next()) {
	  
	  productDTO tdto = new productDTO();
	  
	  tdto.setPseq(rs.getString("pseq"));
	  tdto.setPname(rs.getString("pname"));
	  
	  System.out.println(rs.getString("pseq"));
	  System.out.println(rs.getString("pname"));
	  list.add(tdto); 
	  
	  }
	  
	  rs.close(); pstat.close(); conn.close();
	  
	  return list;
	  
	  } catch (Exception e) 
	  { 
		  System.out.println("prescriptionDAO.protype");
	  e.printStackTrace(); 
	  }
	  
	  return null; 
	  }


	// 처방전
	public int prescription(prescriptionDTO pdto, String rhseq, String dseq) {

		int eresult;

		try {
			conn = DBUtil.open();

			String sql = "insert into tblPresc values ((select max(pseq) from tblPresc) + 1, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, rhseq);
			pstat.setString(2, pdto.getAvail());
			pstat.setString(3, pdto.getExplain());
			pstat.setString(4, dseq);

			eresult = pstat.executeUpdate();

			pstat.close();
			conn.close();

			return eresult;
		} catch (Exception e) {
			System.out.println("prescriptionDAO.prescription");
			e.printStackTrace();
		}

		return 0;
	}

	// 방금 추가한 번호 알기
	public String findpseq() {

		try {

			conn = DBUtil.open();

			String sql = "select max(pseq) as ps from tblPresc";

			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);

			String pseq = null;

			if (rs.next()) {
				pseq = rs.getString("ps");
			}

			return pseq;

		} catch (Exception e) {
			System.out.println("prescriptionDAO.findpseq");
			e.printStackTrace();
		}
		return null;
	}

	// tblPID
	public int addPrescritption(PidDTO piddto) {

		int pidresult;

		try {

			conn = DBUtil.open();

			String sql = "insert into tblPID values ((select max(pidseq) pids pidseq from tblPID) + 1, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, piddto.getProingseq());
			pstat.setString(2, piddto.getDseq());
			pstat.setString(3, piddto.getPseq());

			pidresult = pstat.executeUpdate();

			pstat.close();
			conn.close();

			return pidresult;

		} catch (Exception e) {
			System.out.println("prescriptionDAO.addPrescritption");
			e.printStackTrace();
		}

		return 0;
	}

	// tblDosage
	public int addDosage(prescriptionDTO pdto) {

		int dresult;

		try {
			conn = DBUtil.open();

			String sql = "insert into tblDosage values ((select max(dseq) + 1 from tblDosage), ?, ?, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, pdto.getUsage());
			pstat.setString(2, pdto.getAmount());
			pstat.setString(3, pdto.getPdate());

			dresult = pstat.executeUpdate();

			pstat.close();
			conn.close();

			return dresult;

		} catch (Exception e) {
			System.out.println("prescriptionDAO.addDosage");
			e.printStackTrace();
		}
		return 0;
	}

	// tblProIng

//	public int addProIng(prescriptionDTO pdto) {
//
//		int proingresult;
//
//		try {
//			conn = DBUtil.open();
//
//			String sql = "insert into tblProIng values (select max(proingseq) + 1 from tblProIng, ?, ?)";
//
//			pstat = conn.prepareStatement(sql);
//
//			pstat.setString(1, pdto.getIseq());
//			pstat.setString(2, pdto.getPseq());
//
//			proingresult = pstat.executeUpdate();
//
//			pstat.close();
//			conn.close();
//
//			return proingresult;
//
//		} catch (Exception e) {
//			System.out.println("prescriptionDAO.addDosage");
//			e.printStackTrace();
//		}
//
//		return 0;
//	}

	// 수의사 번호 받아오기
	public String get(String id) {
		try {

			String sql = "select d.dseq from tblCompany c inner join tblHospital h on c.cseq = h.cseq inner join tblDoctor d on h.hpseq = d.hpseq where c.id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			rs = pstat.executeQuery();
			
			String iid = null;
			
			if (rs.next()) {
				iid = rs.getString("dseq");
			}
			
			return iid;

		} catch (Exception e) {
			System.out.println("prescriptionDAO.get");
			e.printStackTrace();
		}

		return null;
	}

	// 예약 날짜 받아오기
	public String get1(String prseq) {
		try {

			String sql = "select\r\n" + "r.rhseq\r\n" + "from\r\n" + "tblcompany c\r\n" + "inner join tblhospital h\r\n"
					+ "on c.cseq = h.cseq\r\n" + "inner join tblreshos r\r\n" + "on h.hpseq = r.hpseq";

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			return prseq;
		} catch (Exception e) {
			System.out.println("prescriptionDAO.get1");
			e.printStackTrace();
		}
		return null;
	}

	public String findpiseq(String iseq, String pseq) {
		try {
			
			conn = DBUtil.open();
			
			String sql = "select proingseq from tblproing where iseq = ? and pseq = ?";
			
			
			pstat = conn.prepareStatement(sql);
			
			
			pstat.setString(1, iseq);
			pstat.setString(2, pseq);
			
			String proingseq = null;
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				proingseq = rs.getString("proingseq");
				
			}
			
			return proingseq;
			
		} catch (Exception e) {
			System.out.println("prescriptionDAO.findpiseq");
			e.printStackTrace();
		}
		return null;
	}

	public String finddseq() {
		try {
			conn = DBUtil.open();

			String sql = "select max(dseq) as ds from tblDosage";

			rs = stat.executeQuery(sql);

			String dseq = null;

			if (rs.next()) {
				dseq = rs.getString("ds");
			}

			return dseq;
			
		} catch (Exception e) {
			System.out.println("prescriptionDAO.finddseq");
			e.printStackTrace();
		}
		return null;
	}

	public int addPID(String proingseq, String dseq, String ptseq) {
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "insert into tblPID values ((select max(pidseq) + 1 from tblPID), ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, proingseq);
			pstat.setString(2, dseq);
			pstat.setString(3, ptseq);
			
			int result = 0;
			
			result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("prescriptionDAO.addPID");
			e.printStackTrace();
		}
		return 0;
	}

}

package com.project.care.main.user.diagnosis.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.DML;
import com.project.care.Select;
import com.project.care.dto.AnimalListDTO;
import com.project.care.dto.HospitalDTO;
import com.project.care.dto.PurposeDTO;
import com.project.care.dto.ResHosDTO;

public class ReservationDAO {

	Connection conn;
	PreparedStatement pstat;
	Statement stat;
	ResultSet rs;
	
	public ArrayList<AnimalListDTO> getAnimal(String id) {

		try {
			
			String sql = "select s.uaseq, a.name, a.gender, a.age, i.pic, k.kind from tblUser u \r\n"
					+ "inner join tblUserAni s on u.useq = s.useq\r\n"
					+ "inner join tblAnimal a on s.aseq = a.aseq\r\n"
					+ "inner join tblAniInfo i on a.aseq = i.aseq\r\n"
					+ "inner join tblAniKind k on a.kseq = k.kseq\r\n"
					+ "where u.id = ? and i.state ='n'";
			
			pstat = DBUtil.open().prepareStatement(sql);
			pstat.setString(1, id);
			
			rs = pstat.executeQuery();
			
			ArrayList<AnimalListDTO> list = new ArrayList<AnimalListDTO>();
 			
			while (rs.next()) {
				AnimalListDTO dto = new AnimalListDTO();
				
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getString("age"));
				dto.setPic(rs.getString("pic"));
				dto.setUaseq(rs.getString("uaseq"));
				dto.setKind(rs.getString("kind"));
				
				list.add(dto);
			}
			
			rs.close();
			pstat.close();
			DBUtil.close();
			
			return list;
		
		} catch (Exception e) {
			System.out.println("ReservationDAO.getAnimal");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<PurposeDTO> getPurpose() {
		
		try {
			
			String sql = "select * from tblPurpose";
			
			stat = DBUtil.open().createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<PurposeDTO> list = new ArrayList<PurposeDTO>();
				
			while (rs.next()) {
				PurposeDTO dto = new PurposeDTO();
				
				dto.setPseq(rs.getString("pseq"));
				dto.setPurpose(rs.getString("purpose"));
				
				list.add(dto);
			}
			

			rs.close();
			stat.close();
			DBUtil.close();
			
			return list;
		
		} catch (Exception e) {
			System.out.println("ReservationDAO.getPurpose");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<String> getDay(String hpseq) {
		
		try {
			
			String sql = "select o.open from tblHospital h\r\n"
					+ "inner join tblHosDate d on h.hpseq = d.hpseq\r\n"
					+ "inner join tblOpen o on d.openseq = o.openseq\r\n"
					+ "where h.hpseq = ?";
			
			pstat = DBUtil.open().prepareStatement(sql);
			pstat.setString(1, hpseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<String> list = new ArrayList<String>();
 			
			while (rs.next()) {
				list.add(rs.getString("open"));
			}

			rs.close();
			pstat.close();
			DBUtil.close();
			
			return list;
		
		} catch (Exception e) {
			System.out.println("ReservationDAO.getAnimal");
			e.printStackTrace();
		}
		
		return null;
	}

	public HospitalDTO getTime(String hpseq) {
		
		try {
			
			String sql = "select hosname, starttime, endtime from tblHospital where hpseq = ?";
			
			pstat = DBUtil.open().prepareStatement(sql);
			pstat.setString(1, hpseq);
			
			rs = pstat.executeQuery();
			
 			
			if (rs.next()) {
				HospitalDTO dto = new HospitalDTO();
				
				dto.setHosname(rs.getString("hosname"));
				dto.setStarttime(rs.getString("starttime"));
				dto.setEndtime(rs.getNString("endtime"));
				
				rs.close();
				pstat.close();
				DBUtil.close();
				
				return dto;
			}
			
		
		} catch (Exception e) {
			System.out.println("ReservationDAO.getAnimal");
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean checkUserId(ResHosDTO dto) {

		String sql = "select count(*) as cnt from tblUser where id = (select u.id from tblUserAni a inner join tblUser u on a.useq = u.useq where a.uaseq = ?)";
		
		Select pstat = new Select() {
			
			@Override
			public void setParameters(PreparedStatement pstat) throws SQLException {
				pstat.setString(1, dto.getUaseq());
			}
			
			@Override
			public Object getResult(ResultSet rs) throws SQLException {
				
				if (rs.next()) {
					return rs.getInt("cnt");
				}
				return 0;
			}
		};
		
		try {
			
			int result = (int)pstat.executeQuery(sql);
			if (result == 1) { return true; }
			
		} catch (SQLException e) {
			System.out.println("ReservationDAO.checkUserId");
			e.printStackTrace();
		}

		return false;
		
	}

	public int addReservation(ResHosDTO dto) {
		
		String sql = "insert into tblResHos values(seqResHos.nextVal, to_date( ? ,'yy-mm-dd hh24:mi'), default, ?, ?, ?, ?, " + dto.getPicture() + ")";
		
		DML pstat = new DML() {
			
			@Override
			public void setParameters(PreparedStatement pstat) throws SQLException {
				pstat.setString(1, dto.getResdate());
				pstat.setString(2, dto.getUniqueness()); 
				pstat.setString(3, dto.getHpseq());
				pstat.setString(4, dto.getUaseq());
				pstat.setString(5, dto.getPseq());
				
			}
		};

		try {
			return pstat.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("ReservationDAO.addReservation");
			e.printStackTrace();
			return 0;
		}
	}

	public String getRhseq() {
		
		try {
			String sql = "select max(rhseq) as rhseq from tblResHos";
			
			stat = DBUtil.open().createStatement();
			rs = stat.executeQuery(sql);
			
			String rhseq = "";
			if(rs.next()) {
				rhseq = rs.getString("rhseq");
			}
			
			rs.close();
			stat.close();
			DBUtil.close();
			
			return rhseq;
			
		} catch (SQLException e) {
			System.out.println("ReservationDAO.getRhseq");
			e.printStackTrace();
		}
		
		return null;
	}

}

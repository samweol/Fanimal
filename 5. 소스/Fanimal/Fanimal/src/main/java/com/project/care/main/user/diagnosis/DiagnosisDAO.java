package com.project.care.main.user.diagnosis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.DML;
import com.project.care.Select;
import com.project.care.dto.HosReviewDTO;
import com.project.care.dto.HospitalDTO;
import com.project.care.dto.HospitalListDTO;

import oracle.jdbc.OracleTypes;

public class DiagnosisDAO {
	

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;

	public ArrayList<HospitalDTO> getList(HospitalListDTO dto) {
		
		try {
			String sql = "{ call procHospitalList(?, ?, ?, ?, ?, ?, ?, ?) }";
			
			conn = DBUtil.open();
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getBegin());
			cstat.setString(2, dto.getEnd());
			cstat.setString(3, dto.getSigu());
			cstat.setDouble(4, dto.getXcoor());
			cstat.setDouble(5, dto.getYcoor());
			cstat.setString(6, dto.getSearch());
			cstat.setString(7, dto.getAlign());
			cstat.registerOutParameter(8, OracleTypes.CURSOR);
		
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(8);
			
			ArrayList<HospitalDTO> list = new ArrayList<HospitalDTO>();

			while (rs.next()) {
				HospitalDTO hdto = new HospitalDTO();
				
				hdto.setHpseq(rs.getString("hpseq"));
				hdto.setHosname(rs.getString("hosname"));
				hdto.setTel(rs.getString("tel"));
				hdto.setAddress(rs.getString("address"));
				hdto.setAvgStar(String.format("%.1f", rs.getDouble("avgstar")));
				list.add(hdto);
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			return list;
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}

	public int getPages(HospitalListDTO dto) {
		
		String sql = "select count(*) as cnt from tblHospital h inner join tblCompany c on h.cseq = c.cseq\r\n"
					+ " where h.statseq = 1 and c.address like (? || '%') and h.hosname like ('%' || ? ||'%')";
		
		Select pstat = new Select() {
			
			@Override
			public void setParameters(PreparedStatement pstat) throws SQLException {
				pstat.setString(1, dto.getSigu());
				pstat.setString(2, dto.getSearch());
			}
			
			@Override
			public Object getResult(ResultSet rs) throws SQLException {
				
				if (rs.next()) {
					return rs.getInt("cnt");
				}
				return null;
			}
		};
		
		try {
			return Integer.parseInt(pstat.executeQuery(sql).toString());
		} catch (SQLException e) {
			System.out.println("DiagnosisDAO.getPages");
			e.printStackTrace();
			return 0;
		}
	}
	
	public ArrayList<String> getSi() {
		
		return getSG("");
	}
	public ArrayList<String> getGu(String name) {
		
		return getSG(name);
	}
	
	public ArrayList<String> getSG(String name) {
		
		try {
			
			String sql = "";
			
			if (name.equals("")) {
				sql = "select name from tblSi";
			} else {
				
				sql = "select g.name from tblSG sg "
						+ "inner join tblSi s on sg.siseq = s.siseq "
						+ "inner join tblGu g on sg.guseq = g.guseq "
						+ "where s.name = '" + name + "'";
			}
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			
			ArrayList<String> list = new ArrayList<String>();
			
			while (rs.next()) {
				list.add(rs.getString("name"));
			}
			
			rs.close();
			stat.close();
			conn.close();
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public HospitalDTO getHospitalInfo(String hpseq) {

		String sql = "select h.hosname, h.info, h.starttime, h.endtime, c.tel, c.address, c.xcoor, c.ycoor,\r\n"
						+ "    (select avg(star) from tblHosReview where hpseq = h.hpseq) as avgstar\r\n"
						+ "        from tblHospital h inner join tblCompany c on h.cseq = c.cseq\r\n"
						+ "            where hpseq = ?";
		
		Select pstat = new Select() {
			
			@Override
			public void setParameters(PreparedStatement pstat) throws SQLException {
				pstat.setString(1, hpseq);
			}
			
			@Override
			public Object getResult(ResultSet rs) throws SQLException {
				
				HospitalDTO dto = new HospitalDTO();
				
				if(rs.next()) {
					dto.setHosname(rs.getString("hosname"));
					dto.setTel(rs.getString("tel"));
					dto.setAddress(rs.getString("address"));
					dto.setAvgStar(String.format("%.1f", rs.getDouble("avgstar")));
					dto.setInfo(rs.getString("info"));
					dto.setStarttime(rs.getString("starttime"));
					dto.setEndtime(rs.getString("endtime"));
					dto.setXcoor(rs.getString("xcoor"));
					dto.setYcoor(rs.getString("ycoor"));
				}
				
				return dto;
			}
		};
		
		try {
			return (HospitalDTO)pstat.executeQuery(sql);
			
		} catch (Exception e) {
			System.out.println("DiagnosisDAO.getHospitalInfo");
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String> getHosDate(String hpseq) {
		
		
		try {
			String sql = "select open from tblHosDate d inner join tblOpen o on d.openseq = o.openseq where hpseq = ? order by o.openseq";
			
			conn = DBUtil.open();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, hpseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<String> list = new ArrayList<String>();
			
			while (rs.next()) {
				list.add(rs.getString("open"));
			}
			
			rs.close();
			pstat.close();
			conn.close();
			
			return list;
					
		} catch (Exception e) {
			System.out.println("DiagnosisDAO.getrosDate");
			e.printStackTrace();
			
			return null;
		}
		
	}

	public ArrayList<HosReviewDTO> getReview(HosReviewDTO dto) {
		
		String hpseq = dto.getHpseq();
		String hrseq = dto.getHrseq();
		String redate = "";
		String sql = "";
		
		if(hrseq != null) {
			sql = "select redate from tblHosReview where hrseq = ?";
		
			Select pstat = new Select() {
				
				@Override
				public void setParameters(PreparedStatement pstat) throws SQLException {
					pstat.setString(1, hrseq);
				}
				
				@Override
				public Object getResult(ResultSet rs) throws SQLException {

					if (rs.next()) {
						return rs.getString("redate");
					}
					return null;
				}
			};
			
			
			try {
				String result = pstat.executeQuery(sql).toString();
				
				redate = " and r.redate < to_date('" + result + "', 'yyyy-mm-dd hh24:mi:ss')";
			
			} catch (Exception e) {
				System.out.println("DiagnosisDAO.getReview");
				e.printStackTrace();
			}
		}
		
		
		try {
			
			if(dto.getCnt() == null) {
				sql = "select * from (select r.hrseq, r.id, u.nickname, r.review, r.redate, r.star from tblHosReview r inner join tblUser u on r.id = u.id where r.hpseq = ?" + redate + " order by r.redate desc) where rownum between 1 and 10";
			} else {
				sql = "select * from (select r.hrseq, r.id, u.nickname, r.review, r.redate, r.star from tblHosReview r inner join tblUser u on r.id = u.id where r.hpseq = ?" + redate + " order by r.redate desc) where rownum between 1 and " + dto.getCnt();
			}
			
			conn = DBUtil.open();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, hpseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<HosReviewDTO> list = new ArrayList<HosReviewDTO>();
			
			while (rs.next()) {
				HosReviewDTO rdto = new HosReviewDTO();  
				
				rdto.setHrseq(rs.getString("hrseq"));
				rdto.setId(rs.getString("id"));
				rdto.setNickname(rs.getString("nickname"));
				rdto.setReview(rs.getString("review"));
				rdto.setStar(rs.getString("star"));
				rdto.setRedate(rs.getString("redate"));
				
				list.add(rdto);
			}
			
			rs.close();
			pstat.close();
			conn.close();
			
			return list;
					
		} catch (Exception e) {
			System.out.println("DiagnosisDAO.getReview");
			e.printStackTrace();
			
			return null;
		}
		
	}

	public int getReviewCnt(String hpseq) {
		
		String sql = "select count(*) as cnt from tblHosReview where hpseq = ?";

		Select pstat = new Select() {
			
			@Override
			public void setParameters(PreparedStatement pstat) throws SQLException {
				pstat.setString(1, hpseq);				
			}
			
			@Override
			public Object getResult(ResultSet rs) throws SQLException {
				
				if (rs.next()) { return rs.getInt("cnt"); }
				
				return 0;
			}
		};
		
		try {
			
			return (int)pstat.executeQuery(sql);
		
		} catch (Exception e) {
			System.out.println("DiagnosisDAO.getReviewCnt");
			e.printStackTrace();
			return 0;
		}
		
	}

	public int addReview(HosReviewDTO dto) {

		String sql = "insert into tblHosReview values (seqHosReview.nextVal, ?, ?, ?, default, ?)";
		
		DML pstat = new DML() {
			
			@Override
			public void setParameters(PreparedStatement pstat) throws SQLException {
				pstat.setString(1, dto.getId());
				pstat.setString(2, dto.getReview());
				pstat.setString(3, dto.getStar());
				pstat.setString(4, dto.getHpseq());
			}
		}; 
		
		try {
			return pstat.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("DiagnosisDAO.addReview");
			e.printStackTrace();
			return 0;
		}
	}

	public HosReviewDTO getTheReview() {

		try {
			
			String sql = "select * from (select * from tblHosReview order by redate desc) where rownum = 1";
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				HosReviewDTO dto = new HosReviewDTO();
				
				dto.setHrseq(rs.getString("hrseq"));
				dto.setReview(rs.getString("review"));
				dto.setRedate(rs.getString("redate"));
				dto.setStar(rs.getString("star"));
				dto.setId(rs.getString("id"));
				
				rs.close();
				stat.close();
				conn.close();
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println("DiagnosisDAO.getMaxSeqReview");
			e.printStackTrace();
		}
		
		return null;
	}

	public int delReview(String hrseq) {

		String sql = "delete from tblHosReview where hrseq = ?";
		
		DML pstat = new DML() {
			
			@Override
			public void setParameters(PreparedStatement pstat) throws SQLException {
				pstat.setString(1, hrseq);
			}
		};
		
		try {
			return pstat.executeUpdate(sql);
			
		} catch (Exception e) {
			System.out.println("DiagnosisDAO.delReview");
			e.printStackTrace();
			return 0;
		}
	}

	public boolean checkId(HosReviewDTO dto) {
	
		String id = dto.getId();
		String hrseq = dto.getHrseq();
		
		
		try {
			
			String sql = "select count(*) as cnt from tblHosReview where id = '" + id + "' and hrseq = " + hrseq;
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				int result = rs.getInt("cnt");
				
				rs.close();
				stat.close();
				conn.close();
				
				if(result > 0) { return true; }
			}
		
		} catch (Exception e) {
			System.out.println("DiagnosisDAO.checkId");
			e.printStackTrace();
		}
		
		return false;
	}

}

package com.project.care.main.diagnosis.inquire;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.Select;
import com.project.care.dto.HospitalDTO;
import com.project.care.dto.QuesDTO;
import com.project.care.dto.QuesHospitalDTO;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class InquireDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	public QuesHospitalDTO getHospital(String hpseq) {
		
		String sql = "select c.cseq, c.id, h.hosname from tblCompany c inner join tblhospital h on c.cseq = h.cseq where hpseq = ?";
		
		Select pstat = new Select() {
			
			@Override
			public void setParameters(PreparedStatement pstat) throws SQLException {
				pstat.setString(1, hpseq);
			}
			
			@Override
			public Object getResult(ResultSet rs) throws SQLException {

				QuesHospitalDTO dto = new QuesHospitalDTO();

				if (rs.next()) {
					
					dto.setCseq(rs.getString("cseq"));
					dto.setId(rs.getString("id"));
					dto.setHosname(rs.getString("hosname"));
					dto.setHpseq(hpseq);
				}

				return dto;
			}
		};

		try {
			return (QuesHospitalDTO)pstat.executeQuery(sql);
		
		} catch (Exception e) {
			System.out.println("InquireDAO.getHospital");
			e.printStackTrace();
			return null;
		}
	}

	public int getPages(QuesHospitalDTO dto) {
		
		int result = 0;
		
		try {
			
			String key = dto.getQSearchKey();
			String value = dto.getQSearchValue();
			String sql = "";
			
			if (key.equals("title") || key.equals("content")) {
				sql = "select count(*) as cnt from tblQues where " + key + " like '%" + value + "%' and cseq = " + dto.getCseq();
			} else if (key.equals("nickname")) {
				sql = "select count(*) as cnt from tblQues q inner join " 
						+ "(select i.id, nickname from tblId i inner join tblUser u on i.id = u.id "
						+ "union "
						+ "select i.id, hosname from tblId i "
						+ "inner join tblCompany c on i.id = c.id "
						+ "inner join tblHospital h on c.cseq = h.cseq) u "
						+ "on q.id = u.id  where nickname like '%" + value + "%' and cseq=" + dto.getCseq();
			}
			
			stat = DBUtil.open().createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
			
			rs.close();
			stat.close();
			DBUtil.close();
			
		} catch (Exception e) {
			System.out.println("InquireDAO.getPages");
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<QuesDTO> getList(QuesHospitalDTO dto) {
		
		try {
			String sql = "{ call procQues(?, ?, ?, ?, ?, ?, ?) }";
			
			cstat = DBUtil.open().prepareCall(sql);
			cstat.setString(1, dto.getCseq());
			cstat.setString(2, dto.getId());
			cstat.setString(3, dto.getQBegin());
			cstat.setString(4, dto.getQEnd());
			cstat.setString(5, dto.getQSearchKey());
			cstat.setString(6, dto.getQSearchValue());
			cstat.registerOutParameter(7, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(7);
			
			ArrayList<QuesDTO> list = new ArrayList<QuesDTO>();
			
			while (rs.next()) {
				QuesDTO qdto = new QuesDTO();
				
				qdto.setHqseq(rs.getString("hqseq"));
				qdto.setTitle(rs.getString("title"));
				qdto.setPostdate(rs.getString("postdate"));
				qdto.setAttachFile(rs.getString("attachFile"));
				qdto.setNickname(rs.getString("nickname"));
				qdto.setAnswer(rs.getString("answer"));
				qdto.setType(rs.getString("type"));
				qdto.setSecret(rs.getString("secret"));
				
				list.add(qdto);
			}
			
			rs.close();
			cstat.close();
			DBUtil.close();
			
			return list;
			
		} catch (Exception e) {
			System.out.println("InquireDAO.getList");
			e.printStackTrace();
		}
		
		return null;
	}

}

package com.project.care.main.company.enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.care.DBUtil;
import com.project.care.dto.enrollmentDTO;

public class enrollmentDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public enrollmentDAO() {
		conn = DBUtil.open();
	}
	
	// enrollment 서블릿 > dto > 등록하기
	public int add(enrollmentDTO dto) {
		
		try {
			
			String sql = "enrollment";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getHosname());
			pstat.setInt(3, dto.getLicense());
			pstat.setString(4, dto.getInfo());
			pstat.setString(5, dto.getStarttime());
			pstat.setString(6, dto.getEndtime());
			pstat.setString(7, dto.getOpen());
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("enrollmentDAO.add");
			e.printStackTrace();
		}
		
		return 0;
	}
}

package com.project.care.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.care.DBUtil;
import com.project.care.dto.AdminDTO;
import com.project.care.dto.LoginDTO;

public class AdminDAO {
	
	Connection conn;
	PreparedStatement pstat;
	Statement stat;
	ResultSet rs;
	
	public AdminDAO () {
		conn = DBUtil.open();
	}

	public AdminDTO adminLogin(LoginDTO dto) {
		try {
			String sql = "select * from tblAdmin where id = ? and password = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPassword());
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				AdminDTO admindto = new AdminDTO();
				
				admindto.setId(rs.getString("id"));
				admindto.setPassword(rs.getString("password"));
				
				return admindto;
			}
		} catch (Exception e) {
			System.out.println("AdminDAO.adminLogin");
			e.printStackTrace();
		}
		return null;
	}

}

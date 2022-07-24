package com.project.care.main.admin.usersearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.dto.UserDTO;

public class userinfoDAO {
	
	Connection conn;
	PreparedStatement pstat;
	Statement stat;
	ResultSet rs;
	
	public userinfoDAO() {
		conn = DBUtil.open();
	}
	// 유저 목록 불러와주세요 > userInfoList
		public ArrayList<UserDTO> list() {
			try {
		         String sql = "select useq, id, name, nickname, tel, address, joindate from tblUser";
		         
		         stat = conn.createStatement();
		         
		         rs = stat.executeQuery(sql);
		         
		         ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		         
		         while (rs.next()) {
		            
		        	UserDTO dto =  new UserDTO();
		            
		        	dto.setUseq(rs.getString("useq"));
		            dto.setId(rs.getString("id"));
		            dto.setName(rs.getString("name"));
		            dto.setNickname(rs.getString("nickname"));
		            dto.setTel(rs.getString("tel"));
		            dto.setAddress(rs.getString("address"));
		            dto.setJoindate(rs.getString("joindate"));
		            
		            list.add(dto);
		            
		         }
		         
		         return list;
		         
		      } catch (Exception e) {
		         System.out.println("userifoDAO.list");
		         e.printStackTrace();
		      }
			
			return null;
		}
}

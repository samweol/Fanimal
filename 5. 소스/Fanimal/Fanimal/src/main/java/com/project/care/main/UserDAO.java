package com.project.care.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.project.care.DBUtil;
import com.project.care.dto.LoginDTO;
import com.project.care.dto.UserDTO;

public class UserDAO {

	Connection conn;
	PreparedStatement pstat;
	Statement stat;
	ResultSet rs;
	
	public UserDAO () {
		conn = DBUtil.open();
	}

	public UserDTO userLogin(LoginDTO dto) {
		try {
			String sql = "select * from tblUser where id = ? and password = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPassword());
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				UserDTO userdto = new UserDTO();
				userdto.setUseq(rs.getString("useq"));
				userdto.setId(rs.getString("id"));
				userdto.setName(rs.getString("name"));
				userdto.setNickname(rs.getString("nickname"));
				userdto.setPassword(rs.getString("password"));
				userdto.setTel(rs.getString("tel"));
				userdto.setAddress(rs.getString("address"));
				userdto.setJoindate(rs.getString("joindate"));
				userdto.setXcoor(rs.getDouble("xcoor"));
				userdto.setYcoor(rs.getDouble("ycoor"));
				userdto.setBirth(rs.getString("birth"));
				userdto.setPicture(rs.getString("picture"));
				
				return userdto;
			}
			
		} catch (Exception e) {
			System.out.println("UserDAO.userLogin");
			e.printStackTrace();
		}
		return null;
	}

	public String findId(HashMap<String, String> map) {
		try {
			//회원 아이디 찾기
			String sql = "select * from tblUser where name = ? and tel = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("name"));
			pstat.setString(2, map.get("tel"));
			
			rs = pstat.executeQuery();
			
			if(rs.next()) { // 아이디 있으면 아이디 반환
				return rs.getString("id"); 
			}
		} catch (Exception e) {
			System.out.println("UserDAO.findId");
			e.printStackTrace();
		}
		return null;
	}

	public String findPassword(HashMap<String, String> map) {
		try {
			String sql = "select * from tblUser where name = ? and id = ? and tel = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("name"));
			pstat.setString(2, map.get("id"));
			pstat.setString(3, map.get("tel"));
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getString("password");
			}
		} catch (Exception e) {
			System.out.println("UserDAO.findPassword");
			e.printStackTrace();
		}
		return null;
	}

	public int updatePassword(HashMap<String, String> map) {
		try {
			String sql = "update tblUser set password = ? where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("password"));
			pstat.setString(2, map.get("id"));
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("UserDAO.updatePassword");
			e.printStackTrace();
		}
		return 0;
	}

	public int userRegister(UserDTO dto) {
		try {
			
			if(dto.getPicture() == null) {
				String sql = "insert into tblUser values (seqUser.nextVal, ?, ?, ?, ?, ?, ?, default, ?, ?, ?, default)";				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, dto.getId());
				pstat.setString(2, dto.getName());
				pstat.setString(3, dto.getNickname());
				pstat.setString(4, dto.getPassword());
				pstat.setString(5, dto.getTel());
				pstat.setString(6, dto.getAddress());
				pstat.setDouble(7, dto.getXcoor());
				pstat.setDouble(8, dto.getYcoor());
				pstat.setString(9, dto.getBirth());
				
				return pstat.executeUpdate();
			} else {
				String sql = "insert into tblUser values (seqUser.nextVal, ?, ?, ?, ?, ?, ?, default, ?, ?, ?, ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, dto.getId());
				pstat.setString(2, dto.getName());
				pstat.setString(3, dto.getNickname());
				pstat.setString(4, dto.getPassword());
				pstat.setString(5, dto.getTel());
				pstat.setString(6, dto.getAddress());
				pstat.setDouble(7, dto.getXcoor());
				pstat.setDouble(8, dto.getYcoor());
				pstat.setString(9, dto.getBirth());
				pstat.setString(10, dto.getPicture());
				
				return pstat.executeUpdate();
			}
			
			
			
		} catch (Exception e) {
			System.out.println("UserDAO.userRegister");
			e.printStackTrace();
		}
		return 0;
	}

	public void addId(UserDTO dto) {
		try {
			String sql = "insert into tblId values (?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("UserDAO.addId");
			e.printStackTrace();
		}
		
	}

	public int checkId(String id) {
		try {
			String sql = "select * from tblId where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("UserDAO.checkId");
			e.printStackTrace();
		}
		return 0;
	}

	public int checkNickname(String nickname) {
		try {
			String sql = "select * from tblUser where nickname = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, nickname);
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("UserDAO.checkNickname");
			e.printStackTrace();
		}
		return 0;
	}

	public UserDTO getUserInfo(UserDTO userdto) {
		try {
			String sql = "select * from tblUser where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, userdto.getId());
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setName(rs.getString("name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setId(rs.getString("id"));
				dto.setBirth(rs.getString("birth"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				
				return dto;
			}
		} catch (Exception e) {
			System.out.println("UserDAO.getUserInfo");
			e.printStackTrace();
		}
		return null;
	}
	
}

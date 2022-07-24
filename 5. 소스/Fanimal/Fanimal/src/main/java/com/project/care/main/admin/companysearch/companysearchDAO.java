package com.project.care.main.admin.companysearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.dto.CompanyDTO;
import com.project.care.dto.companyviewDTO;

public class companysearchDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public companysearchDAO() {
		conn = DBUtil.open();
	}

	// 목록조회
	public ArrayList<CompanyDTO> list() {
		
		try {
			String sql = "select cseq, id, name, tel, address, business from tblCompany";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<CompanyDTO> list = new ArrayList<CompanyDTO>();
			
			while (rs.next()) {
	            
	            CompanyDTO dto =  new CompanyDTO();
	            
	            dto.setCseq(rs.getString("cseq"));
	            dto.setId(rs.getString("id"));
	            dto.setName(rs.getString("name"));
	            dto.setTel(rs.getString("tel"));
	            dto.setAddress(rs.getString("address"));
	            dto.setBusiness(rs.getString("business"));
	            
	            list.add(dto);
	         }
	         
	         return list;
		} catch (Exception e) {
			System.out.println("companysearchDAO.list");
			e.printStackTrace();
		}
		
		return null;
	}

	public companyviewDTO cget(String seq) {
		
		try {
			
			String sql = "select h.hpseq seq, h.hosname hosname, h.license license, h.starttime starttime, h.endtime endtime, o.open open, h.info info, s.stat state from tblhospital h inner join tblstat s on h.statseq = s.statseq inner join tblhosdate d on d.hpseq = h.hpseq inner join tblopen o on d.opneseq = o.opneseq where h.hpseq = cseq and cseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			companyviewDTO dto = new companyviewDTO();
			 
			if (rs.next())	{
				
				dto.setHosname(rs.getString("hosname"));
	            dto.setLicense(rs.getString("license"));
	            dto.setStarttime(rs.getString("starttime"));
	            dto.setEndtime(rs.getString("endtime"));
	            dto.setOpen(rs.getString("open"));
	            dto.setInfo(rs.getString("info"));
	            dto.setState(rs.getString("state"));
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("companysearchDAO.get");
			e.printStackTrace();
		}
		return null;
	}
	
		public CompanyDTO get(String seq) {
		
		try {
			
			String sql = "select id, email, name, business from tblCompany where cseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			CompanyDTO dto = new CompanyDTO();
			 
			if (rs.next())	{
				
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setId(rs.getString("id"));
				dto.setBusiness(rs.getString("business"));
				
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("companysearchDAO.get");
			e.printStackTrace();
		}
		return null;
	}
	
}
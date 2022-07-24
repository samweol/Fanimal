package com.project.care.main.admin.companyapprove;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.dto.CompanyapproveDTO;

public class companyapproveDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public companyapproveDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<CompanyapproveDTO> list() {
		
			try {
				
				String sql = "select c.cseq cseq, i.id id, c.name name, c.tel tel, c.address address, c.business business, h.hosname hosname, h.license license, h.info info, h.starttime starttime, h.endtime endtime, s.stat stat from tblid i inner join tblCompany c on i.id = c.id inner join tblHospital h on c.cseq = h.cseq inner join tblStat s on h.statseq = s.statseq";
				
				stat = conn.createStatement();
				
				rs = stat.executeQuery(sql);
				
				ArrayList<CompanyapproveDTO> list = new ArrayList<CompanyapproveDTO>();
				
				while (rs.next()) {
		            
					CompanyapproveDTO dto =  new CompanyapproveDTO();
		            
					dto.setCseq(rs.getString("cseq"));
					dto.setId(rs.getString("id"));
		            dto.setName(rs.getString("name"));
		            dto.setTel(rs.getString("tel"));
		            dto.setAddress(rs.getString("address"));
		            dto.setBusiness(rs.getString("business"));
		            dto.setStat(rs.getString("stat"));
		            
		            list.add(dto);
		         }
		         
		         return list;
			} catch (Exception e) {
				System.out.println("companysearchDAO.list");
				e.printStackTrace();
			}
			
			return null;
		}

	public CompanyapproveDTO get(String seq) {
			try {
				String sql = "select h.hosname hosname, h.license license, h.info info, h.starttime starttime, h.endtime endtime, s.stat stat from tblid i inner join tblCompany c on i.id = c.id inner join tblHospital h on c.cseq = h.cseq inner join tblStat s on h.statseq = s.statseq where c.cseq=?";

				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				rs = pstat.executeQuery();
				
				CompanyapproveDTO dto = new CompanyapproveDTO();
				
				if (rs.next())	{
					
					dto.setHosname(rs.getString("hosname"));
					dto.setLicense(rs.getString("license"));
					dto.setInfo(rs.getString("info"));
					dto.setStarttime(rs.getString("starttime"));
					dto.setEndtime(rs.getString("endtime"));
					
					dto.setStat(rs.getString("stat"));

				}
				
				return dto;	
			} catch (Exception e) {
				System.out.println("companyapproveDAO.get");
				e.printStackTrace();
			}

		return null;
	}

	// 승인완료 눌렀을 때 > dto > 수정
	public int check(CompanyapproveDTO dto) {
		
		try {
			
			String sql = "select s.stat stat from tblid i inner join tblCompany c on i.id = c.id inner join tblHospital h on c.cseq = h.cseq inner join tblStat s on h.statseq = s.statseq";
					
			pstat.setString(1, dto.getStat());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("companyapproveDAO.check");
			e.printStackTrace();
		}
		
		return 0;
	}

}

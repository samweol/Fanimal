package com.project.care.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.care.DBUtil;
import com.project.care.dto.CompanyDTO;
import com.project.care.dto.LoginDTO;

public class CompanyDAO {

	Connection conn;
	PreparedStatement pstat;
	Statement stat;
	ResultSet rs;
	
	public CompanyDAO () {
		conn = DBUtil.open();
	}

	public CompanyDTO companyLogin(LoginDTO dto) {
		try {
			String sql = "select * from tblCompany where id = ? and password = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPassword());
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				CompanyDTO companydto = new CompanyDTO();
				
				companydto.setCseq(rs.getString("cseq"));
				companydto.setId(rs.getString("id"));
				companydto.setName(rs.getString("name"));
				companydto.setTel(rs.getString("tel"));
				companydto.setAddress(rs.getString("address"));
				companydto.setXcoor(rs.getDouble("xcoor"));
				companydto.setYcoor(rs.getDouble("ycoor"));
				companydto.setBusiness(rs.getString("business"));
				companydto.setPassword(rs.getString("password"));
				companydto.setEmail(rs.getString("email"));
				
				return companydto;
			}
		} catch (Exception e) {
			System.out.println("CompanyDAO.companyLogin");
			e.printStackTrace();
		}
		return null;
	}

	public String findId(HashMap<String, String> map) {
		try {
			String sql = "select * from tblCompany where name = ? and tel = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("name"));
			pstat.setString(2, map.get("tel"));
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getString("id");
			}
		} catch (Exception e) {
			System.out.println("CompanyDAO.findId");
			e.printStackTrace();
		}
		return null;
	}

	public String findPassword(HashMap<String, String> map) {
		try {
			String sql = "select * from tblCompany where name = ? and tel = ? and id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("name"));
			pstat.setString(2, map.get("tel"));
			pstat.setString(3, map.get("id"));
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getString("password");
			}
		} catch (Exception e) {
			System.out.println("CompanyDAO.findPassword");
			e.printStackTrace();
		}
		return null;
	}

	public int updatePassword(HashMap<String, String> map) {
		try {
			String sql = "update tblCompany set password = ? where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("password"));
			pstat.setString(2, map.get("id"));
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("CompanyDAO.updatePassword");
			e.printStackTrace();
		}
		return 0;
	}

	public CompanyDTO getHospitalInfo(CompanyDTO companydto) {
		try {
			String sql = "select c.id, h.hosname, h.license, h.info, h.starttime, h.endtime, st.stat from tblHospital h\r\n"
					+ "    inner join tblCompany c\r\n"
					+ "        on c.cseq = h.cseq\r\n"
					+ "            inner join tblStat st\r\n"
					+ "                on st.statseq = h.statseq\r\n"
					+ "                    where c.id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, companydto.getId());
			rs = pstat.executeQuery();
			
			//System.out.println(companydto.getId());
			
			if(rs.next()) {
				CompanyDTO dto = new CompanyDTO();
				dto.setHosname(rs.getString("hosname"));
				dto.setLicense(rs.getString("license"));
				dto.setInfo(rs.getString("info"));
				dto.setStarttime(rs.getString("starttime"));
				dto.setEndtime(rs.getString("endtime"));
				dto.setStat(rs.getString("stat"));
				
				return dto;
			}
		} catch (Exception e) {
			System.out.println("CompanyDAO.getHospitalInfo");
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getOpenDate(CompanyDTO companydto) {
		try {
			String sql = "select c.id, h.hosname, op.open from tblHosDate hd\r\n"
					+ "    inner join tblHospital h\r\n"
					+ "        on h.hpseq = hd.hpseq\r\n"
					+ "            inner join tblOpen op\r\n"
					+ "                on op.openseq = hd.openseq\r\n"
					+ "                    inner join tblCompany c\r\n"
					+ "                        on c.cseq = h.cseq\r\n"
					+ "                            where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, companydto.getId());
			rs = pstat.executeQuery();
			
			while(rs.next()) {
				ArrayList<String> mlist = new ArrayList<String>();
				mlist.add(rs.getString("open"));
				
				return mlist;
			}
		} catch (Exception e) {
			System.out.println("CompanyDAO.getOpenDate");
			e.printStackTrace();
		}
		return null;
	}

	public int updateCompany(CompanyDTO dto) {
		try {
			String sql = "update tblCompany set email = ? where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getEmail());
			pstat.setString(2, dto.getId());
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("CompanyDAO.updateCompany");
			e.printStackTrace();
		}
		return 0;
	}

	public CompanyDTO getCompanyInfo(CompanyDTO companydto) {
		try {
			String sql = "select * from tblCompany where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, companydto.getId());
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				CompanyDTO dto = new CompanyDTO();
				dto.setName(rs.getString("name"));
				dto.setBusiness(rs.getString("business"));
				dto.setId(rs.getString("id"));
				dto.setEmail(rs.getString("email"));
				
				return dto;
			}
		} catch (Exception e) {
			System.out.println("CompanyDAO.getCompanyInfo");
			e.printStackTrace();
		}
		return null;
	}

	public int findHpseq(CompanyDTO dto) {
		try {
			String sql = "select h.hpseq from tblHospital h\r\n"
					+ "    inner join tblCompany c\r\n"
					+ "        on c.cseq = h.cseq\r\n"
					+ "            where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("hpseq");
			}
		} catch (Exception e) {
			System.out.println("CompanyDAO.findHpseq");
			e.printStackTrace();
		}
		return 0;
	}

	public int updateHospital(int hpseq, String info) {
		try {
			String sql = "update tblHospital set info = ? where hpseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, info);
			pstat.setInt(2, hpseq);
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("CompanyDAO.updateHospital");
			e.printStackTrace();
		}
		return 0;
	}

	public int companyRegister(CompanyDTO dto) {
		try {
			String sql = "insert into tblCompany values ((select  NVL(MAX(cseq), 0) + 1 from tblCompany), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getName());
			pstat.setString(3, dto.getTel());
			pstat.setString(4, dto.getAddress());
			pstat.setDouble(5, dto.getXcoor());
			pstat.setDouble(6, dto.getYcoor());
			pstat.setString(7, dto.getBusiness());
			pstat.setString(8, dto.getPassword());
			pstat.setString(9, dto.getEmail());
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("CompanyDAO.companyRegister");
			e.printStackTrace();
		}
		return 0;
	}

	public void addId(CompanyDTO dto) {
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

	public int checkBusiness(String business) {
		try {
			String sql = "select * from tblCompany where business = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, business);
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("CompanyDAO.checkBusiness");
			e.printStackTrace();
		}
		return 0;
	}

	public CompanyDTO companyState(LoginDTO dto) {
		try {
			String sql = "select c.id, h.hosname, st.stat from tblCompany c\r\n"
					+ "    inner join tblHospital h\r\n"
					+ "        on h.cseq = c.cseq\r\n"
					+ "            inner join tblStat st\r\n"
					+ "                on st.statseq = h.statseq\r\n"
					+ "                    where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				CompanyDTO cdto = new CompanyDTO();
				cdto.setId(rs.getString("id"));
				cdto.setHosname(rs.getString("hosname"));
				cdto.setStat(rs.getString("stat"));
				
				return cdto;
			}
		} catch (Exception e) {
			System.out.println("CompanyDAO.companyState");
			e.printStackTrace();
		}
		return null;
	}

	public int getHpseq(String id) {
		try {
			String sql = "select hpseq from tblHospital h\r\n"
					+ "    inner join tblCompany c\r\n"
					+ "        on h.cseq = c.cseq\r\n"
					+ "            where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				int hpseq = rs.getInt("hpseq");
				return hpseq;
			}
		} catch (Exception e) {
			System.out.println("CompanyDAO.getHpseq");
			e.printStackTrace();
		}
		return 0;
	}

	public int cancelEnrollment(int hpseq) {
		try {
			String sql = "delete from tblHospital where hpseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, hpseq);
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("CompanyDAO.cancelEnrollment");
			e.printStackTrace();
		}
		return 0;
	}

	public void deleteDoctor(int hpseq) {
		try {
			String sql = "delete from tblDoctor where hpseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, hpseq);
			
			pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("CompanyDAO.deleteDoctor");
			e.printStackTrace();
		}
		
	}

	public void deleteHosDate(int hpseq) {
		try {
			String sql = "delete from tblHosDate where hpseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, hpseq);
			
			pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("CompanyDAO.deleteHosDate");
			e.printStackTrace();
		}
		
	}

	public ArrayList<Integer> countMonth(CompanyDTO dto) {
		try {
			String sql = "select count(*) as count, TO_CHAR(TO_DATE(rh.resdate), 'YYYY-MM') as MONTHLYDATA  from tblResHos rh \r\n"
					+ "    inner join tblHospital h\r\n"
					+ "        on rh.hpseq = h.hpseq\r\n"
					+ "            inner join tblCompany c\r\n"
					+ "                on c.cseq = h.cseq\r\n"
					+ "                    where c.cseq = ?\r\n"
					+ "                        group by TO_CHAR(TO_DATE(rh.resdate), 'YYYY-MM')\r\n"
					+ "                            order by MONTHLYDATA";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCseq());
			rs = pstat.executeQuery();
			
			ArrayList<Integer> mlist = new ArrayList<Integer>();
			
			while(rs.next()) {
				
				mlist.add(rs.getInt("count"));
				
				
			}
			return mlist;
		} catch (Exception e) {
			System.out.println("CompanyDAO.countMonth");
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getMonth(CompanyDTO dto) {
		try {
			String sql = "select count(*) as count, TO_CHAR(TO_DATE(rh.resdate), 'YYYY-MM') as MONTHLYDATA  from tblResHos rh \r\n"
					+ "    inner join tblHospital h\r\n"
					+ "        on rh.hpseq = h.hpseq\r\n"
					+ "            inner join tblCompany c\r\n"
					+ "                on c.cseq = h.cseq\r\n"
					+ "                    where c.cseq = ?\r\n"
					+ "                        group by TO_CHAR(TO_DATE(rh.resdate), 'YYYY-MM')\r\n"
					+ "                            order by MONTHLYDATA";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCseq());
			rs = pstat.executeQuery();
			
			ArrayList<String> mlist = new ArrayList<String>();
			
			while(rs.next()) {
				
				mlist.add(rs.getString("MONTHLYDATA"));
				
				
			}
			return mlist;
		} catch (Exception e) {
			System.out.println("CompanyDAO.getMonth");
			e.printStackTrace();
		}
		return null;
	}

	
}

package com.project.care.main.company.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.dto.componentDTO;
import com.project.care.dto.reservationDTO;

public class reservationDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public reservationDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<reservationDTO> list() {

		try {
			String sql = "select r.rhseq seq, a.name name, v.visit visit, r.resdate resdate, p.purpose purpose from tblanimal a inner join tbluserani e on a.aseq = e.aseq inner join tbluser u on u.useq = e.useq inner join tblreshos r on e.uaseq = r.uaseq inner join tblvisit v on v.visitseq = r.visitseq inner join tblpurpose p on p.pseq = r.pseq order by resdate desc";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<reservationDTO> list = new ArrayList<reservationDTO>();

			while (rs.next()) {

				reservationDTO dto = new reservationDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setVisit(rs.getString("visit"));
				dto.setResdate(rs.getString("resdate"));
				dto.setPurpose(rs.getString("purpose"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("reservationDAO.list");
			e.printStackTrace();
		}

		return null;
	}

	public reservationDTO get(String seq) {

		try {

			String sql = "select r.rhseq seq, a.name name, v.visit visit, r.resdate resdate, p.purpose purpose, u.name username, u.tel tel, r.uniqueness uniqueness, r.picture pic from tblanimal a inner join tbluserani e on a.aseq = e.aseq inner join tblaniinfo f on a.aseq = f.aseq inner join tbluser u on u.useq = e.useq inner join tblreshos r on e.uaseq = r.uaseq inner join tblvisit v on v.visitseq = r.visitseq inner join tblpurpose p on p.pseq = r.pseq where r.rhseq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			reservationDTO dto = new reservationDTO();

			if (rs.next()) {

				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setVisit(rs.getString("visit"));
				dto.setResdate(rs.getString("resdate"));
				dto.setPurpose(rs.getString("purpose"));
				dto.setUsername(rs.getString("username"));
				dto.setTel(rs.getString("tel"));
				dto.setUniqueness(rs.getString("uniqueness"));
				dto.setPic(rs.getString("pic"));

			}

			return dto;

		} catch (Exception e) {
			System.out.println("reservationDAO.get");
			e.printStackTrace();
		}
		return null;
	}

}
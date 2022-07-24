package com.project.care.main.community;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.care.DBUtil;
import com.project.care.dto.CommuDTO;
import com.project.care.dto.FreeCommentDTO;


public class CommuDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public CommuDAO() {
		conn = DBUtil.open();
	}
	
	public int add(CommuDTO dto) {
		
		try {
			
			String sql = "insert into tblCommu (commuseq, title, id, postDay, readcount, attachFile, typeseq, field, orgattachFile) values (seqcommu.nextVal, ?, ?, default, default, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getId());
			pstat.setString(3, dto.getAttachFile());
			pstat.setString(4, dto.getTypeseq());
			pstat.setString(5, dto.getField());
			pstat.setString(6, dto.getOrgattachFile());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("CommuDAO.add");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//목록
	public ArrayList<CommuDTO> list(HashMap<String, String> map) {
		
		
		try {
			
			//String sql = "select commuseq, title, id, readcount, postday from tblCommu ";
			
			String where = "";
			
			where = String.format("where typeseq = %s"
					, map.get("division"));
			
			//System.out.println(where);
			
			//where typeseq = 1
			//where typeseq = 1 and subject like '%aaa%'
			
			if (map.get("isSearch").equals("y")) {
				where = String.format("where %s like '%%%s%%'"
										, map.get("column")
										, map.get("word"));
			}
			//전체목록에 대해서 페이징이 되고 있다.
			
			String sql = String.format("select * from \r\n"
		               + "    (select b.*, rownum as rnum from (select \r\n"
		               + "        a.*,\r\n"
		               + "        case when (select nickname from tblUser where id = a.id) is not null then (select nickname from tblUser where id = a.id) else (select hosname from tblHospital where cseq = (select cseq from tblCompany where id = a.id)) end as nickname \r\n"
		               + "    from tblCommu a %s order by postday desc)  b\r\n"
		               + "    )  where rnum between %s and %s\r\n"
		               + "order by postday desc", where, map.get("begin"), map.get("end"));
			
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<CommuDTO> list = new ArrayList<CommuDTO>();
			
			while (rs.next()) {
				
				CommuDTO dto = new CommuDTO();
				
				dto.setCommuseq(rs.getString("commuseq"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setPostDay(rs.getString("postday"));
				dto.setNickname(rs.getString("nickname"));
				
				list.add(dto);
				
			}
			
			return list;			
			
		} catch (Exception e) {
			System.out.println("CommuDAO.list");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public CommuDTO get(String seq) {
		
		try {
			
			String sql = "select c.commuseq, c.title, c.id, c.postday, c.readcount, c.attachfile, c.orgattachfile, c.typeseq, c.field from tblCommu c inner join tblid i on i.id = c.id full outer join tblUser u on i.id = u.id full outer join tblAdmin a on i.id = a.id where c.commuseq=?";
			//System.out.println(seq);
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			CommuDTO dto = new CommuDTO();
			
			if (rs.next()) {
				
				dto.setCommuseq(rs.getString("commuseq"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setPostDay(rs.getString("postday"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setAttachFile(rs.getString("attachFile"));
				dto.setOrgattachFile(rs.getString("orgattachFile"));
				dto.setTypeseq(rs.getString("typeseq"));
				dto.setField(rs.getString("field"));
				//System.out.println(dto);
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("CommuDAO.get");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//조회수
	public void updateReadcount(String seq) {

		try {
			
			String sql = "update tblCommu set readcount = readcount + 1 where commuseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("CommuDAO.updateReadcount");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		CommuDAO dao = new CommuDAO();
		
		
	}
	
	public int del(String seq) {
		
		try {
			
			String sql = "delete from tblCommu where commuseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("CommuDAO.del");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//수정
	public int edit(CommuDTO dto) {
		
		try {
				
			String sql = "update tblCommu set title = ?, field = ?, attachFile = ? where commuseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getField());
			pstat.setString(3, dto.getAttachFile());
			pstat.setString(4, dto.getCommuseq());
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("CommuDAO.edit");
			e.printStackTrace();
		}
		
		return 0;
	}
		
		
	//댓글 추가
	public int addComment(FreeCommentDTO dto) {
		
		try {
			
			String sql = "insert into tblAnswer (caseq, content, answerDay, id, comuseq) values (seqAnswer.nextVal, ?, default, ?, ?)";
			
			
			System.out.println(dto.getComuseq());
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getId());
			pstat.setString(3, dto.getComuseq());
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("CommuDAO.addComment");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//댓글 목록
	public ArrayList<FreeCommentDTO> listComment(String seq) {
		
		try {
			
			String sql = "select a.caseq, a.content, a.answerday, a.id, a.comuseq from tblAnswer a inner join tblId i on i.id = a.id inner join tblUser u on i.id = u.id where a.comuseq =? order by a.caseq desc";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<FreeCommentDTO> clist = new ArrayList<FreeCommentDTO>();
			
			while (rs.next()) {
				
				FreeCommentDTO dto = new FreeCommentDTO();
				
				dto.setCaseq(rs.getString("caseq"));
				dto.setContent(rs.getString("content"));
				dto.setAnswerDay(rs.getString("answerDay"));
				dto.setId(rs.getString("id"));
				
				dto.setComuseq(rs.getString("comuseq"));
				
				clist.add(dto);
			}
			
			return clist;
			
		} catch (Exception e) {
			System.out.println("CommuDAO.listComment");
			e.printStackTrace();
		}
		
		return null;
	}
	
	//삭제
	public int delcomment(String seq) {
		
		try {
			
			String sql = "delete from tblAnswer where caseq = ?";
			
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("CommuDAO.delcomment");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	//댓글 모두 삭제
	public void delCommentAll(String seq) {
		
		try {
			
			String sql = "delete from tblAnswer where comuseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("CommuDAO.delCommentAll");
			e.printStackTrace();
		}
		
	}

	//댓글 수정
	public int editComment(FreeCommentDTO dto) {
		
		try {
			
			String sql = "update tblAnswer set content = ? where caseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getCaseq());
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("CommuDAO.editComment");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//게시물 총 수
	public int getTotalCount(HashMap<String, String> map) {
		
		try {
			
			String where = "";
			
			where = String.format("where typeseq = %s"
					, map.get("division"));
			
			if (map.get("isSearch").equals("y")) {
				where = String.format(" where %s like '%%%s%%'"
										, map.get("column")
										, map.get("word"));
			}
			
			String sql = "select count(*) as cnt from tblCommu " + where;
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
			
		} catch (Exception e) {
			System.out.println("CommuDAO.getTotalCount");
			e.printStackTrace();
		}
		
		return 0;
	}

}

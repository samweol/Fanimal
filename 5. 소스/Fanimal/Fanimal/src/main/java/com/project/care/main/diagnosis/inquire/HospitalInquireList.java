package com.project.care.main.diagnosis.inquire;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CompanyDTO;
import com.project.care.dto.HospitalListDTO;
import com.project.care.dto.QuesDTO;
import com.project.care.dto.QuesHospitalDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/inquire/hospitalinquirelist.do")
public class HospitalInquireList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		InquireDAO dao = new InquireDAO();
		
		String type = "2";
		String id = "";
		
		if (session.getAttribute("auth") instanceof CompanyDTO) {
			CompanyDTO dto = (CompanyDTO)session.getAttribute("auth");
			id = session.getId();
			type = "0";
		} else if (session.getAttribute("auth") instanceof UserDTO) {
			UserDTO dto = (UserDTO)session.getAttribute("auth");
			id = session.getId();
			type = "1";
		}
		
		
		HospitalListDTO hdto = new HospitalListDTO();
		hdto.setSi(req.getParameter("si"));
		hdto.setGu(req.getParameter("gu"));
		hdto.setAlign(req.getParameter("align"));
		hdto.setSearch(req.getParameter("search"));
		hdto.setPage(req.getParameter("page"));
		String hpseq = req.getParameter("hpseq");
		
		if (hpseq == null) {
			return;
		}
		
		
		QuesHospitalDTO dto = dao.getHospital(hpseq);
		String qSearchKey = req.getParameter("qSearchKey");
		String qSearchValue = req.getParameter("qSearchValue");
		
		if (qSearchKey != null && qSearchValue != null) { 
			dto.setQSearchKey(qSearchKey); 
			dto.setQSearchValue(qSearchValue);
		} else {
			dto.setQSearchKey("title");
			dto.setQSearchValue("");
		}
		
		
		//페이징처리
		String qPage = req.getParameter("qPage");
		
		int totalNum = dao.getPages(dto); //전체 출력물 수
		int onePageNum = 20; //페이지당 출력물 수
		
		int totalPage = (int)Math.ceil((double)totalNum / onePageNum); //전체 페이지수
		int nowPage = 1;
		
		if (qPage != null) { nowPage = Integer.parseInt(qPage); }
		if (nowPage > totalPage) { nowPage = totalPage; }
		if (nowPage < 1) { nowPage = 1; }
		dto.setQPage(String.valueOf(nowPage));
		
		int begin = onePageNum * (nowPage - 1) + 1;
		int end =  onePageNum * nowPage;
		dto.setQBegin("" + begin);
		dto.setQEnd("" + end);
		
		int pageBarNum = 10;
		int beginPage = ((nowPage-1) / pageBarNum) *  pageBarNum + 1;
		int endPage = beginPage + pageBarNum - 1;
		if (endPage > totalPage) { endPage = totalPage; }
		dto.setQBeginPage(beginPage);
		dto.setQEndPage(endPage);
		
		//DAO
		ArrayList<QuesDTO> list = dao.getList(dto);
		
		for(QuesDTO q :list) {
			q.setPostdate(q.getPostdate().substring(0, 16));
		}
		
		req.setAttribute("dto", dto);   //문의게시판 검색조건
		req.setAttribute("hdto", hdto); //병원리스트 검색조건
		req.setAttribute("list", list);
		req.setAttribute("type", type);
		req.setAttribute("id", id);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/diagnosis/inquire/hospitalinquirelist.jsp");

		dispatcher.forward(req, resp);
	}


}

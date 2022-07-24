package com.project.care.main.user.diagnosis;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.HospitalDTO;
import com.project.care.dto.HospitalListDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/diagnosis/hospitallist.do")
public class HospitalList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserDTO auth = (UserDTO)session.getAttribute("auth");
		
		HospitalListDTO dto = new HospitalListDTO();
		String si = req.getParameter("si");
		String gu = req.getParameter("gu");
		String page = req.getParameter("page");
		String align = req.getParameter("align");
		String search = req.getParameter("search");
		
		if (align == null) { align = "distance"; }
		
		dto.setAlign(align);
		dto.setSearch(search);
		
		//시도 시군구 포장
		String sg = "";
		
		if (si == null) { si = "all"; }
		if (gu == null) { gu = "all"; }
		dto.setSi(si);
		dto.setGu(gu);
		
		if (!gu.equals("all")) {
			dto.setSigu(si + " " + gu);
		} else if (!si.equals("all")) {
			dto.setSigu(si);
		} else {
			dto.setSigu("");
		}
		
		//거리순 정렬
		if (auth != null) {
			dto.setXcoor((auth.getXcoor()));
			dto.setYcoor((auth.getYcoor()));
		} else {
			dto.setXcoor(203469.119368);
			dto.setYcoor(443500.904043);
		}
		
		//병원명 검색
		if (search == null) {
			dto.setSearch("");
		}
		
		
		//페이징
		DiagnosisDAO dao = new DiagnosisDAO();
		
		int totalNum = dao.getPages(dto); //전체 출력물 수
		int onePageNum = 20; //페이지당 출력물 수
		
		int totalPage = (int)Math.ceil((double)totalNum / onePageNum); //전체 페이지수
		int nowPage = 1;
		
		if (page != null) { nowPage = Integer.parseInt(page); }
		
		if (nowPage < 1) {
			nowPage = 1;
		} else if (nowPage > totalPage) {
			nowPage = totalPage;
		}
		dto.setPage(String.valueOf(nowPage));
		
		int begin = onePageNum * (nowPage - 1) + 1;
		int end =  onePageNum * nowPage;
		dto.setBegin("" + begin);
		dto.setEnd("" + end);
		
		int pageBarNum = 10;
		int beginPage = ((nowPage-1) / pageBarNum) *  pageBarNum + 1;
		int endPage = beginPage + pageBarNum - 1;
		if (endPage > totalPage) { endPage = totalPage; }
			
		//DAO
		ArrayList<HospitalDTO> list = dao.getList(dto);
		ArrayList<String> silist = dao.getSi();
		ArrayList<String> gulist = null;
		if (!si.equals("all")) {
			gulist = dao.getGu(si);
		}
		
		req.setAttribute("list", list);
		req.setAttribute("dto", dto);
		req.setAttribute("beginPage", beginPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("silist", silist);
		req.setAttribute("gulist", gulist);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/user/diagnosis/hospitallist.jsp");

		dispatcher.forward(req, resp);
	}

}

package com.project.care.main.user.mypage.diary;

import java.io.IOException;

import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.CDiaryDTO;
import com.project.care.dto.WDiaryDTO;



@WebServlet("/cdiary/list.do")
public class ListDiary extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		CDiaryDAO dao = new CDiaryDAO();
		
		String uaseq = "1" ;//나중에 받아올 데이터  회원,동물 번호
		String type =req.getParameter("type");
		
		
		ArrayList<CDiaryDTO> clist = dao.clist(uaseq); //케어일기 리스트
		ArrayList<WDiaryDTO> wlist = dao.wlist(uaseq); //산책일기 리스트
		ArrayList<WDiaryDTO> dlist = dao.dlist(uaseq); //해당반려동물 프로필 리스트
		int count = dao.plist(uaseq); //처방전이 여러개 존재할경우, 한개 존재할경우 나눠서 생각하기
		ArrayList<CDiaryDTO> pslist = dao.pslist(uaseq);  //진료확인서번호, 반려동물번호
		
			
		req.setAttribute("clist", clist);
		req.setAttribute("wlist", wlist);
		req.setAttribute("dlist", dlist);
		req.setAttribute("count", count);
		req.setAttribute("pslist", pslist);
		req.setAttribute("type", type);
		
		
	
		String cdiaryPath = "/WEB-INF/views/user/mypage/cdiary";

		RequestDispatcher dispatcher = req.getRequestDispatcher(cdiaryPath+"/list.jsp");
		
		dispatcher.forward(req, resp);

	}
}
package com.project.care.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.care.dto.CompanyDTO;

@WebServlet("/main/companyregister.do")
public class CompanyRegister extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/companyregister.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 인코딩하기
		req.setCharacterEncoding("UTF-8");
		
		
		//데이터 받아오기
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String business = req.getParameter("business");
		String tel1 = req.getParameter("tel1");
		String tel2 = req.getParameter("tel2");
		String tel3 = req.getParameter("tel3");
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String address = req.getParameter("address");
		String xcoor = req.getParameter("xcoor");
		String ycoor = req.getParameter("ycoor");
		
		CompanyDTO dto = new CompanyDTO();
		
		dto.setId(id);
		dto.setPassword(pw);
		dto.setName(name);
		dto.setBusiness(business);
		//전화번호 -> 가공
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		dto.setTel(tel);
		dto.setAddress(address);
		//이메일 -> 가공
		String email = email1 + "@" + email2;
		dto.setEmail(email);
		dto.setXcoor(Double.parseDouble(xcoor));
		dto.setYcoor(Double.parseDouble(ycoor));
		
		
		CompanyDAO dao = new CompanyDAO();
		dao.addId(dto);
		
		int result = dao.companyRegister(dto);
		
		System.out.println(result);
		
		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;");

        PrintWriter writer = resp.getWriter();
		
		if(result == 1) {
			resp.sendRedirect("/fanimal/main/companylogin.do");
		} else {
			writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('회원가입 실패')");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("<html>");
            writer.close();
		}
	}
}

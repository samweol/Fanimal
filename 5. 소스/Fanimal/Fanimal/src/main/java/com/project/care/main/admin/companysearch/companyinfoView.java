package com.project.care.main.admin.companysearch;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.CompanyDTO;
import com.project.care.dto.companyviewDTO;

@WebServlet("/admin/companyinfoview.do")
public class companyinfoView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		companysearchDAO dao = new companysearchDAO();
		
		companyviewDTO cvdto = dao.cget(seq);

		CompanyDTO cdto = dao.get(seq);
		
		req.setAttribute("cvdto", cvdto);
		req.setAttribute("cdto", cdto);
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/companyinfoview.jsp");

		dispatcher.forward(req, resp);            

	}
}

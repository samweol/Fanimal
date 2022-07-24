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


@WebServlet("/admin/companyinfolist.do")
public class CompanyInfoList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		companysearchDAO dao = new companysearchDAO();
		
		ArrayList<CompanyDTO> list = dao.list();
		
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/companyinfolist.jsp");

		dispatcher.forward(req, resp);

	}
}
package com.project.care.main.company.enrollment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/company/enrollment.do")
public class Enrollment extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse  resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/enrollment.jsp");

		dispatcher.forward(req, resp);

	}
}
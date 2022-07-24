package com.project.care.main.company.mypage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.main.CompanyDAO;

@WebServlet("/company/cancelenrollment.do")
public class CancelEnrollment extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		
		CompanyDAO dao = new CompanyDAO();
		
		//hpseq 찾기
		int hpseq = dao.getHpseq(id);
		
		System.out.println("hpseq : " + hpseq);
		
		//수의사
		dao.deleteDoctor(hpseq);
		
		//병원운영요일
		dao.deleteHosDate(hpseq);
		
		
		//hospital 지우기
		int result = dao.cancelEnrollment(hpseq);
		
		
		
		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        if (result == 1) {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('승인이 취소되었습니다.');");
            writer.println("location.href='/fanimal/company/companypage.do'");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");
        } else {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('승인 취소가 거절되었습니다.');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");
        }
	}
}

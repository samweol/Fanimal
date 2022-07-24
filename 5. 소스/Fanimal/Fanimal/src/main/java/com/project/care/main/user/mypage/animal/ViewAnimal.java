package com.project.care.main.user.mypage.animal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.AniViewDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/user/mypage/viewanimal.do")
public class ViewAnimal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		UserDTO dto = (UserDTO) session.getAttribute("auth");
		
		if (session.getAttribute("auth") == null) {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();

            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('회원만 접근 가능합니다.');");
            writer.println("location.href = '/fanimal/main/userlogin.do';");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

            writer.close();
            return;
        }
		

		req.setCharacterEncoding("UTF-8");
		
		String uaseq = req.getParameter("uaseq");
		
		//System.out.println(uaseq);
		
		AniViewDTO viewdto = new AniViewDTO();
		
		AnimalDAO dao = new AnimalDAO();
		
		viewdto = dao.aniview(uaseq);
		
		
		
		req.setAttribute("viewdto", viewdto);
		req.setAttribute("uaseq", uaseq);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/viewanimal.jsp");

		dispatcher.forward(req, resp);

	}
	
	/*
	 * @Override protected void doPost(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException {
	 * 
	 * 
	 * 
	 * resp.sendRedirect("/WEB-INF/views/user/mypage/viewanimal.jsp");
	 * 
	 * }
	 */

}
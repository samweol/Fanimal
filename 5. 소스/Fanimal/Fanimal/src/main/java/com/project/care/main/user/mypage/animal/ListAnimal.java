package com.project.care.main.user.mypage.animal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.AnimalDTO;
import com.project.care.dto.AnimalListDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/user/mypage/listanimal.do")
public class ListAnimal extends HttpServlet {

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
		
		
		AnimalDTO anidto = new AnimalDTO();
		
		AnimalDAO dao = new AnimalDAO();
		
		ArrayList<AnimalListDTO> alist = new ArrayList<AnimalListDTO>();
		
		
		alist = dao.anilist(dto.getId());
		
		
		
		req.setAttribute("alist", alist);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/listanimal.jsp");

		dispatcher.forward(req, resp);

	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
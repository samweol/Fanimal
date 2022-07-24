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
import com.project.care.dto.UserDTO;

@WebServlet("/main/userregister.do")
public class UserRegister extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/userregister.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 인코딩하기
		req.setCharacterEncoding("UTF-8");
		
		int size = 1024 * 1024 * 100;
		String path = req.getRealPath("/asset/images/user");
		//System.out.println(path); 
		//C:\class\server\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Fanimal\asset\images\\user	
		
		MultipartRequest multi = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy());
		
		//데이터 받아오기
		String id = multi.getParameter("id");
		String pw = multi.getParameter("pw");
		String name = multi.getParameter("name");
		String nickname = multi.getParameter("nickname");
		String tel1 = multi.getParameter("tel1");
		String tel2 = multi.getParameter("tel2");
		String tel3 = multi.getParameter("tel3");
		String address = multi.getParameter("address");
		String xcoor = multi.getParameter("xcoor");
		String ycoor = multi.getParameter("ycoor");
		String birth = multi.getParameter("birth");
		String img = multi.getFilesystemName("img");
		
		//System.out.println(address); //서울 강남구 가로수길 5
		//System.out.println(birth); //2022-07-26
		
		UserDTO dto = new UserDTO();
		
		dto.setId(id);
		dto.setPassword(pw);
		dto.setName(name);
		dto.setNickname(nickname);
		//전화번호 -> 가공
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		dto.setTel(tel);
		dto.setAddress(address);
		
		dto.setXcoor(Double.parseDouble(xcoor));
		dto.setYcoor(Double.parseDouble(ycoor));
		dto.setBirth(birth);
		dto.setPicture(img);
		//System.out.println(img); null
		//System.out.println(dto);
		//UserDTO(id=jooye, name=서주예, nickname=주예, password=jooye1234, tel=010-1111-1111, address=서울 마포구 독막로3길 13, joindate=null, xcoor=37.5490138703904, ycoor=126.916590603184, birth=2022-07-27, picture=null)
		
		UserDAO dao = new UserDAO();
		dao.addId(dto); //tblId에 넣기
		int result = dao.userRegister(dto);
		//System.out.println(result);
		
		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;");

        PrintWriter writer = resp.getWriter();
		
		if(result == 1) {
			resp.sendRedirect("/fanimal/main/userlogin.do");
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

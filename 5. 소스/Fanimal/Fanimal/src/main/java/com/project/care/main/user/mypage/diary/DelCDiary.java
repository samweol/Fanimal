package com.project.care.main.user.mypage.diary;

import java.io.File;
import java.io.IOException;




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.CDiaryDTO;



@WebServlet("/cdiary/del.do")
public class DelCDiary extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String cdiaryPath = "/WEB-INF/views/user/mypage/cdiary";
		
		String cdSeq = req.getParameter("cdseq");
		int result =0;
		
		
			CDiaryDAO dao = new CDiaryDAO();
		
			CDiaryDTO dto = dao.viewCDairy(cdSeq);
			
			
			//원글에 첨부파일이 있다면 
			if(dto.getPicture() != null){
				String path = req.getRealPath("files")+"\\"+dto.getPicture();
				File file = new File(path);
				file.delete();
				System.out.println("실패1");
			}
			
			result = dao.del(cdSeq);
			
			System.out.println(result);
		
	
		
		
		//4.
		req.setAttribute("result", result);	
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(cdiaryPath+"/del.jsp");
		dispatcher.forward(req, resp);

	}
}

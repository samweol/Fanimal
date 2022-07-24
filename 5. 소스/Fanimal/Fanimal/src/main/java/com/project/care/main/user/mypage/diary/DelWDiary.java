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
import com.project.care.dto.WDiaryDTO;



@WebServlet("/wdiary/del.do")
public class DelWDiary extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String cdiaryPath = "/WEB-INF/views/user/mypage/cdiary";
		
	
		String wseq = req.getParameter("wseq");
		int result =0;
		
		
			WDiaryDAO dao = new WDiaryDAO();
		
			WDiaryDTO dto = dao.viewCDairy(wseq);
			
			//원글에 첨부파일이 있다면 
			if(dto.getPic() != null){
				String path = req.getRealPath("files")+"\\"+dto.getPic();
				File file = new File(path);
				file.delete();
				System.out.println("실패1");
			}
			
			dao.delinfo(wseq); //자식테이블 부터 삭제해줘야함
			result = dao.del(wseq);
			
	

			System.out.println(result);
			
			//4.
			req.setAttribute("result", result);	
			
			
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(cdiaryPath+"/del.jsp");
			dispatcher.forward(req, resp);

	}
}

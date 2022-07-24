package com.project.care.main.user.mypage.animal;

import java.io.File;
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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.care.dto.AniTypeDTO;
import com.project.care.dto.AniViewDTO;
import com.project.care.dto.DisDTO;
import com.project.care.dto.EditAniViewDTO;
import com.project.care.dto.EditAnimalDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/user/mypage/editanimal.do")
public class EditAnimal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		HttpSession session = req.getSession();
		
		UserDTO idto = (UserDTO) session.getAttribute("auth");
		
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
		
		
		
		
		
		
		String uaseq = req.getParameter("uaseq");
		
		AnimalDAO dao = new AnimalDAO();
		
		ArrayList<AniTypeDTO> typelist = new ArrayList<AniTypeDTO>();
		ArrayList<DisDTO> dislist = new ArrayList<DisDTO>();
		
		
		typelist = dao.anitype();
		//kdto = dao.anikind();
		
		dislist = dao.dislist();
				
		
				
		
		EditAnimalDTO dto = new EditAnimalDTO();
		
		//AnimalDAO dao = new AnimalDAO();
		
		dto = dao.editlist(uaseq);
		
		
		
		req.setAttribute("typelist", typelist);
		req.setAttribute("dislist", dislist);
		req.setAttribute("uaseq", uaseq);
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/editanimal.jsp");

		dispatcher.forward(req, resp);

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
			
		
		req.setCharacterEncoding("UTF-8");
		
		
		String path = req.getRealPath("/files");
		int size = 1024 * 1024 * 100;
		
		
		//C:\class\server\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Fanimal\files
		//System.out.println(path);
		
		MultipartRequest multi = null;
		
		try {
			
			multi = new MultipartRequest(
										req,
										path,
										size,
										"UTF-8",
										new DefaultFileRenamePolicy()
										);
			
		} catch (Exception e) {
			System.out.println("AddAnimal.doPost");
			e.printStackTrace();
		}
		
		String name = multi.getParameter("name");
//		String type = multi.getParameter("type"); //
//		String kind = multi.getParameter("kind"); //품종
		String gender = multi.getParameter("gender"); //성별
		String age = multi.getParameter("age"); //나이
		
		String yyyy = multi.getParameter("yyyy"); //년월일
		String mm = multi.getParameter("mm");
		String dd = multi.getParameter("dd");		
		String neuter = multi.getParameter("neuter"); //중성화
		String weight = multi.getParameter("weight"); //몸무게
		String disease = multi.getParameter("disease"); //질병
		String die = multi.getParameter("die"); //사망여부
		
		
		//파일명
		String filename = multi.getFilesystemName("attach");
		String orgfilename = multi.getOriginalFileName("attach");
		
		String uaseq = multi.getParameter("uaseq");
		
		 System.out.println(name); //System.out.println(type); System.out.println(kind);
		 System.out.println(gender); System.out.println(age);
		 System.out.println(yyyy); System.out.println(mm); System.out.println(dd);
		 System.out.println(neuter); System.out.println(weight);
		 System.out.println(disease); System.out.println(die);
		 System.out.println(filename); System.out.println(orgfilename);
		
		 System.out.println(uaseq);
		 
		 String birth = String.format("%s-%s-%s", yyyy, mm, dd);
		 
		 
		 AnimalDAO dao = new AnimalDAO();
		  
		 String fname = dao.picname(uaseq);
		  
		 
		 int nofresult = 0; //파일 첨부 x
		 int nofresult2 = 0; //파일 첨부 x
		 int nofresult3 = 0; //파일 첨부 x
		 
		 int picresult = 0; //기존파일이 기본 파일이면 그냥 파일추가&업데이트
		 int picresult2 = 0;
		 int picresult3 = 0;
		 
		 int delresult = 0; //파일 지우고 추가
		 int delresult2 = 0; //파일 지우고 추가
		 int delresult3 = 0; //파일 지우고 추가
		 
		 
		 EditAniViewDTO edto = new EditAniViewDTO();
		 
		 edto.setName(name);
		 edto.setGender(gender);
		 edto.setAge(age);
		 edto.setBirth(birth);
		 edto.setNeutral(neuter);
		 edto.setWeight(weight);
		 edto.setDis(disease);
		 edto.setState(die);
		 edto.setPic(filename);
		 
		 System.out.println("fname: " + fname);
		  
		 if (filename == null) {
			  
			 //파일이 없으면 파일명 추가와 삭제 없이 그냥 업데이트
			 nofresult = dao.editAniInfo(uaseq, edto); //aniinfo
			 nofresult3 = dao.editAniDis(uaseq, edto); //anidis
			 nofresult2 = dao.editAnimal(uaseq, edto); //animal
			 
		 } else {			  
			 			 
			 if ((fname.equals("pic.png")) || (filename.equals("pic.png"))) {
				 //기존 파일이 기본파일이면 그냥 업데이트
				 System.out.println("오나?");
				 picresult = dao.editAniInfoPic(uaseq, edto); //aniinfo
				 picresult2 = dao.editAniDis(uaseq, edto); //anidis
				 picresult3 = dao.editAnimal(uaseq, edto); //animal
				 
			 } else {
				//기존 파일 삭제 후 추가
				 String picname = dao.picname(uaseq);
				 
				 File file = new File(path + "\\" + picname);
				 file.delete();				 
				 
				 delresult = dao.editAniInfoPic(uaseq, edto); //aniinfo
				 delresult2 = dao.editAniDis(uaseq, edto); //anidis
				 delresult3 = dao.editAnimal(uaseq, edto); //animal				 
				 
			 }			 
		 }
		
		 AniViewDTO viewdto = new AniViewDTO();
		 viewdto = dao.aniview(uaseq);
		 
		 
//		if (((nofresult != 0) && (nofresult3 != 0) && (nofresult3 != 0))
//				|| ((delresult != 0) && (delresult2 != 0) && (delresult3 != 0))
//				|| ((picresult != 0) && (picresult2 != 0) && (picresult3 != 0))) {
			
			resp.sendRedirect(String.format("/fanimal/user/mypage/viewanimal.do?uaseq=%s", uaseq));
			
			
//		} else {
//			//resp.sendRedirect(String.format("/fanimal/user/mypage/viewreshos.do?uaseq=%s", uaseq));
//			resp.sendRedirect("/fanimal/user/mypage/listanimal.do");
//		}
		
		
		
		
	}
	

}

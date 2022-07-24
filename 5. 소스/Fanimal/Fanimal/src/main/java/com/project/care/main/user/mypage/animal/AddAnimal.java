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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.care.dto.AniDisDTO;
import com.project.care.dto.AniInfoDTO;
import com.project.care.dto.AniKindDTO;
import com.project.care.dto.AniTypeDTO;
import com.project.care.dto.AnimalDTO;
import com.project.care.dto.DisDTO;
import com.project.care.dto.UserAniDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/user/mypage/addanimal.do")
public class AddAnimal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		HttpSession session = req.getSession();
		// 유저의 세션 쓰셔야하는 경우
		
		UserDTO dto = (UserDTO) session.getAttribute("auth");
		

		//그 후 dto.getId() 같은 메소드로 필요한 정보 받아오시면 됩니당

		//jsp에서 session값을 사용하고 싶으실때는 auth.id , autho.nickname이런식으로 받아서 쓰시면됩니당
		
		
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
		
				
		//셀렉트박스에 목록 띄워야함
		
		AniTypeDTO tdto = new AniTypeDTO(); //강아지, 고양이
		AniKindDTO kdto = new AniKindDTO(); //말티즈, 푸들
		
		
		AnimalDAO dao = new AnimalDAO();
		
		ArrayList<AniTypeDTO> typelist = new ArrayList<AniTypeDTO>();
		ArrayList<DisDTO> dislist = new ArrayList<DisDTO>();
		
		
		typelist = dao.anitype();
		//kdto = dao.anikind();
		
		dislist = dao.dislist();
				
		
		
		req.setAttribute("typelist", typelist);
		req.setAttribute("dislist", dislist);
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/addanimal.jsp");

		dispatcher.forward(req, resp);

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.세션 받기
		//2. 인코딩
		
		
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
		String type = multi.getParameter("type"); //
		String kind = multi.getParameter("kind"); //품종
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
		
		if (filename == null) {
			filename = "pic.png";
		}
		
		String birth = String.format("%s-%s-%s", yyyy, mm, dd);
		
		
		
		  //System.out.println(name); System.out.println(type); System.out.println(kind);
		 // System.out.println(gender); System.out.println(age);
		  //System.out.println(yyyy); System.out.println(mm); System.out.println(dd);
		  System.out.println(neuter); System.out.println(weight);
		  System.out.println(disease); System.out.println(die);
		  System.out.println(filename); //System.out.println(orgfilename);
		 
		
		
		//tblAnimal 필수정보 (완)
		//번호(aseq), 이름(name), 성별(gender), 나이(age), 품종번호(kseq), 생일(birth)
		
		//tblAniInfo 필수정보(완)
		//번호(aiseq), 동물번호(aseq), 사진(pic), 중성화여부(neutral), 사망여부(state), 몸무게(weight)
		
		//tblAniDis 동물&질병
		//번호(adseq), 동묿번호(aseq), 질병번호(dseq)
		
		//tblDis 질병
		//번호(dseq), 질병명(dname)
		
		//tblAniType 타입
		//번호(tseq), 동물타입(type)
		
		//tblAniKind 품종
		//번호(kseq), 타입번호(tseq), 품종명(kind)
		  
		//tblUserAni
		
		
		AnimalDAO dao = new AnimalDAO();
		  
		//tblAnimal  
		AnimalDTO anidto = new AnimalDTO();
		anidto.setName(name);
		anidto.setGender(gender);
		anidto.setAge(age);
		anidto.setKseq(kind);
		anidto.setBirth(birth);
		
		
		
		int result = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int aseq = 0;
		
		result = dao.addAnimal(anidto);
		
		aseq = dao.findaseq(); //방금 추가한 동물 번호 알아내기
		
		
		//tblAniInfo
		AniInfoDTO aniInfodto = new AniInfoDTO();
		aniInfodto.setAseq(Integer.toString(aseq));
		aniInfodto.setNeutral(neuter);
		aniInfodto.setState(die);
		aniInfodto.setWeight(weight);
		aniInfodto.setPic(filename);		
		
		result2 = dao.addAniInfo(aniInfodto);
		
		
		
		//tblAniDis
		AniDisDTO anidisdto = new AniDisDTO();
		anidisdto.setAseq(aseq);
		anidisdto.setDseq(disease);
		
		result3 = dao.addAniDis(anidisdto);
		
		
		//tblUserAni 추가
		HttpSession session = req.getSession();
		// 유저의 세션 쓰셔야하는 경우
		UserDTO dto = (UserDTO) session.getAttribute("auth");			
		
		int useq = 0;		
		useq = dao.finduseq(dto.getId()); //유저번호
		
		UserAniDTO uadto = new UserAniDTO();
		uadto.setUseq(useq);
		uadto.setAseq(aseq);
		
		result4 = dao.addua(uadto);
		
		if ((result != 0) && (result2 != 0) && (result3 != 0) && (result4 != 0)) {
			resp.sendRedirect("/fanimal/user/mypage/listanimal.do");			
		} else {
			resp.sendRedirect("/fanimal/user/mypage/addanimal.do");		
			
		}
		
		
	}

}
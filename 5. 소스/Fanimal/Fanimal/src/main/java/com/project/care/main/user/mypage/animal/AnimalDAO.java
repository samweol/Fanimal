package com.project.care.main.user.mypage.animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.DBUtil;
import com.project.care.dto.AniDisDTO;
import com.project.care.dto.AniInfoDTO;
import com.project.care.dto.AniKindDTO;
import com.project.care.dto.AniResDTO;
import com.project.care.dto.AniTypeDTO;
import com.project.care.dto.AniViewDTO;
import com.project.care.dto.AnimalDTO;
import com.project.care.dto.AnimalListDTO;
import com.project.care.dto.CalendarDTO;
import com.project.care.dto.DisDTO;
import com.project.care.dto.EditAniViewDTO;
import com.project.care.dto.EditAnimalDTO;
import com.project.care.dto.UserAniDTO;

public class AnimalDAO {

	    private Connection conn;
	    private Statement stat;
	    private PreparedStatement pstat;
	    private ResultSet rs;

	    public AnimalDAO() {
	        conn = DBUtil.open();
	    }

	    
	    
	    //동물 종류 목록
		public ArrayList<AniTypeDTO> anitype() {
			
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select * from tblAniType";
				
				stat = conn.createStatement();
				
				rs = stat.executeQuery(sql);
				
				
				
				ArrayList<AniTypeDTO> tlist = new ArrayList<AniTypeDTO>();
				
				
				while (rs.next()) {
					
					AniTypeDTO tdto = new AniTypeDTO();
					
					tdto.setTseq(Integer.parseInt(rs.getString("tseq")));
					tdto.setType(rs.getString("type"));
					
					//System.out.println(tdto.getTseq());
					//System.out.println(tdto.getType());
					
					tlist.add(tdto);
				}
								
				
				rs.close();
	            stat.close();
	            conn.close();
				
				return tlist;
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.anitype");
				e.printStackTrace();
			}
			
			
			return null;
		}



		public ArrayList<DisDTO> dislist() {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select * from tblDis";
				
				stat = conn.createStatement();
				
				rs = stat.executeQuery(sql);
				
				
				
				ArrayList<DisDTO> dlist = new ArrayList<DisDTO>();
				
				
				while (rs.next()) {
					
					DisDTO ddto = new DisDTO();
					
					ddto.setDseq(Integer.parseInt(rs.getString("dseq")));
					ddto.setDname(rs.getString("dname"));
					
					//System.out.println(tdto.getTseq());
					//System.out.println(tdto.getType());
					
					dlist.add(ddto);
				}
								
				
				rs.close();
	            stat.close();
	            conn.close();
				
				return dlist;
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.anitype");
				e.printStackTrace();
			}
			
			return null;
		}


		
		//동물 해당 타입의 품종 정보
		public ArrayList<AniKindDTO> anikind(String tseq) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select * from tblAniKind where tseq = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, tseq);
				rs = pstat.executeQuery();				
				
				
				ArrayList<AniKindDTO> klist = new ArrayList<AniKindDTO>();
				
				
				while (rs.next()) {
					
					AniKindDTO kdto = new AniKindDTO();
					
					kdto.setKseq(Integer.parseInt(rs.getString("kseq")));
					kdto.setTseq(Integer.parseInt(rs.getString("tseq")));
					kdto.setKind(rs.getString("kind"));
					
					//System.out.println(tdto.getTseq());
					//System.out.println(tdto.getType());
					
					klist.add(kdto);
				}
								
				
				rs.close();
	            pstat.close();
	            conn.close();
				
				return klist;
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.anitype");
				e.printStackTrace();
			}
			
			
			
			return null;
		}



		
		//동물 추가
		public int addAnimal(AnimalDTO anidto) {
			//번호(aseq), 이름(name), 성별(gender), 나이(age), 품종번호(kseq), 생일(birth)
			
			int result;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "insert into tblAnimal values ((select max(aseq) from tblAnimal) + 1, ?, ?, ?, ?, ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, anidto.getName());
				pstat.setString(2, anidto.getGender());
				pstat.setString(3, anidto.getAge());
				pstat.setString(4, anidto.getKseq());
				pstat.setString(5, anidto.getBirth());
				
				result = pstat.executeUpdate();
								
				
	            pstat.close();
	            conn.close();
	            
				
				return result;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.addAnimal");
				e.printStackTrace();
			}
			
			
			
			return 0;
		}


		
		//방금 추가한 동물
		public int findaseq() {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select max(aseq) as aseq from tblAnimal";
				
				stat = conn.createStatement();
				
				rs = stat.executeQuery(sql);
				
				int aseq = 0;
				
				if (rs.next()) {
					aseq = Integer.parseInt(rs.getString("aseq"));									
				}
				
				return aseq;
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.findaseq");
				e.printStackTrace();
			}
			
			return 0;
		}


		
		
		//반려동물 정보에도 추가
		public int addAniInfo(AniInfoDTO aniInfodto) {
			//번호(aiseq), 동물번호(aseq), 사진(pic), 중성화여부(neutral), 사망여부(state), 몸무게(weight)
			
			int result;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "insert into tblAniInfo values ((select max(aiseq) as aiseq from tblAniInfo) + 1, ?, ?, ?, ?, ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, aniInfodto.getAseq());
				pstat.setString(2, aniInfodto.getPic());
				pstat.setString(3, aniInfodto.getNeutral());
				pstat.setString(4, aniInfodto.getState());
				pstat.setString(5, aniInfodto.getWeight());
				
				result = pstat.executeUpdate();
								
				
				
	            pstat.close();
	            conn.close();
	            
				
				return result;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.addAniInfo");
				e.printStackTrace();
			}
			
			
			
			return 0;
		}


		
		//동물&질병 추가
		public int addAniDis(AniDisDTO anidisdto) {
			
			int result;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "insert into tblAniDis values ((select max(adseq) as adseq from tblAniDis) + 1, ?, ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, Integer.toString(anidisdto.getAseq()));
				pstat.setString(2, anidisdto.getDseq());

				
				result = pstat.executeUpdate();
								
				
	            pstat.close();
	            conn.close();
	            
				
				return result;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.addAniDis");
				e.printStackTrace();
			}
			
			return 0;
		}



		public int finduseq(String id) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select useq from tblUser where id = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, id);
				
				rs = pstat.executeQuery();
				
				int useq = 0;
				
				if (rs.next()) {
					useq = Integer.parseInt(rs.getString("useq"));
				}
				
				rs.close();
				pstat.close();
				conn.close();
				
				return useq;
								
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.finduseq");
				e.printStackTrace();
			}
			
			return 0;
		}


		
		//tblUseqAni 유저&동물 테이블에 추가
		public int addua(UserAniDTO uadto) {
			
			int result = 0;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "insert into tblUserAni values ((select max(uaseq) as uaseq from tblUserAni) + 1, ?, ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, Integer.toString(uadto.getUseq()));
				pstat.setString(2, Integer.toString(uadto.getAseq()));

				
				result = pstat.executeUpdate();
								
				
	            pstat.close();
	            conn.close();
	            
				
				return result;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.addua");
				e.printStackTrace();
			}
			
			return 0;
		}


		
		
		//ListAnimal.java > 반려동물 리스트
		public ArrayList<AnimalListDTO> anilist(String id) {
			
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select a.name, (select kind from tblAniKind k where a.kseq = k.kseq) as kind, a.gender, a.age, (select name from tblUser u where u.useq = ua.useq) as uname, (select pic from tblAniInfo ai where ai.aseq = a.aseq) as pic, ua.uaseq from tblUserAni ua inner join tblAnimal a on ua.aseq = a.aseq where ua.useq = (select useq from tblUser where id = ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, id);
				
				rs = pstat.executeQuery();
				
				ArrayList<AnimalListDTO> alist = new ArrayList<AnimalListDTO>();
				
				while (rs.next()) {
					
					AnimalListDTO dto = new AnimalListDTO();
					
					dto.setName(rs.getString("name"));
					dto.setKind(rs.getString("kind"));
					dto.setGender(rs.getString("gender"));
					dto.setAge(rs.getString("age"));
					dto.setUname(rs.getString("uname"));
					dto.setPic(rs.getString("pic"));
					dto.setUaseq(rs.getString("uaseq"));
					
					alist.add(dto);
					
				}
									
					
				rs.close();
	            pstat.close();
	            conn.close();
				
				return alist;
				
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.anilist");
				e.printStackTrace();
			}
			
			return null;
		}


		
		//ViewAnimal.java > 동물 상세보기
		public AniViewDTO aniview(String uaseq) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select a.name, at.type, ak.kind, a.gender, a.age, a.birth, ai.neutral, d.dname, ai.weight, ai.state, ai.pic from tblUserAni ua inner join tblAnimal a on ua.aseq = a.aseq inner join tblAniDis ad on a.aseq = ad.aseq inner join tblAniInfo ai on ai.aseq = a.aseq inner join tblAniKind ak on ak.kseq = a.kseq inner join tblAniType at on ak.tseq = at.tseq inner join tblDis d on d.dseq = ad.dseq where ua.uaseq = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, uaseq);
				
				rs = pstat.executeQuery();
				
				AniViewDTO dto = new AniViewDTO();
				
				if (rs.next()) {
					
					
					//이름 분류 품종 성별 나이 생일 중성화 질병명 몸무게 사망 사진
					dto.setName(rs.getString("name"));
					dto.setType(rs.getString("type"));
					dto.setKind(rs.getString("kind"));
					dto.setGender(rs.getString("gender"));
					dto.setAge(rs.getString("age"));
					dto.setBirth(rs.getString("birth").substring(0,10));
					dto.setNeutral(rs.getString("neutral"));
					dto.setDname(rs.getString("dname"));
					dto.setWeight(rs.getString("weight"));
					dto.setState(rs.getString("state"));
					dto.setPic(rs.getString("pic"));
															
				}
				
				rs.close();
				pstat.close();
				conn.close();
				
				return dto;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.aniview");
				e.printStackTrace();
			}
			
			
			return null;
		}


		
		//uaseq로 동물번호 알아내기
		public String findaniaseq(String uaseq) {
			
				try {
				
				conn = DBUtil.open();
				
				String sql = "select aseq from tblUserAni where uaseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, uaseq);
				
				
				rs = pstat.executeQuery();
				
				String aseq = null;
				
				if (rs.next()) {
					aseq = rs.getString("aseq");									
				}
				
				return aseq;
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.findaseq");
				e.printStackTrace();
			}
			
			return null;
		}


		
		//동물 정보 삭제
		public int delinfo(String aseq) {
			
			int result = 0;			
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "delete from tblAniInfo where aseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, aseq);
				
				result = pstat.executeUpdate();			
				
				pstat.close();
				conn.close();
				
				return result;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.delinfo");
				e.printStackTrace();
			}			
			
			return 0;
		}


		//동물 질병 삭제
		public int deldis(String aseq) {
			
			int result = 0;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "delete from tblAniDis where aseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, aseq);
				
				result = pstat.executeUpdate();			
				
				pstat.close();
				conn.close();
				
				return result;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.deldis");
				e.printStackTrace();
			}		
			
			return 0;
		}


		//동물 케어일기 삭제
		public int delcdi(String aseq) {
			
			int result = 0;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "delete from tblCDiary where aseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, aseq);
				
				result = pstat.executeUpdate();			
				
				pstat.close();
				conn.close();
				
				return result;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.delcdi");
				e.printStackTrace();
			}
			
			return 0;
		}


		//유저&동물 삭제
		public int delua(String aseq) {

			int result = 0;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "delete from tblUserAni where aseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, aseq);
				
				result = pstat.executeUpdate();			
				
				pstat.close();
				conn.close();
				
				return result;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.delua");
				e.printStackTrace();
			}
			
			return 0;
		}


		//동물 삭제
		public int delani(String aseq) {
			
			int result = 0;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "delete from tblAnimal where aseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, aseq);
				
				result = pstat.executeUpdate();			
				
				pstat.close();
				conn.close();
				
				return result;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.delani");
				e.printStackTrace();
			}
			
			return 0;
		}


		
		//ResHos.java > 병원 예약 리스트
		public ArrayList<AniResDTO> reslist(String id) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select a.name, rh.rhseq, (select hosname from tblHospital h where h.hpseq = rh.hpseq) as hpname, rh.resdate from tblUserAni ua inner join tblAnimal a on ua.aseq = a.aseq inner join tblResHos rh on ua.uaseq = rh.uaseq where useq = (select useq from tblUser where id = ?) and rh.resdate > sysdate order by resdate asc";
						
				pstat = conn.prepareStatement(sql);				
				pstat.setString(1, id);
				
				rs = pstat.executeQuery();
				
				ArrayList<AniResDTO> rlist = new ArrayList<AniResDTO>();
				
				while (rs.next()) {
					
					AniResDTO dto = new AniResDTO();
					dto.setRhseq(rs.getString("rhseq"));
					dto.setName(rs.getString("name"));
					dto.setResdate(rs.getString("resdate").substring(0, 10));
					dto.setHpname(rs.getString("hpname"));
					
					rlist.add(dto);					
				}
				
				rs.close();
				pstat.close();
				conn.close();
				
				return rlist;
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.reslist");
				e.printStackTrace();
			}
			
			return null;
		}



		public ArrayList<AniResDTO> beflist(String id, int begin, int end) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql ="select * from (select vw.*, rownum as rnum from vwbefres vw where useq = (select useq from tblUser where id = ?) and resdate < sysdate) where rnum between ? and ?";
				
				pstat = conn.prepareStatement(sql);				
				pstat.setString(1, id);
				pstat.setInt(2, begin);
				pstat.setInt(3, end);
				
				rs = pstat.executeQuery();
				
				ArrayList<AniResDTO> beflist = new ArrayList<AniResDTO>();
				
				while (rs.next()) {
					
					AniResDTO dto = new AniResDTO();
					dto.setRhseq(rs.getString("rhseq"));
					dto.setName(rs.getString("name"));
					dto.setResdate(rs.getString("resdate").substring(0, 10));
					dto.setHpname(rs.getString("hpname"));
					dto.setUniqueness(rs.getString("uniqueness"));
					
					
					beflist.add(dto);					
				}
				
				rs.close();
				pstat.close();
				conn.close();
				
				return beflist;
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.beflist");
				e.printStackTrace();
			}
			
			return null;
		}



		//총 지난 예약수
		public int getTotalCount(String id) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select count(*) as cnt from tblUserAni ua inner join tblAnimal a on ua.aseq = a.aseq inner join tblResHos rh on ua.uaseq = rh.uaseq where useq = (select useq from tblUser where id = ?) and rh.resdate < sysdate";
				
				pstat = conn.prepareStatement(sql);				
				pstat.setString(1, id);
				
				rs = pstat.executeQuery();
				
				int cnt = 0;				
				
				if(rs.next()) {
					
									
					cnt = Integer.parseInt(rs.getString("cnt"));
				}
				
				rs.close();
				pstat.close();
				conn.close();
				
				return cnt;
				
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.getTotalCount");
				e.printStackTrace();
			}
			return 0;
		}


		//예약 상세보기
		public AniResDTO viewres(String rhseq) {
			
			try {
				
				conn = DBUtil.open();
				System.out.println("rhseq:" + rhseq);
				String sql = "select a.name, rh.resdate, h.hosname, p.purpose, rh.uniqueness, rh.picture from tblResHos rh inner join tblUserAni ua on rh.uaseq = ua.uaseq inner join tblAnimal a on ua.aseq = a.aseq inner join tblHospital h on rh.hpseq = h.hpseq inner join tblPurpose p on rh.pseq = p.pseq inner join tblAniInfo ai on ai.aseq = a.aseq where rh.rhseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, rhseq);
				
				rs = pstat.executeQuery();
				
				AniResDTO dto = new AniResDTO();
				System.out.println(3);
				
				if (rs.next()) {
					
					System.out.println(2);
					dto.setName(rs.getString("name"));
					dto.setResdate(rs.getString("resdate").substring(0, 16));
					dto.setHosname(rs.getString("hosname"));
					dto.setPurpose(rs.getString("purpose"));
					dto.setUniqueness(rs.getString("uniqueness"));
					dto.setPic(rs.getString("picture"));
					
				}
				
				return dto;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.viewres");
				e.printStackTrace();
			}
			
			return null;
		}


		
		//병원 예약 삭제
		public int delreshos(String rhseq) {
			
			int result = 0;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "delete from tblResHos where rhseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, rhseq);
				
				
				result = pstat.executeUpdate();
				
				pstat.close();
				conn.close();		
				
				return result;
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.delreshos");
				e.printStackTrace();
			}
			
			return 0;
		}


		
		//일정 리스트		
		public ArrayList<CalendarDTO> callist(String id) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select memo, datetime, end from tblSche where id = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, id);
				
				rs = pstat.executeQuery();
				
				ArrayList<CalendarDTO> clist = new ArrayList<CalendarDTO>();
				
				
				while (rs.next()) {
				
					CalendarDTO cdto = new CalendarDTO();
					// memo datetime(start) end(end) / id가 홍길동인
					cdto.setMemo(rs.getString("memo"));
					cdto.setDatetime(rs.getString("datetime").substring(0,10));
					
					if (rs.getString("end") == null) {
						cdto.setEnd(rs.getString("datetime").substring(0,10));
					} else {
						cdto.setEnd(rs.getString("end").substring(0,10));
					}
					
					clist.add(cdto);
				}
				
				return clist;
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.callist");
				e.printStackTrace();
			}
			
			return null;
		}



		public ArrayList<CalendarDTO> reshoslist(String useq) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select hp.hosname, rh.resdate from tblResHos rh inner join tblUserAni ua on rh.uaseq = ua.uaseq inner join tblhospital hp on hp.hpseq = rh.hpseq where ua.useq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, useq);
				
				rs = pstat.executeQuery();
				
				ArrayList<CalendarDTO> rlist = new ArrayList<CalendarDTO>();
				
				
				while (rs.next()) {
					
					CalendarDTO rdto = new CalendarDTO();
					rdto.setResdate(rs.getString("resdate").substring(0,10));
					rdto.setHname(rs.getString("hosname"));
					
					rlist.add(rdto);
				}
				
				return rlist;
				
				//CalendarDTO rdto = new CalendarDTO();
				
				//rdto.
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.reshoslist");
				e.printStackTrace();
			}
			
			return null;
		}



		
		//일정 추가
		public int addCal(String day, String memo, String start, String id) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "insert into tblSche values(seqSche.nextVal, ?,?,?,1,?,1)";
				// 메모 시작날짜 아이디 1 종료날짜 1
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, memo);
				pstat.setString(2, start);
				pstat.setString(3, id);				
				pstat.setString(4, day);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.addCal");
				e.printStackTrace();
			}
			
			return 0;
		}



		
		public EditAnimalDTO editlist(String uaseq) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select a.name, ak.tseq, ak.kind, a.gender, a.age, a.birth, ai.neutral, ai.weight, ad.dseq, ai.state, ai.pic, at.type from tblAnimal a inner join tblAniInfo ai on a.aseq = ai.aseq inner join tblUserAni ua on a.aseq = ua.aseq inner join tblAniKind ak on a.kseq = ak.kseq inner join tblAniType at on ak.tseq = at.tseq inner join tblAniDis ad on a.aseq = ad.aseq inner join tblAniType at on ak.tseq = at.tseq where ua.uaseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, uaseq);
				
				rs = pstat.executeQuery();
					
				EditAnimalDTO dto = new EditAnimalDTO();
				
				if (rs.next()) {
					
					dto.setName(rs.getString("name"));
					dto.setTseq(rs.getString("tseq"));
					dto.setKind(rs.getString("kind"));
					dto.setGender(rs.getString("gender"));					
					dto.setAge(rs.getString("age"));
					dto.setYy(rs.getString("birth").substring(0, 4));
					dto.setMm(rs.getString("birth").substring(5, 7));
					dto.setDd(rs.getString("birth").substring(8, 10));
					dto.setNeutral(rs.getString("neutral"));
					dto.setWeight(rs.getString("weight"));
					dto.setDseq(rs.getString("dseq"));
					dto.setState(rs.getString("state"));
					dto.setPic(rs.getString("pic"));
					dto.setType(rs.getString("type"));
					
				}
				
				return dto;
				
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.editlist");
				e.printStackTrace();
			}
			
			return null;
		}


		
		//현재 동물의 프로필 사진 이름 가져오기
		public String picname(String uaseq) {
			
			String fname = null;
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "select ai.pic from tblAnimal a inner join tblAniInfo ai on a.aseq = ai.aseq inner join tblUserAni ua on ua.aseq = a.aseq where ua.uaseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, uaseq);
				
				rs = pstat.executeQuery();
				
				if (rs.next()) {
					
					fname = rs.getString("pic");
					
				}
				
				return fname;
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.picname");
				e.printStackTrace();
			}
			
			
			return null;
		}



		
		public int editAniInfo(String uaseq, EditAniViewDTO edto) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "update tblAniInfo set neutral = ?, state = ?, weight = ? where aseq = (select aseq from tblUserAni ua where ua.uaseq = ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, edto.getNeutral());
				pstat.setString(2, edto.getState());
				pstat.setString(3, edto.getWeight());
				pstat.setString(4, uaseq);
				
				return pstat.executeUpdate();
				
						
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.editAniInfo");
				e.printStackTrace();
			}
			
			
			return 0;
		}



		public int editAniDis(String uaseq, EditAniViewDTO edto) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "update tblAniDis set dseq = ? where aseq = (select aseq from tblUserAni ua where ua.uaseq = ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, edto.getDis());
				pstat.setString(2, uaseq);
				
				return pstat.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.editAniDis");
				e.printStackTrace();
			}
			
			return 0;
		}



		public int editAnimal(String uaseq, EditAniViewDTO edto) {
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "update tblAnimal set name = ?, gender = ?, age = ?, birth = ? where aseq = (select aseq from tblUserAni ua where ua.uaseq = ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, edto.getName());
				pstat.setString(2, edto.getGender());
				pstat.setString(3, edto.getAge());
				pstat.setString(4, edto.getBirth());
				pstat.setString(5, uaseq);
				
				return pstat.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.editAnimal");
				e.printStackTrace();
			}
			
			
			return 0;
		}



		public int editAniInfoPic(String uaseq, EditAniViewDTO edto) {
			
			
			try {
				
				conn = DBUtil.open();
				
				String sql = "update tblAniInfo set neutral = ?, state = ?, weight = ?, pic = ? where aseq = (select aseq from tblUserAni ua where ua.uaseq = ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, edto.getNeutral());
				pstat.setString(2, edto.getState());
				pstat.setString(3, edto.getWeight());
				pstat.setString(4, edto.getPic());
				pstat.setString(5, uaseq);
				
				return pstat.executeUpdate();
				
						
				
			} catch (Exception e) {
				System.out.println("AnimalDAO.editAniInfo");
				e.printStackTrace();
			}
			
			
			return 0;
		}

		
		
}

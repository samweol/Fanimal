<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.pure-menu-link {
	    color: black;
	}
</style>


    <header>
            
            <div id="login" style="margin-top: 10px; margin-right: 8px;">
                <div id="profile"><img src="/fanimal/asset/images/user/${auth.picture}" style="width: 50px; height: 50px; border-radius: 50%"></div>
                <div id="userinfo" style="padding-top: 5px;">
                    <div>
                        <span>${auth.nickname }님</span>
                    </div>
                    <div>
                        <span><a href="/fanimal/user/usermypage.do" style="color: gray; text-decoration: none;">마이페이지</a></span>
                        <span>|</span>
                        <span><a href="/fanimal/main/logout.do" style="color: gray; text-decoration: none;">로그아웃</a></span>
                    </div>
                </div>
            </div>
            <div id="mainlogo" style = "cursor: pointer;" onclick = "location.href='/fanimal/user/mypage/listanimal.do';">
            </div>
            <div>
                
                <nav class="pure-menu pure-menu-horizontal">
	         <div id="mainmenu">
	             <ul class="pure-menu-list">
	             <li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
                    <a href="/fanimal/user/mypage/listanimal.do" id="menuLink1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 반려동물</a>
                    <ul class="pure-menu-children">
                        <li class="pure-menu-item">
                            <a href="/fanimal/user/mypage/calendar.do" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 일정표</a>
                        </li>
                        <li class="pure-menu-item">
                            <a href="/fanimal/user/mypage/reshos.do" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 예약정보 조회</a>
                        </li>
                    </ul>
                </li>
                <li class="pure-menu-item pure-menu-selected">
                    <a href="/fanimal/diagnosis/hospitallist.do" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 병원진료</a>
                </li>
                
                <li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
                    <a href="/fanimal/community/freelist.do" id="menuLink1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 커뮤니티</a>
                    <ul class="pure-menu-children">
                        <li class="pure-menu-item">
                            <a href="/fanimal/community/freelist.do?division=1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 자유게시판</a>
                        </li>
                        <li class="pure-menu-item">
                            <a href="/fanimal/community/freelist.do?division=2" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 증상게시판</a>
                        </li>
                    </ul>
                </li>
                <li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
                    <a href="#" id="menuLink1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 고객센터</a>
                    <ul class="pure-menu-children">
                        <li class="pure-menu-item">
                            <a href="#" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 공지사항</a>
                        </li>
                        <li class="pure-menu-item">
                            <a href="#" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 문의게시판</a>
                        </li>
                        <li class="pure-menu-item">
                            <a href="#" class="pure-menu-link"><i class="fa-solid fa-paw"></i> FAQ</a>
                        </li>
                    </ul>
                </li>
            </ul>
	         </div>
	     </nav>
            </div>
        </header>
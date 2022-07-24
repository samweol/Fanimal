<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@2.1.0/build/pure-min.css" integrity="sha384-yHIFVG6ClnONEA5yB5DJXfW2/KC173DIQrYoZMEtBvGzmf0PKiGyNEqe9N6BNDBH" crossorigin="anonymous">
<style>
	.pure-menu-link {
	    color: black;
	}
</style>


 <header>
            
            <div id="login" style="margin-top: 10px; margin-right: 8px;">
                <div id="profile"></div>
                <div id="userinfo" style="padding-top: 5px;">
                    <div>
                        <span>${auth.name }님</span>
                    </div>
                    <div>
                        <span><a href="/fanimal/company/companymypage.do" style="color: gray; text-decoration: none; font-size: 13px">마이페이지</a></span>
                        <span style="color: gray; text-decoration: none; font-size: 13px">|</span>
                        <span><a href="/fanimal/main/logout.do" style="color: gray; text-decoration: none; font-size: 13px">로그아웃</a></span>
                    </div>
                </div>
            </div>
            <div id="mainlogo" style = "cursor: pointer;" onclick = "location.href='/fanimal/company/companymypage.do';">
            </div>
            <div>
                
                <nav class="pure-menu pure-menu-horizontal">
	         <div id="mainmenu">
	             <ul class="pure-menu-list">
                <li class="pure-menu-item pure-menu-selected">
                    <a href="/fanimal/company/enrollment.do" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 기업등록</a>
                </li>
                <li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
                    <a href="/fanimal/company/companymypage.do" id="menuLink1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 마이페이지</a>
                    <ul class="pure-menu-children">
                        <li class="pure-menu-item">
                            <a href="/fanimal/company/companymypage.do" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 내 정보보기</a>
                        </li>
                        <li class="pure-menu-item">
                            <a href="/fanimal/company/companypage.do" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 병원 정보보기</a>
                        </li>
                        <li class="pure-menu-item">
                            <a href="/fanimal/company/reservationchart.do" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 차트보기</a>
                        </li>
                    </ul>
                </li>
                <li class="pure-menu-item pure-menu-selected">
                    <a href="/fanimal/company/reservationlist.do" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 예약내역</a>
                </li>
                <li class="pure-menu-item pure-menu-selected">
                    <a href="/fanimal/inquire/hospitalinquirelist.do?hpseq=1&si=all&gu=all&align=distance&search=늘푸른&page=1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 문의게시판</a>
                </li>
                <li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
                    <a href="/fanimal/community/freelistcp.do" id="menuLink1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 커뮤니티</a>
                    <ul class="pure-menu-children">
                        <li class="pure-menu-item">
                            <a href="/fanimal/community/freelistcp.do?division=1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 자유게시판</a>
                        </li>
                        <li class="pure-menu-item">
                            <a href="/fanimal/community/freelistcp.do?division=2" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 증상게시판</a>
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
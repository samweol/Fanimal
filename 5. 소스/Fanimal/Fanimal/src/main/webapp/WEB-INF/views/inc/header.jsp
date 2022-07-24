<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.pure-menu-link {
	    color: black;
	}
</style>    

<header>
            
	 <div id="login">
	     <input type="button" value="로그인" class="btn btn-warning" onclick="location.href='/fanimal/main/logincheck.do';">
	     <input type="button" value="회원가입" class="btn btn-secondary" onclick="location.href='/fanimal/main/registerselect.do'">
	 </div>
	 <div id="mainlogo" style = "cursor: pointer;" onclick = "location.href='/fanimal/main/index.do';">
     </div>
	 <div>
	     
	     <nav class="pure-menu pure-menu-horizontal">
	         <div id="mainmenu">
	             <ul class="pure-menu-list">
                <li class="pure-menu-item pure-menu-selected">
                    <a href="/fanimal/diagnosis/hospitallist.do" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 병원진료</a>
                </li>
                <li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
                    <a href="/fanimal/community/freelist.do" id="menuLink1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 커뮤니티</a>
                    <ul class="pure-menu-children">
                        <li class="pure-menu-item">
                            <a href="/fanimal/community/freelist.do?division=2" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 증상게시판</a>
                        </li>
                        <li class="pure-menu-item">
                            <a href="/fanimal/community/freelist.do?division=1" class="pure-menu-link"><i class="fa-solid fa-paw"></i> 자유게시판</a>
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
                    </ul>
                </li>
                <li class="pure-menu-item pure-menu-selected">
                    <a href="#" class="pure-menu-link"><i class="fa-solid fa-paw"></i> FAQ</a>
                </li>
            </ul>
	         </div>
	     </nav>
	 </div>
</header>
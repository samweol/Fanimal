<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<script src="https://kit.fontawesome.com/3ac15bdbeb.js" crossorigin="anonymous"></script>
<link rel="icon" type="image/x-icon" href="/fanimal/pic/dog.ico">
<style>
     a {
         color: black;
         text-decoration: none;
     }
     
     a:hover {
        text-decoration: none;
     }

     .btns > a > div {
         width: 200px;
         height: 200px;
         background-color: aquamarine;
         /* border: 1px solid black; */
         border-radius: 50%;

         display: flex;
         justify-content: center; 
         align-items: center;
         
         font-size: 25px;
     }
    </style>
<title>Insert title here</title>
</head>
<body>
	<main>
        <div style="display: flex; flex-direction: column; align-items: center; margin-top: 100px; margin-bottom: 80px;">
            <div id="logo"><i class="fa-solid fa-house-chimney-medical" style="font-size: 40px;"></i></div>
            <div style="font-size: 25px; margin-top: 20px; font-weight: bold;">${auth.name }</div>
            <c:if test="${cdto.stat eq '승인'}">
            <div style="font-size: 30px; margin-top: 10px; font-weight: bold;">${cdto.hosname }</div>
            </c:if>
            <div style="margin-top: 8px; "><a href="/fanimal/main/logout.do" style="color: gray;">로그아웃</a></div>
        </div>
        <div class="btns" style="display: flex; justify-content: space-between; width: 1200px; margin: 0 auto;">
            <c:if test="${empty cdto }">
            <a href="/fanimal/company/enrollment.do"><div>등록신청</div></a>
            </c:if>
            <a href="/fanimal/company/companymypage.do"><div>마이페이지</div></a>
            <a href="/fanimal/company/reservationlist.do"><div>예약내역</div></a>
            <a href="/fanimal/inquire/hospitalinquirelist.do?hpseq=1&si=all&gu=all&align=distance&search=늘푸른&page=1"><div>문의게시판</div></a>
            <a href="/fanimal/community/freelistcp.do"><div>커뮤니티</div></a>
        </div>
    </main>
</body>
</html>
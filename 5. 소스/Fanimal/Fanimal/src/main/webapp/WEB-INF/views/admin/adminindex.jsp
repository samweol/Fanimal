<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<meta charset="UTF-8">
<style>
	#homelogo {
        margin: 0 auto;
        text-align: center;
        background-image: url('/fanimal/asset/images/logo.png');
        background-size: 500px;
        background-repeat: no-repeat;
        width: 400px;
        height: 300px;
        background-position: -55px -100px;
    }
    
    .btns {
    	display: flex;
    	justify-content: center;
    	align-items: center;
    	background-color: #E0E0E0;
    	width: 400px;
    	height: 70px;
    	border-radius: 10px;
    	margin: 10px;
    	font-size: 20px;
    }
    
    a {
    	color: black;
    	text-decoration: none;
    }
    
    a:hover {
    	color: black;
    	text-decoration: none;
    }
</style>
<title>Insert title here</title>
</head>
<body>
	<div style="display: flex; flex-direction: column; align-items: center;">
		<div id="homelogo"></div>
		<a href="/fanimal/admin/userinfolist.do"><div class="btns">회원 정보 조회</div></a>
		<a href="/fanimal/admin/companyinfolist.do"><div class="btns">기업 정보 조회</div></a>
		<a ><div class="btns">커뮤니티</div></a>
		<a href="/fanimal/admin/companyapprovelist.do"><div class="btns">기업 승인 리스트</div></a>
		<a href="/fanimal/main/logout.do"><div class="btns">로그아웃</div></a>
	</div>

</body>
</html>
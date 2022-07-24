<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    #login {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 100px;
    }
    
    a {
    	color: black;
    	text-decoration: none;
    }
    
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
</style>
<link rel="icon" type="image/x-icon" href="/fanimal/pic/dog.ico">
<title>Insert title here</title>
</head>
<body>
	<main>
        <div id="login">
        	<a href="/fanimal/main/index.do"><div id="homelogo">
            
        	</div></a>
            <div style="margin-bottom: 15px; font-size: 20px; font-weight: bold;">개인 회원</div>
            <div>
            	<c:if test="${not empty loginError }">
            		<div style="margin-bottom: 5px; color: red;">일치하는 회원 정보가 없습니다.</div>
            	</c:if>
                <form action="/fanimal/main/userlogin.do" method="post" style="display: flex; flex-direction: column;">
                    <input type="text" name="id" style="margin-bottom: 5px; width: 200px;" required placeholder="ID">
                    <input type="password" name="password" style="margin-bottom: 5px; width: 200px;" required placeholder="Password"> 
                    <input type="submit" value="로그인" style="font-size: 15px; font-weight: bold; margin-bottom: 5px; width: 210px; background-color: orange; border: 1px solid orange; border-radius: 3px; cursor: pointer;">
                </form>
            </div>
            <div><span><a href="/fanimal/main/findid.do">아이디</a></span>/<span><a href="/fanimal/main/findpassword.do">비밀번호 찾기</a></span></div>
        </div>
    </main>
</body>
</html>
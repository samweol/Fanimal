<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
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
</style>
<link rel="icon" type="image/x-icon" href="/fanimal/pic/dog.ico">
<title>Insert title here</title>
</head>
<body>
	<main>
		<div id="login" style="margin-top: 100px;">
        	<a href="/fanimal/main/index.do"><div id="homelogo">         
        </div></a>
        <form action="/fanimal/main/changepassword.do" method="POST" style="display: flex; flex-direction: column; align-items: center; margin-top: 20px;">
            <div style="margin-top: 5px;">
                <span style="display: inline-block; width: 180px; background-color: orange; border-radius: 10px; text-align: center;">새로운 비밀번호</span>
                <input type="password" name="password">
            </div>
            <div style="margin-top: 5px;">
                <span style="display: inline-block; width: 180px; background-color: orange; border-radius: 10px; text-align: center;">새로운 비밀번호 확인</span>
                <input type="password" name="passwordcheck">
            </div>
            <div style="margin-top: 5px;">
                <input type="submit" value="비밀번호 재설정" style="width: 360px; background-color: orange; border: 1px solid orange; border-radius: 3px; cursor: pointer;">
            </div>
            <input type="hidden" name="id" value="${id}">
        </form>
    </main>
    
    <script>
    	<c:if test="${result == 1 && type == 'u'}">
    		alert("비밀번호가 변경되었습니다.");
    		location.href = "/fanimal/main/userlogin.do";
    	</c:if>
    	
    	<c:if test="${result == 1 && type == 'c'}">
			alert("비밀번호가 변경되었습니다.");
			location.href = "/fanimal/main/companylogin.do";
		</c:if>
		
		<c:if test="${not empty error}">
			alert("비밀번호가 일치하지 않습니다.");
			history.back();
		</c:if>
    </script>
</body>
</html>
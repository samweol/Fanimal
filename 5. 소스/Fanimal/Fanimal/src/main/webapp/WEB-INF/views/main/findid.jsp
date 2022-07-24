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
        <div>
            <form action="/fanimal/main/findid.do" method="POST" style="display: flex; flex-direction: column; width: 300px; margin: 0 auto;">
                <div style="margin-top: 5px;">
                    <span style="display: inline-block; width: 100px; background-color: orange; border-radius: 10px; text-align: center;">이름</span>
                    <input type="text" name="name" required>
                </div>
                <div style="margin-top: 5px;">
                    <span style="display: inline-block; width: 100px; background-color: orange; border-radius: 10px; text-align: center;">전화번호</span>
                    <input type="text" name="tel" required>
                </div>
                <div style="margin-top: 5px;">
                    <input type="submit" value="아이디찾기" style="width: 285px; background-color: orange; border: 3px solid orange; border-radius: 3px;">
                </div>
            </form>
        </div>
    </main>
    <script>
    	<c:if test="${not empty id}">
    		alert('가입하신 아이디는 ${id} 입니다.');
    		<c:if test="${type == 'u'}">
    		location.href="/fanimal/main/userlogin.do";
    		</c:if>
    		<c:if test="${type == 'c'}">
    		location.href="/fanimal/main/companylogin.do";
    		</c:if>
    		
    	</c:if>
    	
    	<c:if test="${noId == 'y'}">
			alert('일치하는 회원 정보가 없습니다.');
			location.href="/fanimal/main/findid.do";
		</c:if>
    </script>
</body>
</html>
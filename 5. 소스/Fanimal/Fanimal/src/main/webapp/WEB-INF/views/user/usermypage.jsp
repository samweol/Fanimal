<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="/fanimal/asset/css/user/listanimal.css">
<title>Insert title here</title>
<style>
        .col {
            width: 150px;
            padding: 5px;
            background-color: orange;
            border-radius: 10px;
            text-align: center;
            margin-bottom: 10px;
            font-size: 20px;
        }

        .content {
            padding: 5px;
            margin-left: 10px;
            width: 250px;
            font-size: 18px;
        }
        
        #content {
        	height: calc(100vh - 319px);
        }
</style>
</head>
<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
            <div id="content">	
            	<div style="display: flex; justify-content: center; font-size: 30px; padding-top: 30px;"><i style="padding-top: 5px;" class="fa-solid fa-paw"></i>마이페이지</div>								
				<div style="display: flex; flex-direction: column; align-items: center;">
			            <div style="display: flex; margin-top: 20px;">
			                <div class="col">이름</div>
			                <div class="content">${dto.name }</div>
			            </div>
			            <div style="display: flex;">
			                <div class="col">닉네임</div>
			                <div class="content">${dto.nickname }</div>
			            </div>
			            <div style="display: flex;">
			                <div class="col">아이디</div>
			                <div class="content">${dto.id }</div>
			            </div>
			            <div style="display: flex;">
			                <div class="col">생일</div>
			                <div class="content">${dto.birth }</div>
			            </div>
			            <div style="display: flex;">
			                <div class="col">전화번호</div>
			                <div class="content">${dto.tel }</div>
			            </div>
			            <div style="display: flex;">
			                <div class="col">주소</div>
			                <div class="content">${dto.address }</div>
			            </div>
			            
        			</div>
            </div>
        </section>
        <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
    </main>    
</body>
</html>
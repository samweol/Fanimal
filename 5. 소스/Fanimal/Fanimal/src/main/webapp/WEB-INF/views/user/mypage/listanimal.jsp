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

</style>
</head>
<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
            <div id="content">					
				
				<c:forEach items="${alist}" var="dto">
				<a href="/fanimal/user/mypage/viewanimal.do?uaseq=${dto.uaseq}"> <!-- 클릭 시 링크 설정 -->
				<div class="card">				
					<!-- 카드 헤더 -->
					<div class="card-header" 
						style="background: url('/fanimal/files/${dto.pic}') no-repeat; background-size: 100% 280px; background-repeat: no-repeat;">
						<div class = "card-header-is_closed" > 
			                <!-- <div class = "card-header-text" > </div > 
			                <div class = "card-header-number" > </div >  -->
			            </div >
					</div>
			
					<!--  카드 바디 -->
					<div class="card-body">
			
						<!--  카드 바디 헤더 -->
						<div class="card-body-header">
							<h1><i class="fa-solid fa-paw"></i> ${dto.name}</h1>
							<p class="card-body-hashtag">
								${dto.kind} / <c:if test="${dto.gender == 'm'}">수컷</c:if><c:if test="${dto.gender == 'f'}">암컷</c:if> / ${dto.age}살
							</p>
							<hr style="border-color: #EF5A31; margin: 5px 0px;">
							 <p class = "card-body-nickname">보호자: ${dto.uname}</p>
						</div>
						<!-- <p class="card-body-description">
							안녕하세요!
							4월 15일 순천만 동행구합니다!
						</p> -->
						<!--  카드 바디 본문 -->
			
						<!--  카드 바디 푸터 -->
						<!-- <div class="card-body-footer">
							<hr style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">
							<i class="icon icon-view_count"></i>조회 38회
							<i class="icon icon-comments_count"></i>댓글 4개
							<i class="reg_date"> 2018/04/12 </i>
						</div>	 -->			
					</div>				
				</div>
				</a>				
				</c:forEach>
							
				
				<!-- 반려동물 추가 > AddAnimal.java -->
				<a href="/fanimal/user/mypage/addanimal.do"> <!-- 클릭 시 링크 설정 -->
				<div class="card">				
					<!-- 카드 헤더 -->
					<div class="add-card-header">
					<i class="fa-solid fa-plus" id="plus"></i>
						<div class = "card-header-is_closed" > 
			                <!-- <div class = "card-header-text" > </div > 
			                <div class = "card-header-number" > </div >  -->
							
			            </div >
					</div>
			
					<!--  카드 바디 -->
					<div class="card-body">
			
						<!--  카드 바디 헤더 -->
						<div class="card-body-header">
						
						</div>							
					</div>				
				</div>
				</a>			


            </div>
        </section>
       <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
    </main>    
</body>
</html>
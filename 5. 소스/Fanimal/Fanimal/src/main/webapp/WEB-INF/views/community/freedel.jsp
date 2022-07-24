<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "/WEB-INF/views/inc/userasset.jsp"%>
</head>
<body>

	<main>
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
			

			<div id="content">
			<form method="POST" action="/fanimal/community/freedelok.do" style="width: 300px; margin: 0 auto; text-align: center;" >
						
			<div style="margin-bottom: 15px; padding-top: 30px; font-size: 25px;">삭제하시겠습니까?</div>
						
			<div class="btns" style="padding-bottom: 30px;">
				<input type="button" value="돌아가기" class="btn btn-secondary"
					onclick="location.href='/fanimal/community/freeview.do?seq=${seq}';">
				<button class="btn btn-primary">
					<i class="fas fa-pen"></i>
					삭제하기
				</button>
			</div>
			
			<input type="hidden" name="seq" value="${seq}">
			
			</form>
			
			</div>
		</section>
		<%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
	</main>

</body>
</html>
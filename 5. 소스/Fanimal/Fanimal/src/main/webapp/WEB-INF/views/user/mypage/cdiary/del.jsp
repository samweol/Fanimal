<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
	     	컨텐츠내용
	     
	        </section>
	        <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
        </main>
        
        	
	<script>
		
		<c:if test="${result == 1}">
		location.href = '/fanimal/cdiary/list.do';
		</c:if>
		
		<c:if test="${result == -1}">
		alert('failed');
		history.back();
		</c:if>
	
	</script>
</body>
</html>
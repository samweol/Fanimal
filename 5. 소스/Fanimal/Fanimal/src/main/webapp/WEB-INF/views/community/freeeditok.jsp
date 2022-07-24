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
			
		</section>
		<%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
	</main>
	
	<script>
		
		<c:if test="${result == 1}">
		location.href = '/fanimal/community/freeview.do?seq=${seq}&isSearch=${isSearch}&column=${column}&word=${word}';
		</c:if>
		
		<c:if test="${result == 0}">
		alert('failed');
		history.back();
		</c:if>
	
	</script>

</body>
</html>
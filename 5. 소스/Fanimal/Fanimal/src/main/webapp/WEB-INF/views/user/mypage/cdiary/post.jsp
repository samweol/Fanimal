<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<title>Insert title here</title>
</head>
<body>
<%

String filename = (String)request.getAttribute("filename");

String src = request.getContextPath() + "\\files\\" + filename;

%>


	<main>
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		<section>

	    </section>
	   <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
        </main>
        
      <script>
		<c:if test="${result == 1}">
		location.href ='/fanimal/cdiary/list.do?value=${value}';
		</c:if>
		
		<c:if test="${result == 0}">
		alert('failed');
		history.back();
		</c:if>
	
	</script>
</body>
</html>
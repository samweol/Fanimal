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
		<%@ include file ="/WEB-INF/views/inc/header.jsp"%>
		<section>
	            <div id="content" style="width: 100%;">
	                <div class="slider" style="width: 100%;">
	                    <input type="radio" name="slide" id="slide1" checked>
	                    <input type="radio" name="slide" id="slide2">
	                    <input type="radio" name="slide" id="slide3">
	                    <input type="radio" name="slide" id="slide4">
	                    <ul id="imgholder" class="imgs">
	                        <li><img src="/fanimal/asset/images/back3.jpg" style="width: 100%;" class="image-thumbnail"></li>
	                        <li><img src="/fanimal/asset/images/back2.jpg" style="width: 100%;" class="image-thumbnail"></li>
	                        <li><img src="/fanimal/asset/images/back1.jpg" style="width: 100%;" class="image-thumbnail"></li>
	                    </ul>
	                    <div class="bullets">
	                        <label for="slide1">&nbsp;</label>
	                        <label for="slide2">&nbsp;</label>
	                        <label for="slide3">&nbsp;</label>
	                    </div>
	                </div>
	            </div>
	        </section>
	        <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
        </main>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>

<c:if test="${type == '0'}">
		
<link rel="stylesheet" href="/fanimal/asset/css/company_style.css">
</c:if>
<title>Insert title here</title>
<style>
#inquirelist { font-size: 14px; }
#inquirelist > h1 { padding: 70px 0 30px 0; margin:0; }

.inquirelist-list { background-color: white;  margin-top: 20px;}
.inquirelist-list tr:nth-child(1) { background-color: gold; height: 40px; }
.inquirelist-list th, .inquirelist-list td { text-align: center; }
.inquirelist-list tr:hover { font-weight: bold; cursor:pointer; }
.inquirelist-list th:nth-child(1){ width: 100px; }
.inquirelist-list th:nth-child(2){ width: auto; }
.inquirelist-list th:nth-child(3){ width: 180px; }
.inquirelist-list th:nth-child(4){ width: 180px; }
.inquirelist-list th:nth-child(5){ width: 100px; }
.inquirelist-list td:nth-child(2) { text-align:left; }

.inquirelist-list .list-notice { 
	font-weight: bold;
}

.inquirelist-search input {
	display: inline-block;
	width: 280px;
	margin: 0 3px;
	font-size: 14px;
}
.inquirelist-search {
	width: 450px;
	margin: 0 auto;
}

.inquirelist-search select {
	display: inline-block;
	width: 80px;
}

.inquirelist-page {
	width: 500px;
	margin: 0 auto;
	text-align: center;
}

.inquirelist-page .page-link, .hospitallist-page .page-link:hover {
	color: black;
}
.inquirelist-page .active .page-link {
	color: black;
	background-color: gold;
	border-color: #DDD;
}
.inquirelist-page .active .page-link:hover {
	background-color: #F0AD4E;
	border-color: #AAA;
}
#inquirelist .list-add {
	float: right;
	margin: 10px 0;
}

</style>
</head>
<body>
	<main>
		<c:if test="${type == '0'}">
		<%@ include file ="/WEB-INF/views/inc/companyheader.jsp"%>
		</c:if>
		<c:if test="${type == '1'}">
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		</c:if>
		<c:if test="${type == '2'}">
		<%@ include file ="/WEB-INF/views/inc/header.jsp"%>
		</c:if>
		<section>
            <div id="content">
			
						
			<form method="GET" action="/fanimal/inquire/hospitalinquirelist.do" id="inquirelist" style="padding-bottom: 30px;">

				<h1>${dto.hosname} 문의게시판</h1>
	        	
	        	<table class="table table-stripped inquirelist-list">
	        		<tr>
	        			<th>번호</th>
	        			<th>제목</th>
	        			<th>작성일</th>
	        			<th>작성자</th>
	        			<th>비밀글</th>
	        		</tr>
	        		<c:forEach items="${list}" var="ques">
 	        		<tr>
	        			<td>${ques.hqseq}</td>
	        			<td>
	        				<c:if test="${ques.type == '0'}">
	        				<span class="list-notice"><i class="fa-solid fa-bullhorn"></i> ${ques.title}</span>
	        				</c:if>
	        				<c:if test="${ques.type == '1'}">
		        				${ques.title}
		        				<c:if test="${not empty ques.attachFile}">
		        				<i class="fa-solid fa-image"></i>
		        				</c:if>
		        				<c:if test="${ques.answer == 'y'}">
		        				<span class="badge rounded-pill text-bg-success">답변 완료</span>
		        				</c:if>
	        				</c:if>
	        			</td>
	        			<td>${ques.postdate}</td>
	        			<td>${ques.nickname}</td>
	        			<td>
		        			<c:if test="${ques.secret == 'y'}">
		        			<i class="fa-solid fa-lock"></i>
		        			</c:if>
	        			</td>
	        		</tr>
	        		</c:forEach>
	        		<c:if test="${empty list}">
	        		<tr>
	        			<td colspan="5">해당하는 문의게시판 글이 없습니다.</td>
	        		</tr>
	        		</c:if>
	        	</table>
	        	<input type="hidden" name="si" value="${hdto.si}">
	        	<input type="hidden" name="gu" value="${hdto.gu}">
	        	<input type="hidden" name="align" value="${hdto.align}">
	        	<input type="hidden" name="search" value="${hdto.search}">
	        	<input type="hidden" name="page" value="${hdto.page}">
	        	<input type="hidden" name="hpseq" value="${dto.hpseq}">
	        	
	        	<c:if test="${type == '0'}">
	        	<button class="btn btn-success list-add">공지글 쓰기</button>
				</c:if>
				<c:if test="${type == '1'}">
	        	<button class="btn btn-success list-add">문의글 쓰기</button>
				</c:if>
	        	
	        	<c:if test="${dto.QEndPage > 1}">
				<nav aria-label="Page navigation example" class="inquirelist-page">
				  <ul class="pagination justify-content-center">
				    <li class="page-item">
				      <a class="page-link badge-pill" onclick="movePage(${dto.QPage - 1});" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <c:forEach var="i" begin="${dto.QBeginPage}" end="${dto.QEndPage}">
				    <c:if test="${i == dto.QPage}">
				    <li class="page-item active"><a class="page-link badge-pill" onclick="movePage(${i});">${i}</a></li>
				    </c:if>
				    <c:if test="${i != dto.QPage}">
				    <li class="page-item"><a class="page-link badge-pill" onclick="movePage(${i});">${i}</a></li>
				    </c:if>
				    </c:forEach>
				    <li class="page-item">
				      <a class="page-link badge-pill" onclick="movePage(${dto.QPage + 1});" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
				</c:if>
				
						
						
				<div class="inquirelist-search">
					<select name="qSearchKey" class="form-control">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="nickname">작성자</option>
					</select>
					<input type="text" name="qSearchValue" class="form-control col-8">
					<button class="btn btn-success">검색</button>
				</div>
	        </form>
	        
	        
            </div>
        </section>
        <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
	</main>
	                          
	<script>
	
		$('.list-notice').parents('tr').css('background-color', '#EEE');
	
		$('#inquirelist select[name=qSearchKey]').val('${dto.QSearchKey}');
		$('#inquirelist input[name=qSearchValue]').val('${dto.QSearchValue}');
		
		function movePage(page) {
			
			let qSearchKey = $('#inquirelist select[name=qSearchKey]').val();
			let qSearchValue = $('#inquirelist input[name=qSearchValue]').val();
			
			location.href = '/fanimal/inquire/hospitalinquirelist.do?si=${hdto.si}&gu=${hdto.gu}&align=${hdto.align}&search=${hdto.search}&page=${hdto.page}&hpseq=${dto.hpseq}&qSearchKey=' + qSearchKey + '&qSearchValue=' + qSearchValue + '&qPage=' + page;
	 	}
		
		
	
	</script>
</body>
</html>
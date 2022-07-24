<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
<style>
#hospitallist { font-size: 14px; }
.hospitallist-sigu { display: inline-block; width: 50rem; }
.hospitallist-sigu select {
	display: inline;
	margin: 100px 10px 20px 15px;
	font-size: 14px;
}
.hospitallist-align { width: 35rem; float:right; }
.hospitallist-align select {
	display: inline;
	margin-top: 100px;
	float:right;
	font-size: 14px;
} 
.hospitallist-search input {
	display: inline-block;
	width: 300px;
	margin-right: 10px;
	font-size: 14px;
}
.hospitallist-search {
	width: 400px;
	margin: 0 auto;
}

.hospitallist-page {
	width: 500px;
	margin: 0 auto;
}

.hospitallist-page .page-link, .hospitallist-page .page-link:hover {
	color: black;
}
.hospitallist-page .active .page-link {
	color: black;
	background-color: gold;
	border-color: #DDD;
}
.hospitallist-page .active .page-link:hover {
	background-color: #F0AD4E;
	border-color: #AAA;
}

.hospitalist-list { background-color: white;  margin-top: 20px;}
.hospitalist-list tr:nth-child(1) { background-color: gold; height: 40px; }
.hospitalist-list th, .hospitalist-list td { text-align: center; }
.hospitalist-list th:nth-child(1) { width: 90px; }
.hospitalist-list th:nth-child(2) { width: 180px; }
.hospitalist-list th:nth-child(3) { width: 180px; }
.hospitalist-list th:nth-child(4) { width: auto; }
.hospitalist-list th:nth-child(5) { width: 100px; }
.hospitalist-list td:nth-child(4),
.hospitalist-list td:nth-child(5) { text-align:left; }
.hospitalist-list tr:hover { font-weight: bold; cursor:pointer; }

</style>
</head>
<body>

	<main>
		<c:if test="${empty auth.id }">
      		<%@ include file ="/WEB-INF/views/inc/header.jsp"%>
   		</c:if>
   		<c:if test="${not empty auth.id }">
     		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
   		</c:if>
		<section>
		<div id="content">
			<form method="GET" action="/fanimal/diagnosis/hospitallist.do" id="hospitallist">
				<div class="hospitallist-sigu">
					<span>시도</span>
					<select name="si" class="form-control col-4">
						<option value="all">전체</option>
						<c:forEach items="${silist}" var="si">
						<option value="${si}">${si}</option>
						</c:forEach>
					</select>
					
					<span>시군구</span>
					<select name="gu" class="form-control col-4">
						<option value="all">전체</option>
						<c:forEach items="${gulist}" var="gu">
						<option value="${gu}">${gu}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="hospitallist-align">
					<select name="align" class="form-control col-3">
						<option value="distance">거리순</option>
						<option value="avgstar">별점순</option>
					</select>
				</div>
				
				<table class="table table-stripped hospitalist-list">
					<tr>
						<th>번호</th>
						<th>병원명</th>
						<th>전화번호</th>
						<th>병원주소</th>
						<th>별점</th>
					</tr>
					<c:if test="${not empty list}">
					<c:forEach items="${list}" var="ldto">
					<tr onclick="location.href='/fanimal/diagnosis/hospitalview.do?hpseq=${ldto.hpseq}&si=${dto.si}&gu=${dto.gu}&align=${dto.align}&search=${dto.search}&page=${dto.page}';">
						<td>${ldto.hpseq}</td>
						<td>${ldto.hosname}</td>
						<td>${ldto.tel}</td>
						<td>${ldto.address}</td>
						<td>
						<c:if test="${not empty ldto.avgStar}">
						★ ${ldto.avgStar}
						</c:if>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${empty list}">
					<tr>
						<td colspan="5">병원 목록이 존재하지 않습니다.</td>
					</tr>
					</c:if>
				</table>
				
				<c:if test="${endPage > 1}">
				<nav aria-label="Page navigation example" class="hospitallist-page">
				  <ul class="pagination">
				    <li class="page-item">
				      <a class="page-link badge-pill" onclick="movePage(${dto.page - 1});" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <c:forEach var="i" begin="${beginPage}" end="${endPage}">
				    <c:if test="${i == dto.page}">
				    <li class="page-item active"><a class="page-link badge-pill" onclick="movePage(${i});">${i}</a></li>
				    </c:if>
				    <c:if test="${i != dto.page}">
				    <li class="page-item"><a class="page-link badge-pill" onclick="movePage(${i});">${i}</a></li>
				    </c:if>
				    </c:forEach>
				    <li class="page-item">
				      <a class="page-link badge-pill" onclick="movePage(${dto.page + 1});" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
				</c:if>
						
						
				<div class="hospitallist-search" style="padding-bottom: 30px;">
					<input type="text" name="search" placeholder="병원명을 검색하세요." class="form-control col-8">
					<button class="btn btn-secondary">검색</button>
				</div>
			</form>	
			
			
		</div>	
		</section>
		<%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
	</main>
	
	<script>
		$('#hospitallist select[name=si]').val('${dto.si}');
		$('#hospitallist select[name=gu]').val('${dto.gu}');
		$('#hospitallist select[name=align]').val('${dto.align}');
		$('#hospitallist input[name=search]').val('${dto.search}');
		
		function movePage(page) {
			
			let si = $('#hospitallist select[name=si]').val();
			let gu = $('#hospitallist select[name=gu]').val();
			let align = $('#hospitallist select[name=align]').val();
			let search = $('#hospitallist input[name=search]').val();
			
			location.href = '/fanimal/diagnosis/hospitallist.do?si='+si+'&gu='+gu+'&align='+align+'&search='+search+'&page='+page;
	 	}
		
		$('#hospitallist select[name=si]').change(function() {
			$('#hospitallist select[name=gu]').val('all');
			movePage(1);
		});
		
		$('#hospitallist select[name=gu]').change(function() {
			movePage(1);
		});
		
		$('#hospitallist select[name=align]').change(function() {
			movePage(1);
		});
		
		
	</script>
</body>
</html>
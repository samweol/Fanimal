<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "/WEB-INF/views/inc/userasset.jsp"%>
<style>
.container {
	max-width:900px;
}

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
			<div class="container" style="padding-top:30px;">
			<div style="font-size: 30px; display:flex; justify-content: center; padding-bottom: 30px;">커뮤니티</div>
				<c:if test="${map.isSearch == 'y'}">
				<div style="text-align:center; margin-bottom: 10px; color: cornflowerblue;">
					'${map.word}'
				</div>
				</c:if>
				
				<table class="table table-hover horizontal" >
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>날짜</th>
					</tr>
					<c:forEach items="${list}" var="dto">
								
					<tr>
						<td>${dto.commuseq}</td>
						<td>
							<a href="/fanimal/community/freeview.do?seq=${dto.commuseq}&isSearch=${map.isSearch}&column=${map.column}&word=${map.word}">${dto.title}</a>
							
						</td>
						<td>${dto.nickname}</td>
						<td>${dto.readcount}</td>					
						<td>${dto.postDay}</td>
						
					</tr>
					</c:forEach>
					<c:if test="${list.size() == 0}">
					<tr>
						<td colspan="5">게시물이 없습니다.</td>
					</tr>
					</c:if>
				</table>
				
				<div style="text-align: center;">
					
					${pagebar}
					
				</div>
						
				<c:if test="${not empty auth.id}">
				<div class="btns">
					<button type="button" class="btn btn-info" style="float:right;"
						onclick="location.href='/fanimal/community/freeadd.do';">		
						글쓰기
					</button>
				</div>
				</c:if>
					
				<br>
				<br>
				<br>
				<br>			
						
				<div>
					<form method="GET" action="/fanimal/community/freelist.do" style="display:flex; justify-content: center; padding-bottom: 30px;">
					<table class="search">
						<tr>
							<td>
								<select name="column" class="form-control">
									<option value="title">제목</option>
									<option value="field">내용</option>
								</select>
							</td>
							<td>
								<input type="text" name="word" class="form-control" required>
							</td>
							<td>
								<button class="btn btn-info">
									검색하기
								</button>
								
								<c:if test="${map.isSearch == 'y'}">
								<button class="btn btn-secondary" type="button"
									onclick="location.href='/fanimal/community/freelist.do';">
									돌아가기
								</button>
								</c:if>
							</td>
						</tr>
					</table>
					</form>
				</div>	
			</div>
		</section>
		<%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
		
	
	</main>
	
	<script>
		
		<c:if test="%{dto.postDay == nowday}">
			
		</c:if>
	
	
	
		//검색중 > 상태유지
		<c:if test="${map.isSearch == 'y'}">
		$('select[name=column]').val('${map.column}');
		$('input[name=word]').val('${map.word}');
		</c:if>
	
		
		$("#pagebar").change(function() {
			
			location.href = '/fanimal/community/freelist.do?page=' + $(this).val() + "&column=${map.column}&word=${map.word}";
			
		});
		
		$('#pagebar').val(${nowPage});
		
		function new_icon($bo_table) {
			  global $g4;
			  $temp = sql_fetch("select bo_new from `$g4[board_table]` where bo_table = '$bo_table'");
			  $timefrom = date("Y-m-d H:i:s", time() - $temp[bo_new] * 60 * 60);
			  if(sql_fetch("select wr_datetime from `$g4[write_prefix]$bo_table` where !wr_is_comment AND wr_datetime > '$timefrom'")) echo " <img src=\"$g4[path]/skin/board/basic/img/icon_new.gif\" />";
			}
	
	</script>
		
	
</body>
</html>
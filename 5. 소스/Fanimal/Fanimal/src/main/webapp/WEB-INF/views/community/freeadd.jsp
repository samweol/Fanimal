<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "/WEB-INF/views/inc/userasset.jsp"%>
</head>

<script type="text/javascript">
</script>
<body>
	<main>
		
			<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
			<section>
			 <div id="content">
				<form method="post" action="/fanimal/community/freeaddok.do" enctype="multipart/form-data" style="padding-top: 30px;">
					
					<table class="table table-bordered vertical">
						
						<tr>
							<td style = "width: 150px;">
								<select name="division" class="form-control">
										<option value="1">자유게시판</option>
										<option value="2">증상게시판</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" class="form-control" required></td>												
							
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="field" class="form-control" style="height: 500px;" required></textarea></td>
						</tr>
						<tr>
							<th>파일</th>
							<td><input type="file" name="attach" class="form-control"></td>
						</tr>
					</table>
					
					<div class="btns" style="padding-bottom: 30px; display: flex; justify-content: right;">
					<input type="button" value="돌아가기" class="btn btn-secondary"
						onclick="location.href='/main/index.do';">
					<button class="btn btn-primary">
						<i class="fas fa-pen"></i>
						쓰기
					</button>
				</div>
					
				</form>
			</div>
			</section>
			<%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
		
	</main>
			
</body>
</html>
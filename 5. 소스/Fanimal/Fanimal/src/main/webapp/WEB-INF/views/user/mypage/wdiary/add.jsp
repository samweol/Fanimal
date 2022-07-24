<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="/fanimal/asset/css/add.css">
<title>Insert title here</title>
</head>
<body>
	<main>
		<%@ include file="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
			<div class="container" style="padding-top: 30px; padding-bottom: 30px;">
				<form action="/fanimal/wdiary/post.do" method="post"
					enctype="multipart/form-data">

					<div class="form-group row">

						<label for="title" class="col-sm-2 col-form-label">글제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-plaintext" id="title"
								name="title">
						</div>
					</div>
					
					
			   	<div class="form-group row">
						<label for="place" class="col-sm-2 col-form-label">장소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-plaintext" id="place"
								name="place">
						</div>
					</div>

					<div class="form-group row">
						<label for="datetune" class="col-sm-2 col-form-label">작성일</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-plaintext"
								placeholder="yy/mm/dd" id="datetime" name="datetime">
						</div>
					</div>


					<div class="form-group row">
						<label for="datetune" class="col-sm-2 col-form-label">파일</label>
						<div class="col-sm-10">
							<input type="file" class="form-control-file" id="pic"
								name="pic">
						</div>
					</div>
					<div class="diary-type">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="diaryType"
								id="care" value="care"> <label class="form-check-label"
								for="care">케어일기</label>
						</div>

						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="diaryType"
								id="walk" value="walk"> <label class="form-check-label"
								for="walk">산책일기</label>
						</div>
						</div>

					
					<div class="form-group">
						<textarea class="form-control" id="content" name="content"
							rows="10" placeholder="내용을 입력하세요"></textarea>
					</div>
					<input type="hidden" name="aseq" value="${aseq}">
					<button type="submit" class="btn btn-primary">작성</button>

				</form>
			</div>
		</section>
		<%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
	</main>
</body>
</html>
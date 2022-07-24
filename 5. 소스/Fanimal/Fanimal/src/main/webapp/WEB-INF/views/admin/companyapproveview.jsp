<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
#main {
	height: 100%;
	margin: 0 auto;
}

#main h1 {
	padding-top: 30px;
	align-content: center;
	text-align: center;
	margin: 0;
	padding-bottom: 20px;
	/* padding-bottom: 5px; */
	margin-bottom: 5px;
}

#form {
	background-color: white;
	height: 370px;
	font-size: 19px;
	width: 940px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 10px;
	padding: 10px;
}

#list1 {
	width: 900px;
	align-content: center;
/* 	border: 1px solid #444; */
	margin: 10px;
	margin-bottom: 18px;
}

#list1 .cell1 {
	width:150px;
	text-align: center;
	background-color: #ffdf9e;
	border-radius : 20px;
/* 	border: 1px solid #444; */
	border-radius: 20px;
	padding: 5px;
}

#list1 .cell2 {
	text-align: center;
}

#list1 .cell3 {
	padding-left: 20px;
}

#btns {
	margin-top: 15px;
	margin-left: auto;
	margin-right: auto;
	float: right;
}
</style>
</head>
<body>
	<main>
		<%@ include file="/WEB-INF/views/inc/adminheader.jsp"%>
		<section id="main">

			<h1>기업 승인 상세보기</h1>
			<form method="GET" action="/fanimal/admin/companyapprovelist.do"
				id="form">
				<table id="list1">

						<tr>
							<td class="cell1">기업명</td>
							<td class="cell3" colspan="3">${dto.hosname}</td>
						</tr>
						
				</table>
				<table id="list1">	
						<tr>
							<td class="cell1">인허가번호</td>
							<td class="cell3" colspan="3">제 ${dto.license} 호</td>
						</tr>
				</table>
				<table id="list1">
						<tr>
							<td class="cell1">소개</td>
							<td class="cell3" colspan="3">${dto.info}</td>
						</tr>
				</table>
						
				<table id="list1">
						<tr>
							<td class="cell1">운영시간</td>
							<td class="cell2">${dto.starttime}~${dto.endtime}</td>
							<td class="cell1">휴게시간</td>
							<td class="cell2">14:00~15:00</td>
						</tr>
				</table>
						
				<table id="list1">
						<tr>
							<td class="cell1">승인상태</td>
							<td class="cell3" colspan="3">${dto.stat}</td>
						</tr>


				</table>

				<div id="btns">
					<button id="testBtn" class="btn btn-primary">승인하기</button>
					<div class="modal" id="testModal" tabindex="-1" role="dialog"
						aria-hidden="true">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-body">
									<p>[Hospital] 승인하시겠습니까?</p>
								</div>
								<div class="modal-footer">
									<button class="btn btn-secondary" type="button"
										data-dismiss="modal">뒤로가기</button>
									<button type="button" class="btn btn-primary"
										data-dismiss="modal"
										onclick="location.href='/fanimal/admin/companyapprovecheck.do'">승인완료</button>
								</div>
							</div>
						</div>
					</div>

					<button id="testBtn1" class="btn btn-secondary">거절하기</button>
					<div class="modal" id="testModal1" tabindex="-1" role="dialog"
						aria-hidden="true">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-body">
									<p>[Hospital] 거절하시겠습니까?</p>
								</div>
								<div class="modal-footer">
									<button class="btn btn-secondary" type="button"
										data-dismiss="modal">뒤로가기</button>
									<button type="button" class="btn btn-primary"
										data-dismiss="modal"
										onclick="location.href='/fanimal/admin/companyapproveview.do'">거절완료</button>
								</div>
							</div>
						</div>
					</div>
					<button type="button" class="btn btn-success"
						onclick="location.href='/fanimal/admin/companyapprovelist.do'">뒤로가기</button>
				</div>
			</form>
		</section>




	</main>

	<script>
		$('#testBtn').click(function(e) {
			e.preventDefault();
			$('#testModal').modal("show");
		});

		$(document).ready(
				function() {
					function alignModal() {
						var modalDialog = $(this).find(".modal-dialog");

						// Applying the top margin on modal dialog to align it vertically center
						modalDialog.css("margin-top", Math.max(0, ($(window)
								.height() - modalDialog.height()) / 2));
					}
					// Align modal when it is displayed
					$(".modal").on("shown.bs.modal", alignModal);

					// Align modal when user resize the window
					$(window).on("resize", function() {
						$(".modal:visible").each(alignModal);
					});
				});

		$('#testBtn1').click(function(e) {
			e.preventDefault();
			$('#testModal1').modal("show");
		});
	</script>

</body>
</html>






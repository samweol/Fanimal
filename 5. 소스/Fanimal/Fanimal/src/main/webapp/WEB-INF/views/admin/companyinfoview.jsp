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
	/* width: 940px; */
	margin: 0 auto;
	height: 100%;
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

#tcor {
	width: 1000px;
	background-color: white;
	margin-left: auto;
	margin-right: auto;
	padding: 10px;
}

#list1 {
	font-size:	18px;
	margin-left: auto;
	margin-right: auto;
	margin-top:10px;
	
}

#list1 .cell1 {
	text-align: center;
	background-color: #ffdf9e;
	border-radius: 20px;
	padding: 10px;
	width: 120px;
}

#list1 .cell2 {
	padding-left: 20px;
}

#list1 .cell3 {
	padding-left: 20px;
	width: 572px;
}

#list2 {
	font-size:	18px;
	margin-left: auto;
	margin-right: auto;
}

#list2 .cell1 {
	text-align: center;
	width: 120px;
	background-color: #ffdf9e;
	border-radius: 20px;
	padding: 10px;
}

#list2 .cell3 {
	padding-left: 20px;
	width: 815px;
}

#list3 {
	width: 100px;
	font-size:	18px;
	position: relative;
	padding: 5px;
	width: 1000px;
	max-width: 100%;
	margin-left: auto;
	margin-right: auto;
	
}


.btn {
	float:right;
}
</style>
</head>
<body>
	<main>
		<%@ include file="/WEB-INF/views/inc/adminheader.jsp"%>
		<section id="main">

			<h1>기업 정보 조회</h1>
			<div id="tcor">
			<table id="list2">
				<tr>
					<td class="cell1">대표자 이름</td>
					<td class="cell3">${cdto.name}</td>
				</tr>
				<tr>
					<td class="cell1">사업자 번호</td>
					<td class="cell3">${cdto.business}</td>
				</tr>
				<tr>
					<td class="cell1">아이디</td>
					<td class="cell3">${cdto.id}</td>
				</tr>
				<tr>
					<td class="cell1">이메일</td>
					<td class="cell3">${cdto.email}</td>
				</tr>
				</table>
			<table id="list1">

				<tr>
					<td class="cell1">기업명</td>
					<td class="cell3">${cvdto.hosname}</td>
				</tr>
				<tr>
					<td class="cell1">인허가번호</td>
					<td class="cell3">${cvdto.license}</td>
				</tr>
				<tr>
					<td class="cell1">소개</td>
					<td class="cell3">${cvdto.info}</td>
				</tr>
				<tr>
					<td class="cell1">운영시간</td>
					<td class="cell2">${cvdto.starttime}~${cvdto.endtime}</td>
					<td class="cell1">휴게시간</td>
					<td class="cell2">14:00~15:00</td>
				</tr>
				<tr>
					<td class="cell1">승인상태</td>
					<td class="cell3" colspan="3">${cvdto.state}</td>
				</tr>
				</table>
				
			<table id = "list3">
			<tr class = "btns">
			<td>
			
			<input type ="button" id="testBtn" class="btn btn-primary" value="삭제하기">
			<div class="modal" id="testModal" tabindex="-1" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-body">
							<p>[Hospital] 삭제하시겠습니까?</p>
							<p class="text-warning">
								<small>※삭제완료를 누르게 되면 복원이 불가합니다※</small>
							</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal"
								onclick="location.href='/fanimal/admin/companyinfolist.do'">삭제완료</button>
							<input type ="button" class="btn btn-secondary" type="button"
								data-dismiss="modal" value="뒤로가기">
						</div>
					</div>
				</div>
			</div>
			<input type ="button" class="btn btn-secondary"
				onclick="location.href='/fanimal/admin/companyinfolist.do'" value="뒤로가기">
			</td>
			
			</tr>
			</table>
			</div>
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
	</script>

</body>
</html>






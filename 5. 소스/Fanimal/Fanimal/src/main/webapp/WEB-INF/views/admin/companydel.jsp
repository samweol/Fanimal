<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/inc/userasset.jsp"%>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/views/inc/adminheader.jsp"%>
		<section>

			<h2>Board</h2>

			<form method="POST" action="/fanimal/admin/companydelok.do">
				<div class="btns">
					<button id="testBtn" class="btn btn-primary">삭제하기</button>
					<div class="modal" id="testModal" tabindex="-1" role="dialog"
						aria-hidden="true">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-body">
									<p>[Hospital] 삭제하시겠습니까?</p>
									<p class="text-warning">
										<small>※삭제완료버튼을 누르게 되면 복원이 불가합니다※</small>
									</p>
								</div>
								<div class="modal-footer">
									<button class="btn btn-secondary" type="button"
										data-dismiss="modal">뒤로가기</button>
									<button type="button" class="btn btn-primary"
										data-dismiss="modal"
										onclick="location.href='/fanimal/admin/companyapprovecheck.do'">삭제완료</button>
								</div>
							</div>
						</div>
					</div>


					<input type="button" value="뒤로가기" class="btn btn-secondary"
						onclick="location.href='/fanimal/admin/companyinfoview.do?seq=${seq}';">
				</div>

				<input type="hidden" name="seq" value="${seq}">

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
	</script>
</body>
</html>






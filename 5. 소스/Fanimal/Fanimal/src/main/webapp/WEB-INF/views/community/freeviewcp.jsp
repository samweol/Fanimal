<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "/WEB-INF/views/inc/companyasset.jsp"%>
</head>

<style>
	img {
	  width: 300px;
	  height: 150px;
	  object-fit: cover;
	}
	
	@media (min-width: 1200px) {
    .container{
        max-width: 800px;
    }
    
    textarea { 
    	resize: none;
    }
}

	table, th, td {
	    border: 1px solid #bcbcbc;
	  }
	  .jb-th-1 {
	    padding: 20px 30px;
	  }
</style>


<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/companyheader.jsp"%>
		<section>
		<div class="container" style="padding-top:30px;">			
				<table class="table table-bordered vertical">
					<tr>
						<th>제목</th>
						<td>${dto.title}</td>
						
					</tr>
				
					<tr>
						<th>내용</th>
						<td class="photo" style="height: 300px; vertical-align: middle;">${dto.field}
							<br>
							<br>
							<c:if test="${not empty dto.orgattachFile}">
							<img src="/fanimal/community/download.do?attachFile=${dto.attachFile}&orgattachFile=${dto.orgattachFile}"/>
							</c:if>
						</td>
	
					</tr>
					
					<tr>
					<th>파일</th>
					<td>
						
						<c:if test="${not empty dto.orgattachFile}">
						<a href="/fanimal/community/download.do?attachFile=${dto.attachFile}&orgattachFile=${dto.orgattachFile}">${dto.orgattachFile}</a>
						</c:if>
						
						<c:if test="${empty dto.orgattachFile}">
						파일 없음
						</c:if>
						
					</td>
				</tr>

				</table>
				
				<div class="btns">
				
					<input type="button" value="돌아가기" class="btn btn-secondary"
						onclick="location.href='/fanimal/community/freelistcp.do?column=${column}&word=${word}';">
						
					<c:if test="${not empty auth.id}">
					
					<c:if test="${dto.id == auth.id || auth.id == 'admin'}">
					<button class="btn btn-info"
						onclick="location.href='/fanimal/community/freeedit.do?seq=${dto.commuseq}';">	
						수정하기
					</button>
					
					<button class="btn btn-info"
						onclick="location.href='/fanimal/community/freedel.do?seq=${dto.commuseq}';">
						삭제하기
					</button>
					</c:if>
					
					</c:if>
					
					<br>
					<br>
					
					
				</div>
				
				<!-- 댓글 -->
				 
				<form method="POST" action="/fanimal/community/freecommentaddok.do">
				<table class="tblAddAnswer">
					<tr>
						<td>
							<textarea class="form-control" name="content" rows="1" cols="50" required></textarea>
						</td>
						
						
						<td>
							<button class="btn btn-info">
								입력하기
							</button>
						</td>
					</tr>
				</table>
				<input type="hidden" name="commuseq" value="${dto.commuseq}">
				
				<input type="hidden" name="isSearch" value="${isSearch}">
				<input type="hidden" name="column" value="${column}">
				<input type="hidden" name="word" value="${word}">
				</form>
				
				
				<table class="table table-bordered comment">
					<c:forEach items="${clist}" var="cdto">
					<tr>
						<td>
							<div>${cdto.content}</div>
							<div>
								<span>${cdto.answerDay}</span>
								<span>${cdto.id}</span>
								<c:if test="${cdto.id == auth.id}">
								<span class="btnspan"><a href="#!" onclick="delcomment(${cdto.caseq});">[삭제]</a></span>
								<span class="btnspan"><a href="#!" onclick="editcomment(${cdto.caseq});">[수정]</a></span>
								</c:if>
							</div>
						</td>
					</tr>
					</c:forEach>
					
				</table>
				
		</div>
		</section>
		<%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
	</main>

	<script>
	
		$('.table.comment td').mouseover(function() {
			$(this).find('.btnspan').show();
		});
		
		$('.table.comment td').mouseout(function() {
			$(this).find('.btnspan').hide();
		});
		
		
		function delcomment(seq) {
			
			if (confirm('delete?')) {
				
				//console.log('freecommentdelok.do?seq=' + seq + '&commuseq=${dto.commuseq}&isSearch=${isSearch}&column=${column}&word=${word}');
				location.href = 'freecommentdelok.do?seq=' + seq + '&commuseq=${dto.commuseq}&isSearch=${isSearch}&column=${column}&word=${word}';
				
			}
			
		}
		
		let isEdit = false; 
		
		function editcomment(seq) {
			
			if (!isEdit) {
				
				const tempStr = $(event.target).parent().parent().prev().text();
				
				$(event.target).parents('tr').after(temp);
				
				isEdit = true;
				
				$(event.target).parents('tr').next().find('textarea').val(tempStr);
				$(event.target).parents('tr').next().find('input[name=seq]').val(seq);
			}
			
		}
		
		
		const temp = `<tr id='editRow' style="background-color: #CDCDCD;">
						<td>
							<form method="POST" action="/fanimal/community/freecommenteditok.do">
							<table class="tblEditComment">
								<tr>
									<td>
										<textarea class="form-control" name="content" required></textarea>
									</td>
									<td>
										<button class="btn btn-secondary" type="button"
											onclick="cancelForm();">
											취소하기
										</button>
										<button class="btn btn-primary">
											<i class="fas fa-pen"></i>
											수정하기
										</button>
									</td>
								</tr>
							</table>
							<input type="hidden" name="commuseq" value="${dto.commuseq}">
							
							<input type="hidden" name="isSearch" value="${isSearch}">
							<input type="hidden" name="column" value="${column}">
							<input type="hidden" name="word" value="${word}">
							
							<input type="hidden" name="seq">
							</form>
						</td>
					</tr>`;
	
		function cancelForm() {
			$('#editRow').remove();
			isEdit = false;
		}
		
	</script> 

</body>
</html>
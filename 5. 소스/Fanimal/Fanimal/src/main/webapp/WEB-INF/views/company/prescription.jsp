<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prescription</title>
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
	margin-bottom: 5px;
}

#tablebox {
	width: 1150px;
	margin: 0 auto;
}

#tablebox h1 {
	border-bottom: 7px double #999;
	margin-top: 30px;
	align-content: center;
	text-align: center;
}

#required {
	font-size: 17px;
	border-spacing: 15px;

}

#required .cell2 {
	text-align: center;
}

#list .cell {
	text-align: center;
	padding: 5px;
}

#content {
	width: 1200px;
	background-color: white;
	padding-top:30px;
	height: 64%;
}
#subbtn {
	float:right;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/views/inc/companyheader.jsp"%>
		<section id="main">
			<h1>처방전 작성하기</h1>
			<div id="content">
				<div id="tablebox">
					<form method="POST" action="/fanimal/company/prescription.do">
						<table class="table animal" id="required">

							<tr>
								<td colspan="2"><h2>${dto.name} 님</h2></td>
							</tr>

							<tr>
								<td class="cell2">성 분</td>
								<td><select name="type" id="type" class="form-control"
									onclick="del()" onchange="comChange(this)" required>
										<option value="" disabled selected>선택하기</option>
										<c:forEach items="${clist}" var="cdto">
											<option value="${cdto.iseq}">${cdto.component}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td class="cell2">복용약</td>
								<td><select name="kind" id="kind" class="form-control"
									required>
										<option value="" disabled selected>선택하기</option>
								</select></td>
							</tr>
							<tr>
								<td class="cell2">용 법</td>
								<td><input type="text" name="usage" id="name"
									class="form-control" size="10" required></td>
							</tr>
							<tr>
								<td class="cell2">복용 주기</td>
								<td><input type="text" name="amount" id="name"
									class="form-control" size="10" required></td>
							</tr>
							<tr>
								<td class="cell2">처방 일수</td>
								<td><input type="text" name="pdate" id="name"
									class="form-control" size="10" required></td>
							</tr>
							<tr>
								<td class="cell2">처방전 유효기간</td>
								<td><input type="text" name="avail" id="name"
									class="form-control" size="10" required></td>
							</tr>
							<tr>
								<td class="cell2">설 명</td>
								<td><input type="text" style="width:1006px;height:100px;" name="explain" id="name"
									class="form-control" size="10" required></td>
							</tr>
						</table>
						<div id="subbtn">
							<input type="hidden" value="${seq}" name="seq">
							<button type="submit" class="btn btn-success" onclick="location.href='/fanimal/company/prescriptionview.do'">작성 완료</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</main>
	<script>

	function del() {
		//alert("hi");
		$('#kind').empty();
		
	}


	function comChange(e) {
		$.ajax({
			type: 'GET',
			url: '/fanimal/ajax/product.do',
			data: 'iseq=' + $(event.target).val(),
			dataType: 'json',
			success: function(result) {	
						
				$('#kind').append('<option value="" disabled selected>선택하기</option>');
				
				$(result).each(function(index, item) {
					
					$('#kind').append('<option value="' + item.pseq + '">' + item.pname + '</option>')
								
					
				});
									
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		});
		
		
	}

	</script>

</body>
</html>








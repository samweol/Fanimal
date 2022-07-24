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


#list1 {
	/* border: 1px solid #444; */
	padding-left: 35px;
	width: 1200px;
	/* width: 90%; */
	max-width: 100%;
	margin-left: auto;
	margin-right: auto;
	/* float: left; */
}

#list1 .image {
	/* border: 1px solid #444; */
	position: relative;
	width: 200px;
	height: 200px;
	border-radius: 50%;
	overflow: hidden;
}

#list1 .image>img {
	border: 1px solid #444;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	object-fit: cover;
}

#list1 .cell1 {
	font-size: 22px;
	font-weight: bold;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: 40px;
}

#list1 .cell2 {
	background-color: #81e5ab;
	border: 1px solid #444;
	font-size: 17px;
	text-align: center;
	height: 30px;
	position: relative;
	border-radius: 20px;
}

#list2 {
	/* border: 1px solid #444; */
	width: 400px;
	height: 197px;
	max-width: 100%;
	/* text-align: center; */
	/*margin-left: auto;
	margin-right: auto; */
	/* 	float: left; */
}

#list2 td {
	border: 1px solid #444;
	padding: 5px;
	font-size: 22px;
}

#list2 .cell1 {
	padding-left: 40px;
}

#list3 {
	border: 1px solid #444;
	width: 400px;
	height: 197px;
	background-color: #81e5ab;
	text-align: center;
	font-size: 19px;
	border-radius: 20px;
	margin-top: 20px;
}

#list4 {
	/* border: 1px solid #444; */
	/* width: 90%; */
	max-width: 100%;
	margin-left: auto;
	margin-right: auto;
	border-spacing: 10px;
	margin-top: 10px;
	margin-bottom: 10px;
}

#list4 td {
	/* border: 1px solid #444; */
	padding: 5px;
	font-size: 18px;
}

#list4 .cell1 {
	background-color: #81e5ab;
	width: 200px;
	height: 30px;
	border-radius: 20px;
	text-align: center;
	font-weight: bold;
}

#list4 .cell2 {
	/* background-color: #81e5ab; */
	width: 1000px;
	height: 30px;
	border-radius: 10px;
	padding-left: 20px;
	padding-top: 8px;
	padding-bottom: 8px;
}

#list5 {
	/* border: 1px solid #444; */
	position: relative;
	padding: 5px;
	width: 1200px;
	/* width: 90%; */
	max-width: 100%;
	margin-left: auto;
	margin-right: auto;
	/* margin-right:auto; */
}

.btn {
	padding: 10px 10px;
	float:right;
}

#main form {
	width: 1200px;
	height: 64%;
	max-width: 100%;
	margin-left: auto;
	margin-right: auto;
	padding: 15px;
	background-color: white;
}
</style>
</head>
<body>
	<main>
		<%@ include file="/WEB-INF/views/inc/companyheader.jsp"%>
		<section id="main">

			<h1>예약내역 확인</h1>
			<form method="GET" action="/fanimal/company/reservationlist.do" class="form">
			<div id="table">
				<table id="list1">
					<tr class="col1">
						<td class="image"><img src="/fanimal/files/reservation/${dto.pic}"/></td>
						<td class="cell1">${dto.name}님</td>
					</tr>
				</table>
				<table id="list4">

					<tr class="col1">
						<td class="cell1">방문 상태</td>
						<td class="cell2">${dto.visit}</td>
					</tr>
				</table>
				<table id="list4">

					<tr class="col1">
						<td class="cell1">보호자 성명</td>
						<td class="cell2">${dto.username}</td>
					</tr>
				</table>
				<table id="list4">
					<tr class="col1">
						<td class="cell1">연락처</td>
						<td class="cell2">${dto.tel}</td>
					</tr>
				</table>
				<table id="list4">
					<tr class="col1">
						<td class="cell1">방문 목적</td>
						<td class="cell2">${dto.purpose}</td>
					</tr>
				</table>
				<table id="list4">
					<tr class="col1">
						<td class="cell1">방문 일자</td>
						<td class="cell2">${dto.resdate}</td>
					</tr>
				</table>
				<table id="list4">
					<tr class="col1">
						<td class="cell1">특이 사항</td>
						<td class="cell2">${dto.uniqueness}</td>
					</tr>
				</table>
				<table id="list5">
					<tr class=btns>
						<td class="btn"><input type="button" value="진단결과 작성하기"
							class="btn btn-success"
							onclick="location.href='/fanimal/company/prescription.do?seq=${dto.seq}'">
						</td>
						<td class="btn"><input type="button" value="돌아가기"
							class="btn btn-secondary"
							onclick="location.href='/fanimal/company/reservationlist.do'">

						</td>
					 </tr>
				</table>
				</div>
			</form>
		</section>



	</main>
	<script>
		
	</script>

</body>
</html>






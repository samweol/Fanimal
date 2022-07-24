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
	height:100%;
	/* width: 940px; */
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

#main eform {
	padding: 15px;
	background-color: white;
	margin-left: auto;
	margin-right: auto;
	
}

#list1 {
	width: 940px;
	font-size: 17px;
	border-spacing: 15px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 5px;
	margin-bottom: 15px;
	
}

#list1 .cell1 {
	background-color: #81e5ab;
	font-size: 17px;
	height: 30px;
	width: 150px;
	text-align: center;
	border-radius: 10px;
	padding: 5px;
	position: relative;
	border-radius: 20px;
}

#list1 .cell2 {
	text-align: center;
}

#list1 .cell3 {
	padding-left: 20px;
}

#list1 .cell4 {
	padding-left: 20px;
	width: 120px;
}

#list1 .cell5 {
	width: 120px;
	padding-right: 20px;
}

#list1 .cell6 {
	padding-left: 10px;
}

#list1 .cell7 {
	width: 300px;
	padding-left: 20px;
}

#list1 .cell7 {
	width: 300px;
	padding-left: 20px;
}

#list .checkbox {
	width: 500px;
}

.btn {
	margin-top: 10px;
	position: right;
	float: right;
}

#list .text {
	width: 400px;
	height: 800px;
}

#list1 .cell12 {
	width: 150px;
	background-color: #81e5ab;
	font-size: 17px;
	height: 30px;
	width: 150px;
	text-align: center;
	border-radius: 10px;
	padding: 5px;
	position: relative;
	border-radius: 20px;
	float: left;
}

#list1 .cell10 {
	padding-top: 10px;

}

</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/views/inc/companyheader.jsp"%>
		<section id="main">
			
				<h1>기업 등록 신청</h1>
				<form method "POST" action="/fanimal/company/enrollment.do" class="eform">
				<table id="list1">

					<tr>
						<td class="cell1">기업명</td>
						<td class="cell3" colspan="3"><input type="text"
							name="hosname" required></td>

					</tr>
				</table>
				<table id="list1">
					<tr>
						<td class="cell1">인허가번호</td>
						<td class="cell3" colspan="3"><input type="text"
							name="license" required></td>
					</tr>
				</table>
				<table id="list1">
					<tr>
						<td class="cell1">운영시간</td>
						<td class="cell4"><input type="text" name="starttime"
							required></td>
						<td class="cell6">~</td>
						<td class="cell5"><input type="text" name="endtime" required></td>
						<td class="cell1">휴게시간</td>
						<td class="cell2">9:00 ~ 16:00</td>
					</tr>
				</table>
				<table id="list1">
					<tr>
						<td class="cell1">요일</td>
						<td class="cell7"><input type="checkbox" name="open" value="1">월 <input
							type="checkbox" name="open" value="2">화 <input
							type="checkbox" name="open" value="3">수 <input
							type="checkbox" name="open" value="4">목 <input
							type="checkbox" name="open" value="5">금 <input
							type="checkbox" name="open" value="6">토 <input
							type="checkbox" name="open" value="7">일</td>
						<td class="cell8">/</td>
						<td><input type="checkbox" name="open" value="8">평일 <input
							type="checkbox" name="open" value="9">주말</td>
					</tr>
				</table>
				<table id="list1">
					<tr>
						<td class="cell12">소 개</td>
					</tr>
					<tr>
						<td class="cell10"><input type="text" name="content" class="text" style="width:500px;height:200px;" required></td>
						<td><button type="submit" class="btn btn-success" id="btn">등록하기</button></td>
					</tr>
				</table>
				
			</form>
		</section>

	</main>
	<script>
		
	</script>

</body>
</html>






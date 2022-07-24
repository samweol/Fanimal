<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reservationlist</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
		body {
			margin-bottom: 100px;
		}
		body, div, h1, th, td {
			font-family: Verdana;
			color: #444;
		}
		#main {
			width: 800px;
			margin: 0 auto;
		}
		#main h1 {
			font-variant: small-caps;
			border-bottom: 1px dashed #999;
			margin-top: 30px;
         	align-content: center;
			text-align: center;
		}

		#list1 {
			border: 1px solid #ccc;
			border-collapse: collapse;
			border-left: 0px;
			border-right: 0px;			
			width: 800px;
		}

		#list1 th, #list1 td {
			border: 1px solid #ccc;
			border-left: 0px;
			border-right: 0px;
			padding: 5px;
			font-size: 14px;
		}

		#list1 .col1 { width: 50px; }
		#list1 .col2 { width: 100px; }
		#list1 .col3 { width: 80px; }
		#list1 .col4 { width: 100px; }
		#list1 .col5 { width: 50px; }

		#list1 td { text-align: center; }
		/* #list3 .cell5 { padding-left: 10px; } */
		#list1 th { background-color: #ddd; }

		#list1 tr:hover { background-color: #eee; }

	</style>
</head>
<body>

	<main>
	<%@ include file ="/WEB-INF/views/inc/header.jsp" %>
	
	<div id="main">

		<h1>예약내역</h1>
		<table id="list1">
			<tbody><tr>
				<th class="col1">번호</th>
				<th class="col2">반려동물이름</th>
				<th class="col3">방문목적</th>
				<th class="col4">방문일자</th>
				<th class="col5">방문여부</th>
			</tr>
			<tr>
				<td class="cell1">01</td>
				<td class="cell2">똘이</td>
				<td class="cell3">접종</td>
				<td class="cell4">2022-07</td>
				<td class="cell5">방문예정</td>
			</tr>
			<tr>
				<td class="cell1">02</td>
				<td class="cell2">Magni</td>
				<td class="cell3">22</td>
				<td class="cell4">Facere</td>
				<td class="cell5">방문예정</td>
			</tr>
			<tr>
				<td class="cell1">03</td>
				<td class="cell2">Nam</td>
				<td class="cell3">23</td>
				<td class="cell4">Laudantium</td>
				<td class="cell5">방문예정</td>
			</tr>
			<tr>
				<td class="cell1">04</td>
				<td class="cell2">Beatae</td>
				<td class="cell3">24</td>
				<td class="cell4">Esse</td>
				<td class="cell5">방문예정</td>
			</tr>
			<tr>
				<td class="cell1">05</td>
				<td class="cell2">Placeat</td>
				<td class="cell3">25</td>
				<td class="cell4">Itaque</td>
				<td class="cell5">방문예정</td>
			</tr>
			<tr>
				<td class="cell1">06</td>
				<td class="cell2">Eligendi</td>
				<td class="cell3">26</td>
				<td class="cell4">Velit</td>
				<td class="cell5">방문예정</td>
			</tr>
			<tr>
				<td class="cell1">07</td>
				<td class="cell2">Labore</td>
				<td class="cell3">27</td>
				<td class="cell4">Doloribus</td>
				<td class="cell5">방문예정</td>
			</tr>
			<tr>
				<td class="cell1">08</td>
				<td class="cell2">Ducimus</td>
				<td class="cell3">28</td>
				<td class="cell4">Ea</td>
				<td class="cell5">방문예정</td>
			</tr>
			<tr>
				<td class="cell1">09</td>
				<td class="cell2">Et</td>
				<td class="cell3">29</td>
				<td class="cell4">Nostrum</td>
				<td class="cell5">방문예정</td>
			</tr>
			<tr>
				<td class="cell1">10</td>
				<td class="cell2">Saepe</td>
				<td class="cell3">21</td>
				<td class="cell4">Voluptatibus</td>
				<td class="cell5">방문예정</td>
			</tr>
		</tbody></table>

	</div>
	</main>
	<script>
		
	</script>

</body>
</html>












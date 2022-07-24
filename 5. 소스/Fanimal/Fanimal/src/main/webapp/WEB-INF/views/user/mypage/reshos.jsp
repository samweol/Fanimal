<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="/fanimal/asset/css/user/reshos.css">
<title>Insert title here</title>
<style>

</style>
</head>
<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
            <div id="content">					
				
				<h3 style="margin-top: 0; padding-top: 30px">예약 내역</h3>
				<div id="resdiv">
				
					<c:forEach items="${reslist}" var="dto">
					<a href="/fanimal/user/mypage/viewreshos.do?rhseq=${dto.rhseq}" style="color: black;">
					<table class="resbox">
					
						<tr>
							<td colspan="2">${dto.name}</td>	
						</tr>
						<tr>
							<td>${dto.hpname}</td>
							<td>${dto.resdate}</td>												
						</tr>
						<tr>
							<%-- <td colspan="2"><input type="button" value="예약취소" class="btn btn-danger" onclick="event.preventDefault(); location.href='/fanimal/user/mypage/delreshos.do?rhseq=${dto.rhseq}';"></td> --%>
							<td colspan="2"><input type="button" value="예약취소" class="btn btn-danger" onclick="event.preventDefault(); delcheck(${dto.rhseq});"></td>
						</tr>
					</table>
					</a>					
					</c:forEach>
					<c:if test="${reslist.size() == 0}">
					<h4>예약내역이 없습니다.</h4>					
					</c:if>
					
				</div>		
				
				
				<h3 style="margin-top: 0; padding-top: 30px">지난 예약 내역</h3>
				<div id="beforediv">
					<c:forEach items="${beflist}" var="dto">
					<table class="beforebox">
						<tr>
							<td colspan="2">${dto.name}</td>	
							<td>병원:  ${dto.hpname}</td>
							<td><span>증상:</span><span>  ${dto.uniqueness}</span></td>
							<td>예약날짜:  ${dto.resdate}</td>
						</tr>											
					</table>
					</c:forEach>					
					<c:if test="${beflist.size() == 0}">
					<h4>예약내역이 없습니다.</h4>					
					</c:if>
					
					<div style="text-align: center">
				
						 ${pagebar}
					</div>
				</div>
            </div>
        </section>
       <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
    </main>
    <script>
    $('#pagebar').change(function() {
		
		location.href = '/fanimal/user/mypage/reshos.do?page=' + $(this).val();
		
	});
    
    
    function delcheck(e) {
		var answer;
		var num = e;
		//페이지를 이동하기 전에 confirm()을 사용해 다시 한번 확인한다.
		//확인을 선택하면 answer에  true, 취소를 선택하면 false 값이 들어간다.
		answer = confirm("정말 삭제하시겠습니까?");
		//확인을 선택한 경우 자바스크립트를 호출할 때 같이 넘어온 url이라는 변수에 들어있는 주소로 페이지 이동
		if(answer == true){
			/* location.href = '/fanimal/user/mypage/delreshos.do?uaseq=${uaseq}&rhseq=${rhseq}'; */
			location.href = '/fanimal/user/mypage/delreshos.do?rhseq=' + num;
							
		}
	}
    
    </script>    
</body>
</html>
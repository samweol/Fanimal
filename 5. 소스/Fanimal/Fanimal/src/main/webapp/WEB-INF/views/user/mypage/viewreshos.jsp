<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="/fanimal/asset/css/user/viewreshos.css">
<title>Insert title here</title>
<style>

</style>
</head>
<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
            <div id="content">					
				
				<!--  -->
				<table class="table" id="tblview">
				
					<tr>
                		<td colspan="2"><h2>예약 정보</h2></td>
                	</tr>
                	
                	
                	<tr>
                		<td><div>이름</div></td>
                		<td>${dto.name}</td>
                		<td rowspan="4" colspan="2">
                			<!-- <div><img src="/fanimal/asset/images/user/animallist/ddol2.jpg" id="aniprofile"></div> -->
                			<div style="background-image: url('/fanimal/files/reservation/${dto.pic}')"></div>
                		</td>
                	</tr>
                	<tr>
                		<td><div>예약날짜</div></td>
                		<td>${dto.resdate}</td>
                	</tr>
                	<tr>
                		<td><div>병원명</div></td>
                		<td>${dto.hosname}</td>                		
                	</tr>
                	<tr>
                		<td><div>방문목적</div></td>
                		<td>${dto.purpose}</td>
                	</tr>
                	<tr>
                		<td><div>증상</div></td>
                		<td>${dto.uniqueness}</td>
                	
                		<td colspan="4" id="btnbox" style="text-align:right;">
                		<input type="button" class="btn btn-danger" value="예약취소" onclick="delcheck()">                		
                		</td>
                	</tr>
	            </table>
				
				
				
				

            </div>
        </section>
       <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
    </main>    
    <script>
    
    	function delcheck() {
    		var answer;
    		//페이지를 이동하기 전에 confirm()을 사용해 다시 한번 확인한다.
    		//확인을 선택하면 answer에  true, 취소를 선택하면 false 값이 들어간다.
    		answer = confirm("정말 삭제하시겠습니까?");
    		//확인을 선택한 경우 자바스크립트를 호출할 때 같이 넘어온 url이라는 변수에 들어있는 주소로 페이지 이동
    		if(answer == true){
    			/* location.href = '/fanimal/user/mypage/delreshos.do?uaseq=${uaseq}&rhseq=${rhseq}'; */
    			location.href = '/fanimal/user/mypage/delreshos.do?rhseq=${rhseq}';
    							
    		}
    	}	
    
    </script>
</body>
</html>
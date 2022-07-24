<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="/fanimal/asset/css/user/viewanimal.css">
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
                		<td colspan="2"><h2>반려동물 정보</h2></td>
                	</tr>
                	
                	
                	<tr>
                		<td><div>이름</div></td>
                		<td>${viewdto.name}</td>
                		<td rowspan="6" colspan="2">
                			<!-- <div><img src="/fanimal/asset/images/user/animallist/ddol2.jpg" id="aniprofile"></div> -->
                			<%-- <div><img src="/fanimal/files/${viewdto.pic}" id="aniprofile"></div> --%>
                			<div style="background-image: url('/fanimal/files/${viewdto.pic}')"></div>
                		</td>
                	</tr>
                	<tr>
                		<td><div>분류</div></td>
                		<td>${viewdto.type}</td>
                	</tr>
                	<tr>
                		<td><div>품종</div></td>
                		<td>${viewdto.kind}</td>
                	</tr>
                	
                	<tr>
                		<td><div>성별</div></td>
                		<c:if test="${viewdto.gender == 'm'}">
                		<td>수컷</td>
                		</c:if>
                		<c:if test="${viewdto.gender == 'f'}">
                		<td>암컷</td>
                		</c:if>
                	</tr>
                	<tr>
                		<td><div>나이</div></td>
                		<td>${viewdto.age}살</td>
                	</tr>
                	<tr>
                		<td><div>태어난 날</div></td>
                		<td>${viewdto.birth}</td>
                	</tr>
                	<tr>                		
                		<td><div>중성화</div></td>
                		<c:if test="${viewdto.neutral == 'y'}">
                		<td>여(o)</td>
                		</c:if>
                		<c:if test="${viewdto.neutral == 'n'}">
                		<td>부(x)</td>
                		</c:if>
                		<td class="hbox" id="hbox"><div id="hdiv">보유질환</div></td>
                		<td id="tbox">${viewdto.dname}</td>
                	</tr>
                	<tr>
                		<td><div>몸무게</div></td>
                		<td>${viewdto.weight}kg</td>
                		<td class="hbox" id="hbox2"><div id="hdiv2">사망여부</div></td>
                		<c:if test="${viewdto.state == 'y'}">
                		<td id="tbox2">여(o)</td>
                		</c:if>
                		<c:if test="${viewdto.state == 'n'}">
                		<td id="tbox2">부(x)</td>
                		</c:if>
                		
                	</tr>
                	
                	<tr>
                		<td colspan="4" id="btnbox" style="text-align:right;">                		
                		<input type="button" class="btn btn-warning" value="케어일기" onclick="location.href='/fanimal/cdiary/list.do?uaseq=${uaseq}'">
                		<input type="button" class="btn btn-success" value="수정하기" onclick="location.href='/fanimal/user/mypage/editanimal.do?uaseq=${uaseq}'">
                		<input type="button" class="btn btn-secondary" value="삭제하기" onclick="delcheck()">                		
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
    			location.href = '/fanimal/user/mypage/delAnimal.do?uaseq=${uaseq}';
    		}
    	}	
    
    </script>
</body>
</html>
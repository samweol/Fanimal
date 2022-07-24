<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>

</style>
</head>
<body>
<main>
	<%@ include file ="/WEB-INF/views/inc/adminheader.jsp" %>
	<section id="main">
	
		<h1>기업 승인 상세보기</h1>
		<form method="GET" action="/fanimal/admin/companyapprovecheck.do">
	<table id="list1">

         <tr>
            <td class="cell1">기업명</td>
            <td class="cell3" colspan="3">${dto.hosname}</td>
         </tr>
         <tr>
            <td class="cell1">인허가번호</td>
            <td class="cell3" colspan="3">${dto.license}</td>
         </tr>
         <tr>
            <td class="cell1">소개</td>
            <td class="cell3" colspan="3">${dto.info}</td>
         </tr>
         <tr>
            <td class="cell1">운영시간</td>
            <td class="cell2">${dto.starttime}~${dto.endtime}</td>
            <td class="cell1">휴게시간</td>
            <td class="cell2">14:00~15:00</td>
         </tr>
         <tr>
            <td class="cell1">승인상태</td>
            <td class="cell3" colspan="3">${dto.stat}</td>
         </tr>
            

   	</table>
   	
   	<button type="button" class="btn btn-success" onclick="location.href='/fanimal/admin/companyapprovelist.do'">뒤로가기</button>
	<input type="hidden" name="seq" value="${dto.seq}">
	</form>
	
	</section>
	

	</main>
	
	<script>
	$('#testBtn').click(function(e){
		e.preventDefault();
		$('#testModal').modal("show");
	});
	
	$(document).ready(function(){
	    function alignModal(){
	        var modalDialog = $(this).find(".modal-dialog");
	        
	        // Applying the top margin on modal dialog to align it vertically center
	        modalDialog.css("margin-top", Math.max(0, ($(window).height() - modalDialog.height()) / 2));
	    }
	    // Align modal when it is displayed
	    $(".modal").on("shown.bs.modal", alignModal);
	    
	    // Align modal when user resize the window
	    $(window).on("resize", function(){
	        $(".modal:visible").each(alignModal);
	    });   
	});
	
	$('#testBtn1').click(function(e){
		e.preventDefault();
		$('#testModal1').modal("show");
	});
	

	</script>
</body>
</html>






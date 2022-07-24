<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/fanimal/asset/css/company_style.css">
<script src="/fanimal/asset/js/jquery-1.12.4.js"></script>
<script src="https://kit.fontawesome.com/3ac15bdbeb.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<style>
        .col {
            width: 150px;
            padding: 5px;
            background-color: #81E5AB;
            border-radius: 10px;
            text-align: center;
            margin-bottom: 10px;
        }

        .content {
            padding: 5px;
            margin-left: 5px;
            width: 300px;
        }
        
        #content {
        	height: calc(100vh - 319px);
        }
        
        .pure-menu-link {
    	color: black;
		}
</style>
<title>Insert title here</title>
<link rel="icon" type="image/x-icon" href="/fanimal/pic/dog.ico">
</head>
<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/companyheader.jsp"%>
		<section>
	            <div id="content">
	            <c:if test = "${not empty dto }">
	            <div style="display: flex; justify-content: center; font-size: 30px; padding-top: 50px;"><i style="padding-top: 5px;" class="fa-solid fa-paw"></i> 병원페이지</div>	
	            	<div style="display: flex; flex-direction: column; margin: 0 auto; width: 450px; padding-top: 20px;">
			            <div style="display: flex; justify-content: center; margin-top: 20px;">
			                <div style="font-size: 20px; margin-top: 10px;"><i class="fa-solid fa-paw"></i> ${dto.stat }</div>
			            </div>
			            <div style="display: flex; margin-top: 20px;">
			                <div class="col">병원이름</div>
			                <div class="content">${dto.hosname }</div>
			            </div>
			            <div style="display: flex;">
			                <div class="col">인허가번호</div>
			                <div class="content">${dto.license }</div>
			            </div>
			            <div style="display: flex;">
			                <div class="col">운영시간</div>
			                <div class="content">${dto.starttime } ~ ${dto.endtime }</div>
			            </div>
			            <div style="display: flex;">
			                <div class="col">요일</div>
			                <div class="content">${mlist}</div>
			            </div>
			            <div style="display: flex;">
			                <div class="col">소개 및 설명</div>
			                <div class="content" id="txtinfo">${dto.info }</div>
			                <div><i class="fa-solid fa-pen" onclick="updateinfo('${auth.id}');" style="cursor: pointer;"></i></div>
			            </div>
			            <c:if test = "${dto.stat != '승인'}">
			            <div><input type="button" value="취소하기" onclick="location.href='/fanimal/company/cancelenrollment.do?id=${auth.id}';"></div>
			            </c:if>
        			</div>
        			</c:if>	   
        			<c:if test = "${empty dto }">
        			<div style="display: flex; justify-content: center; font-size: 30px; padding-top: 50px;"><i style="padding-top: 5px;" class="fa-solid fa-paw"></i> 병원페이지</div>
        			<div style="display: flex; justify-content: center; padding-top: 80px;">병원정보가 없습니다.</div>	
        			</c:if>        
	            </div>
	        </section>
	        <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
        </main>
        <script>
        let isEdit = false;
        function updateinfo(id) {
    		const tempStr = $(event.target).parent().prev().text();
    		/* alert(tempStr); */ 
    		$(event.target).parent().parent().after(temp);
    		
    		isEdit = true;
    		
    		$(event.target).parent().parent().next().find('textarea').val(tempStr);
    		$(event.target).parent().parent().next().find('input[name=id]').val(id);
    	}
    	
    	temp =`<div id="editRow" style="display: flex; margin-left: 165px;">
        	<form id="editForm">
    		<textarea name="info" id="info"></textarea>
    		<input type="hidden" name="id">
    		<button onclick="update();">수정하기</button>
    		<button onclick="cancelForm();">취소하기</button>
    	</form>
    </div>`;
    	
    	function update() {
    		$.ajax({
    			type: 'POST',
    			url: '/fanimal/company/updatecompanypage.do',
    			data: $('#editForm').serialize(),
    			dataType: 'json',
    			success: function(result) {
    				if(result.result == '1') {
    					
    					$('#txtinfo').text($('#info').val());
    					$('#editRow').remove();
    					
    					isEdit = false;
    					
    				}
    			},
    			error: function(a, b, c) {
    				console.log(a, b, c);
    			}
    		});
    	}
    	
    	function cancelForm() {
            $('#editRow').remove();
            isEdit = false;
        }

    	</script>
</body>
</html>
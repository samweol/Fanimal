<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/fanimal/asset/css/hospitalReservation.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>


 <style>

.hospital-reservation-title {
	padding: 50px 0 30px 0;
}
.reservation-sel-animal#ui-id-2{
	padding : 20px 70px;
} 
.hospital-reservation h3 {
	height: 45px;
	background-color: gold;
	border: white;
	border-radius: 10px;
	color: black;
}
.hospital-reservation .ui-accordion-header {
	font-size: 180%;
	font-family: 'CookieRun-Regular';
}
.hospital-reservation .card:hover {
	border: 3px solid black;
}   

.btn.reserv-ani-complete {
	font-family: 'CookieRun-Regular';
	width: 150px;
	font-size: 15px;
	padding: 5px;
	display: none;
	border: 2px solid #623D2B;
	border-radius: 10px;
	color: white;
	float: right;
	background-color: #623D2B;
	margin-bottom: 30px;
}
.hospital-reservation {
	height: auto;
	
} 
#reservation-datepicker {
	width: 400px;
	margin: 70px 0 70px 70px;
	display: inline-block;
}
#reservation-datepicker .ui-state-default {
	border-radius: 20px;
	margin: 10px;
    font-size: 15px;
    padding: 6px;
}
.hospital-reservation .ui-datepicker {
	width: auto;
	border-radius: 20px;
    box-shadow: 0 1px 0px 0 rgb(0 0 0 / 20%), 0 3px 5px 0 rgb(0 0 0 / 19%);
}
.hospital-reservation .ui-widget.ui-widget-content {
	border: 0px;
}
.hospital-reservation .ui-datepicker .ui-datepicker-header {
    height: 50px;
    font-size: 20px;
    border-radius: 20px;
    margin-bottom: 10px;
    box-shadow: 0 1px 0px 0 rgb(0 0 0 / 20%), 0 3px 10px 0 rgb(0 0 0 / 19%);
}
#reservation-datepicker > div > table > thead > tr {
	font-size: 18px;
}

.hospital-reservation a.ui-state-default.ui-state-active {
    background-color: #666;
	color: white; 
	border: 1px solid black;
	box-shadow: 0px 0px 3px black;
	
}
.hospital-reservation a.ui-state-default {
	box-shadow: 0px 0px 3px grey;
}
.reservation-sel-other h4 { 
	padding: 50px 0 15px 70px;
	font-size: 20px; 
}
.reservation-sel-other {
	font-size: 16px; 
}
.reservation-sel-purpose {
	margin-left: 70px;
}
.reservation-sel-other label {
	font-weight: normal;
	margin-right: 20px;
}
.reservation-sel-other input[name=purpose] {
	margin-right: 10px;
	width: 18px;
    height: 1.3em;
    vertical-align: -5px;
}
.reservation-sel-other input[name=picture] {
	margin-left: 70px;
	width: 600px;
	font-family: 'CookieRun-Regular'; 
	padding: 10px;
	height: 50px;
}
.reservation-sel-other textarea[name=uniqueness] {
	margin-left: 70px;
	margin-bottom: 20px;
	font-family: 'CookieRun-Regular'; 
	width: 600px;
	resize: none;
}
.reservation-sel-time {
	color: black;
    display: inline-block;
    width: 400px;
    font-size: 20px;
    margin-top: 70px;
    padding: 10px;
    float: right;
    margin-right: 80px;
    background-color: #E9E9E9;
    height: 450px;
    border-radius: 20px;
    box-shadow: 0px 1px 0px 0 rgb(0 0 0 / 20%), 0 3px 5px 0 rgb(0 0 0 / 19%);
}
.reservation-sel-time span.badge {
    padding: 10px;
    margin: 10px;
    width: 100px;
    box-shadow: 0px 0px 3px grey;
    display: none;
}
.reservation-sel-time span.badge:hover {
	background-color:#EEE;
}
#reserv-complete {
	display: inline-block;
}

 </style>

</head>

<body>
	<main>
		<c:if test="${empty auth.id }">
		<%@ include file ="/WEB-INF/views/inc/header.jsp"%>
		</c:if>
		<c:if test="${not empty auth.id }">
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		</c:if>
		<section>
        	<div id="content">
        	
        	<h1 class="hospital-reservation-title">${hosname}</h1>
        	
			<form  enctype="multipart/form-data" class="hospital-reservation">
			<div id="reservation-accordion" style="padding-bottom: 30px;" >
				<!-- 반려동물 선택 -->
				<h3>진료 동물 <span></span>
				</h3>
  				<div class="reservation-sel-animal">
    
					<div class="row">
						<c:forEach items="${animals}" var="animal">
				        <div class="col-sm-4">
				        <div class="card" data-uaseq="${animal.uaseq}">
				          	<div class="card-header" 
								style="background: url('/fanimal/files/reservation/${animal.pic}'); background-size: 100% 280px; background-repeat: no-repeat;">
								<div class = "card-header-is_closed"></div>
							</div>
				            <div class="card-body">
								<h1><i class="fa-solid fa-paw"></i> ${animal.name}</h1>
								<p class="card-body-hashtag">
									${animal.kind} / <c:if test="${animal.gender == 'm'}">수컷</c:if><c:if test="${animal.gender == 'f'}">암컷</c:if> / ${animal.age}살
								</p>
				            </div>
				        </div>
				        </div>
				        </c:forEach> 
				     </div>
					<button type="button" onclick="moveNext(1);" class="btn reserv-ani-complete">선택 완료</button>
			  	</div>
			  
			  	<!-- 날짜시간 선택 -->
			  	<h3>예약 날짜 <span></span></h3>
	      		<div class="reservation-sel-date">
	      		
	      			<div id="reservation-datepicker"></div>
 					<div class="reservation-sel-time">
 					<c:forEach items="${restime}" var="time">
 						<c:if test="${time == '14:00'}"> <!-- 점심시간 전후 구분 -->
 						<hr>
 						</c:if>
 						<span type="button" class="badge badge-pill badge-light" onclick="setTime('${time}')">${time}</span>
 					</c:forEach>
 					</div>
 					
			 		<button type="button" onclick="moveNext(2);" class="btn reserv-ani-complete">선택 완료</button> 	
	     	 	</div>
 				
				<!-- 그외 선택 -->
  				<h3>진료 목적</h3>
    			<div class="reservation-sel-other">
      
		      		<h4>방문 목적</h4>
		      		<div class="reservation-sel-purpose">
				  		<c:forEach items="${purposes}" var="p">
				  		<label><input type="radio" name="purpose" class=".form-check" value="${p.pseq}">${p.purpose}</label>
				  		</c:forEach>
		      		</div>
			  		
				 	<h4>증상 사진</h4>
			  		<input type="file" name="picture" class="form-control" accept="image/*">
			  		
			  		
			  		<h4>특이 사항</h4>
			  		<textarea name="uniqueness" class="form-control" rows="5" placeholder="특이사항이 있을 시 반드시 작성해주세요."></textarea>
					 
					 
					<button type="button" class="btn btn-warning reserv-ani-complete" id="reserv-complete">예약하기</button>
      			</div>
			</div>
			<input type="hidden" name="uaseq">
			<input type="hidden" name="resdate">
			<input type="hidden" name="restime">
			<input type="hidden" name="hpseq" value="${hpseq}">
			</form>
			</div>
        </section>
        
        
        <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
    </main>
	
	<script>
	
		//아코디언
		$( function() {
			$('#reservation-accordion').accordion();
			 
		});
		
		$('#reservation-accordion').accordion({
			heightStyle: "content"
		});
		
		$('.hospital-reservation .card').click(function(){
			$('.card').css('border', '0px');
			$(event.target).parents('.card').css('border', '3px solid black');
			let name = $(event.target).parents('.card').children('.card-body').find('h1').text();
			$('.reservation-sel-animal').prev().children('span').text(': ' + name );
			$('input[name=uaseq]').val($(event.target).parents('.card').data('uaseq'));
			
			$('.reserv-ani-complete').first().css('display','block');
		});
		
		$('#reservation-accordion').accordion({
			beforeActivate: function( event, ui ) {}
		});
		
		
		//데이트피커
		$(function () {
        	$('#reservation-datepicker').datepicker();
        });
	
		
		$('#reservation-datepicker').datepicker({
			minDate: "+1d",
		  	maxDate: "+1m",
		  	 monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		  	dayNamesMin: [  "일" , "월", "화", "수", "목", "금", "토"],
		  	buttonImage: "/images/datepicker.gif",
		  	showMonthAfterYear: true,
		  	dateFormat: 'yy-mm-dd',
		    regional: "ko",
		    beforeShowDay: function(date){				
		    	let day = date.getDay();				
		    	return [(${restdays})];			
		    },			
		    onSelect: function(dateText, inst) {
		    	$('input[name=resdate]').val($(this).val());
		        $('.reservation-sel-time span.badge').css('display', 'inline-block');
		        
		        
		        if ($('input[name=restime]').val() != ''){
		        	$('.reservation-sel-date').prev().children('span').text(' : ' + $(this).val() + ' ' + $('input[name=restime]').val());
		        }
		        
		    },
		});
		
		
		//사용자 정의
		function moveNext(num) {
			$('#reservation-accordion').accordion("option", "active", num);
		}
		
		function setTime(time) {
			let span = $('.reservation-sel-date').prev().children('span');
			let date = $('input[name=resdate]').val();
			
			$('input[name=restime]').val(time);
			
			if(date != ''){
				span.text(' : ' + date + ' ' + time);
				$('.reservation-sel-time span.badge').css({'background-color': 'white', color: 'black'});
				$(event.target).css({ 'background-color': '#666', color: 'white' });			
				$('.reserv-ani-complete').eq(1).css('display','block');
			}
		}
		
		
		$('#reserv-complete').click(function() {
			
			if($('input[name=uaseq]').val() == '') {
				moveNext(0);
				alert('진료 동물을 선택해주세요.');
				return;
			} else if ($('input[name=resdate]').val() == '' || $('input[name=restime]').val() == '') {
				moveNext(1);
				alert('예약 날짜와 시간을 선택해주세요.');
				return;
			} else if ($('input[name=purpose]:checked').val() == null) {
				alert('방문 목적을 선택해주세요.')
				return;
			} 
			
			
			$.ajax({
				type: 'POST',
				url: '/fanimal/reservation/reservationadd.do',
				data: new FormData($('.hospital-reservation')[0]),
				dataType: 'json',
				contentType: false,
				processData: false,
				success: function(result) {
					if (result.result == 1){
						location.href = '/fanimal/user/mypage/viewreshos.do?rhseq=' + result.rhseq;
					} else {
						alert('예약 실패');
					}
				},
				error: function(a, b, c){
					console.log(a, b, c);
				}
			});
			
		});
		
		
		
		
	</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=50afd7bb7d0ec954215a57b040f3f045"></script>
<link rel="stylesheet" href="/fanimal/asset/css/rateit.css">
<title>Insert title here</title>
<style>
#staticMap {
	width:300px;
	height:200px;
}
.hospitalview-info h1 {
	padding-top: 100px;
	margin-bottom: 20px;
}
.hospitalview-info-maintbl, .hospitalview-review table {
	background-color: white;
	font-size: 15px;
} 

.hospitalview-info-maintbl table td:nth-child(1) {
	background-color: gold;
	width: 80px;
	text-align:center;
}
.hospitalview-info-maintbl table td:nth-child(2) {
	padding-left: 15px;
}
.hospitalview-info-maintbl > tbody > tr > td:nth-child(2){
	width: 350px;
	padding-top:20px;
}

.hospitalview-info-maintbl .table-responsive-sm td:nth-child(1),
.hospitalview-info-maintbl .table-responsive-sm td:nth-child(2)  {
	background-color: white;
	text-align: left;
	padding: 4px;
}

.hospitalview-btn button {
	margin: 5px;
	padding: 8px;
	float: right;
	font-size: 15px;
	border-radius: 10px;
}
.hospitalview-btn {
	overflow: hidden;
}
.hospitalview-review { font-size: 14px; }

.hospitalview-review h2 {
	font-size: 25px;
	margin-bottom: 20px;
}


.hospitalview-review table td { vertical-align: middle; }
.hospitalview-review table td:nth-child(1) { width: 120px; padding-left: 20px; }
.hospitalview-review table td:nth-child(2) { width: 140px; }
.hospitalview-review table td:nth-child(3) { width: auto; }
.hospitalview-review table td:nth-child(4) { width: 100px; }
.hospitalview-review table td:nth-child(5) { width: 200px; }
.hospitalview-review textarea { resize: none; size: auto; font-size: 14px; }
.hospitalview-review table button { 	
	font-size: 15px;
	border-radius: 10px;
	padding: 8px;
	margin: 0 auto;
}
.hospitalview-review .review-edit {
	display: none;
	text-align: center;
}
.hospitalview-review tr:hover .review-edit {
	display: block;
}
.hospitalview-review button.btn-block {
	background-color: #DDD;
	width: 700px;
	margin: 0 auto;
	border-radius: 10px;
}


</style>
</head>
<body>
	<main>
		<c:if test="${empty auth.id}">
	    	<%@ include file ="/WEB-INF/views/inc/header.jsp"%>
	   	</c:if>
	   	<c:if test="${not empty auth.id}">
	    	<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
	   	</c:if>
		<section>
            <div id="content">
            	
            	<div class="hospitalview-info">
            		<h1>${info.hosname}</h1>

	            	<table class="table table-borderless hospitalview-info-maintbl">
	            		<tr>
	            			<td>
	            				<table>
	            					<tr>
	            						<td>전화번호</td>
	            						<td>${info.tel}</td>
	            					</tr>
	            					<tr>
	            						<td>진료시간</td>
	            						<td>
	            							<table  class="table-responsive-sm">
	            								<c:forEach items="${open}" var="day">
	            								<tr>
	            									<td>${day}</td>
	            									<td>${info.starttime} ~ ${info.endtime}</td>
	            								</tr>
	            								</c:forEach>
	            								<tr>
	            									<td>휴게 시간</td>
	            									<td>13:00 ~ 14:00</td>
	            								</tr>
	            							</table>
	            						</td>
	            					</tr>
	            					<tr>
	            						<td>주소</td>
	            						<td>${info.address}</td>
	            					</tr>
	            					<tr>
	            						<td>평점</td>
	            						<td>★ ${info.avgStar}</td>
	            					</tr>
	            					<tr>
	            						<td>병원소개</td>
	            						<td>${info.info}</td>
	            					</tr>
	            				</table>
	            			</td>
	            			<td>
								<div id="staticMap"></div>
	            			</td>
	            		</tr>
	            	</table>
	
	            	<div class="hospitalview-btn">
	            	<button type="button" onclick="location.href='/fanimal/inquire/hospitalinquirelist.do?hpseq=${hpseq}&si=${dto.si}&gu=${dto.gu}&align=${dto.align}&search=${dto.search}&page=${dto.page}'" class="btn btn-light">문의하기</button>
	            	<button type="button" onclick="location.href='/fanimal/reservation/reservation.do?hpseq=${hpseq}&si=${dto.si}&gu=${dto.gu}&align=${dto.align}&search=${dto.search}&page=${dto.page}'" class="btn btn-warning">예약하기</button>
	            	</div>
            	
            	</div>
            	
            	<div class="hospitalview-review">
            	<h2>리뷰 <span class="badge badge-pill badge-light">${reviewNum}</span></h2>
            	
            	<form>
            	<table class="table">
           			<c:if test="${not empty id}">
            		<tr class="review-add">
            			<td>${nickname}</td>
            			<td>
            				<span id="review-add-star" class="rateit" data-rateit-resetable="false"></span>
            			</td>
            			<td colspan="2">
            				<textarea name="review" rows="3" cols="50" class="form-control"></textarea>
            			</td>
            			<td>
            				<button type="button" onclick="addReview();" class="btn btn-warning">리뷰 작성</button>
            			</td>
            		</tr>
					</c:if>
            		<c:forEach items="${review}" var="rev">
            		<tr data-hrseq="${rev.hrseq}">
            			<td>${rev.nickname}</td>
            			<td><span class="rateit" data-rateit-resetable="false"  data-rateit-readonly="true" data-rateit-value="${rev.star}" ></span></td>
            			<td>${rev.review}</td>
            			<td>
            				<c:if test="${id == rev.id}">
							<div class="review-edit">
	            				<span onclick="editReview();">수정</span> | 
	            				<span onclick="delReview();">삭제</span>
	            				
							</div>
            				</c:if>
            			</td>
            			<td>
            				<span>${rev.redate}</span>
            			</td>
            		</tr>
            		</c:forEach>
            	</table>
            	<input type="hidden" name="star">
           		<input type="hidden" name="hpseq" value="${hpseq}">
            	</form>
            	
            	<c:if test="${reviewNum > 10}">
            	<button class="btn btn-block btn-light btn-lg" onclick="moreReview();">▼ 더보기</button>
            	</c:if>
            	
            	</div>
            	
            </div>
        </section>
        <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
    </main>
     
    <script src="/fanimal/asset/js/jquery.rateit.js"></script>
	<script>

		var markerPosition  = new kakao.maps.LatLng(${info.xcoor}, ${info.ycoor}); 

		var marker = {
		    position: markerPosition
		};

		var staticMapContainer  = document.getElementById('staticMap'), // 이미지 지도를 표시할 div  
		    staticMapOption = { 
		        center: new kakao.maps.LatLng(${info.xcoor}, ${info.ycoor}), // 이미지 지도의 중심좌표
		        level: 4, // 이미지 지도의 확대 레벨
		        marker: marker // 이미지 지도에 표시할 마커 
		    };    

		var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
		

		
		function moreReview() {
			
			let tbody = $('.hospitalview-review table tbody');
			let hrseq = $('.hospitalview-review table tr').last().data('hrseq');
			
			$.ajax({
				type: 'GET',
				url: '/fanimal/diagnosis/hospitalreview.do',
				data: 'hrseq=' + hrseq + '&hpseq=' + ${hpseq},
				dataType: 'json',
				success: function(result) {
					
					$(result).each(function(index, item){
						
						let temp = '';
						temp += '<tr data-hrseq=' + item.hrseq + '>';
						temp += '<td>' + item.nickname + '</td>'; 
						temp += '<td><span class="rateit rateit-bg" data-rateit-resetable="false"  data-rateit-readonly="true" data-rateit-value="' + item.star + '"></span></td>'; 
						temp += '<td>' + item.review + '</td>'; 
						temp += '<td>'; 
						if ("${id}" == item.id) {
							temp += '<div class="review-edit">'; 
							temp += '<span onclick="editReview();">수정</span> | <span onclick="delReview();">삭제</span>'; 
							temp += '</div>'; 
						}; 
						temp += '</td>'; 
						temp += '<td><span>' + item.redate + '</span></td>'; 
						temp += '</tr>';
						
						tbody.append(temp);
					});
					
					$('.rateit').rateit();
					
					if (${not empty auth.id}) { 
						if ($('.hospitalview-review table tr').length > ${reviewNum}){
							$('.hospitalview-review .btn-block').css('display', 'none');
						};
					} else {
						if ($('.hospitalview-review table tr').length >= ${reviewNum}){
							$('.hospitalview-review .btn-block').css('display', 'none');
						};
					}
					
				},
				error: function(a, b, c){
					console.log(a, b, c);
				}
			});
			
		}
		
		
		 $('#review-add-star').bind('rated', function (event, value) { 
			 $('.hospitalview-review input[name=star]').val(value);
		 })
		
		 
		function addReview() {
		
			let star = $('.hospitalview-review input[name=star]');
			let review = $('.review-add textarea[name=review]');
			
			if (star.val() == '') {
				alert('별점을 입력해주세요.');
				return;
			}

			if (review.val() == ''){
				alert('리뷰를 입력해주세요.');
				return;
			}
			
			
			$.ajax({
				type: 'GET',
				url: '/fanimal/diagnosis/hospitalreviewadd.do',
				data: $('.hospitalview-review form').serialize(),
				dataType: 'json',
				success: function(result) {
					
					if(result.result == 1) {
						
						let temp = '';
						temp += '<tr data-hrseq=' + result.hrseq + '>';
						temp += '<td>${nickname}</td>'; 
						temp += '<td><span class="rateit rateit-bg" data-rateit-resetable="false"  data-rateit-readonly="true" data-rateit-value="' + result.star + '"></span></td>'; 
						temp += '<td>' + result.review + '</td>'; 
						temp += '<td>'; 
						if ("${id}" == result.id) {
							temp += '<div class="review-edit">'; 
							temp += '<span onclick="editReview();">수정</span> | <span onclick="delReview();">삭제</span>'; 
							temp += '</div>'; 
						}; 
						temp += '</td>'; 
						temp += '<td><span>' + result.redate + '</span></td>'; 
						temp += '</tr>';						
						
						
						$('.review-add').after(temp);
						$('.rateit').rateit();
						
						if ($('.hospitalview-review table tr').length > 11) {
							
							$('.hospitalview-review table tr').last().remove();
						}
	
						//review-add 입력란 비우기
						review.val('');
						star.val('');
						$('#review-add-star').rateit('reset');
						
					} else {
						alert('죄송합니다. 리뷰 작성에 실패했습니다.');
					}
				},
				error: function(a, b, c){
					console.log(a, b, c);
				}
			}); 
		
		}
		
		 
		 function editReview() {
			 
			 
		 }
		 
		 function delReview() {
			 
			 let tr = $(event.target).parents('tr');
			 let hrseq = tr.data('hrseq');
			 let anoHrseq = $('.hospitalview-review table tr').last().data('hrseq');
			 
			$.ajax({
				type: 'GET',
				url: '/fanimal/diagnosis/hospitalreviewdel.do',
				data: 'hrseq='+ hrseq + '&hpseq=' + ${hpseq} + '&anoHrseq=' + anoHrseq,
				dataType: 'json',
				success: function(result) {
					
					if (result.result == 1 && result.hrseq != null) {
						
						tr.remove();
						
						let temp = '';
						temp += '<tr data-hrseq=' + result.hrseq + '>';
						temp += '<td>${nickname}</td>'; 
						temp += '<td><span class="rateit rateit-bg" data-rateit-resetable="false"  data-rateit-readonly="true" data-rateit-value="' + result.star + '"></span></td>'; 
						temp += '<td>' + result.review + '</td>'; 
						temp += '<td>'; 
						if ("${id}" == result.id) {
							temp += '<div class="review-edit">'; 
							temp += '<span onclick="editReview();">수정</span> | <span onclick="delReview();">삭제</span>'; 
							temp += '</div>'; 
						}; 
						temp += '</td>'; 
						temp += '<td><span>' + result.redate + '</span></td>'; 
						temp += '</tr>';	
						
						$('.hospitalview-review table tbody').append(temp);
						$('.rateit').rateit();				
						
					} else if (result.result == 1) {
						tr.remove();
					} else {
						alert('죄송합니다. 리뷰 작성에 실패했습니다.');
					}
					
					if ($('.hospitalview-review table tr').length > ${reviewNum}){
						$('.hospitalview-review .btn-block').css('display', 'none');
					};
					
					
				},
				error: function(a, b, c){
					console.log(a, b, c);
				}
			}); 
			
		 }
		 
		/*  function printReveiw(result) {
							
			let temp = '';
			temp += '<tr data-hrseq=' + result.hrseq + '>';
			temp += '<td>${nickname}</td>'; 
			temp += '<td><span class="rateit rateit-bg" data-rateit-resetable="false"  data-rateit-readonly="true" data-rateit-value="' + result.star + '"></span></td>'; 
			temp += '<td>' + result.review + '</td>'; 
			temp += '<td>'; 
			if ("${id}" == result.id) {
				temp += '<div class="review-edit">'; 
				temp += '<span onclick="editReview();">수정</span> | <span onclick="delReview();">삭제</span>'; 
				temp += '</div>'; 
			}; 
			temp += '</td>'; 
			temp += '<td><span>' + result.redate + '</span></td>'; 
			temp += '</tr>';						
					
			return temp;		
		 } */
		
	</script>
		
</body>
</html>
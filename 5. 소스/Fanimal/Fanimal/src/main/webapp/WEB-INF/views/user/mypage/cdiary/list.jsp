
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/fanimal/asset/css/list.css">
<link rel="icon" type="image/x-icon" href="/fanimal/pic/dog.ico">
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="/fanimal/asset/css/add.css">
<title>Insert title here</title>
<style>
.list-group-item1:first-child{
	width:150px;
	display:inline-block;
	margin-left:270px;
	margin-top:10px;
	color:cornflowerblue;
}
.list-group-item2:last-child {
	width:140px;
	display:inline-block;
	margin-left:20px;
	margin-top:10px;
	color:cornflowerblue;
}

.modal-content {
	width:900px;
}
#button2 {
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
  margin-left:10px;
   width:140px;

}

#puppy{
width:400px;
height:400px;
}

/*의사 코멘트 길어질때 여기서 조절해주기*/
#memo{
width:100px;
height:100px;
}
</style>
</head>
<body>
   


	<main>
		<%@ include file="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
			
			<c:forEach items="${dlist}" var="dto">
			<div class="container" style="padding-top: 30px;">
				<div class="row" style="max-width: 40vw;">
					<div class="col-md-3">
						<img src="/fanimal/pic/${dto.dogpic}" alt="Dog Profile Image"
							class="img-circle" id="dog_profile_img">
					</div>
					
					
					
					<div class="col-md-5">
						<div class="row">
							<div class="col-sm-3">
								<img src="/fanimal/pic/산책2.png" alt="Dog Icon"
									class="img-rounded" id="dog_icon">
							</div>
							
							
							<input id="hideASeq" type="hidden" value="${dto.useq}" />
							
							<div class="col-sm-3">
								<h4>${dto.name}</h4>
							</div>

							</div>
							<div class="row">
								<div class="col-sm-3">
									<p>성별 : </p>
								</div>
								<div class="col-sm-3">
									<p>${dto.gender}</p>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<p>나이 :  </p>
								</div>
	
								<div class="col-sm-3">
									<p>${dto.age}살 </p>
								</div>
	
							</div>
							<div class="row">
								<div class="col-sm-3">
									<p>몸무게 :</p>
								</div>
								<div class="col-sm-3">
									<p>${dto.dogweight} kg</p>
								</div>
							</div>
								
					</div>
				</div>
			</div>
			</c:forEach>
			
			
            
		
         <div class="container" style="padding-top: 30px;">
            <ul class="nav nav-tabs nav-justified">
            
         
               <li ><a href="/fanimal/cdiary/list.do">전체보기</a></li>
               <li><a href="/fanimal/cdiary/list.do?type=1">산책일기</a></li>
               <li style="position:relative; left:0; top:0;">
               		<a href="/fanimal/cdiary/list.do?type=2">케어일기</a>
               		<button type="button" class="btn" data-toggle="modal" data-target="#contentListPageModal" style="position:absolute; left: 300px; top: -20px;" onclick="start();">일기 쓰기<img src="/fanimal/pic/연필.png" style=" width: 30px; height: 20px;"></button>
               </li>
            </ul>
         </div>
         
         
        
        <!-- 여기 위치에 삽입 : 추가된 부분 !!! -->
		 <div class="modal fade" id="contentListPageModal" tabindex="-1" role="dialog" aria-labelledby="contentPageModalLabel" aria-hidden="true">
           <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">쓰고 싶은 일기를 고르시오.</h4>
                </div>
                <div class="modal-bodyh">
                    <div class="list-group">
                        <a href="#" class="list-group-item1" data-toggle="modal" data-target="#whywhy">케어일기 작성하기</a>
                        <a id="addWDiary" class="list-group-item2">산책일기 작성하기</a>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
                </div>
            </div>
          </div>
       </div>


			<!-- 리스트 출력 -->
			<div class="container" style="padding-top: 30px;">
				<div class="row d-flex w-100">
				
				
					
					
					<!-- 케어일기 -->
					<c:if test="${type == '2'}">
					<c:forEach items="${clist}" var="cdto">
						<span class="col-sm-3 flex-fill"> <a href="#"
							data-toggle="modal" data-target="#contentPageModal"
							id='modalparent'> <!-- 모달로 들어가는 링크 --> <img
								src="/fanimal/files/${cdto.picture}" alt="Dog Profile Image"
								class="rounded gallery" onclick="getDataToAjax(${cdto.cdSeq})"/>
						</a>
						</span>
					</c:forEach>
					</c:if>
		
										
					<!-- 산책일기 -->	
					<c:if test="${type == '1'}">
					<c:forEach items="${wlist}" var="wdto">
						<span class="col-sm-3 flex-fill">
						 <a href="#" data-toggle="modal"  data-target="#contentPageModal2" id='modalparent2'>   
							<img src="/fanimal/files/${wdto.pic}" alt="Dog Profile Image"
								class="rounded gallery" onclick="getDataToAjax2(${wdto.wseq})">
							</a>
						</span>
					</c:forEach>
					</c:if>
					
					
					
					
					<!-- 전체보기 -->
					<c:if test="${type == null}">
					<c:forEach items="${clist}" var="cdto">
						<span class="col-sm-3 flex-fill"> <a href="#"
							data-toggle="modal" data-target="#contentPageModal"
							id='modalparent'> <!-- 모달로 들어가는 링크 --> <img
								src="/fanimal/files/${cdto.picture}" alt="Dog Profile Image"
								class="rounded gallery" onclick="getDataToAjax(${cdto.cdSeq})">
						</a>
						</span>
					</c:forEach>
					
					
					<c:forEach items="${wlist}" var="wdto">
						<span class="col-sm-3 flex-fill">
						 <a href="#" data-toggle="modal"  data-target="#contentPageModal2" id='modalparent2'>   
							<img src="/fanimal/files/${wdto.pic}" alt="Dog Profile Image"
								class="rounded gallery" onclick="getDataToAjax2(${wdto.wseq})">
							</a>
						</span>
					</c:forEach>
					</c:if>
				</div>
			</div>




			<!-- Modal 1 -->
			<div class="modal fade" id="contentPageModal" tabindex="-1"
				role="dialog" aria-labelledby="contentPageModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="contentPageModalLabel"></h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="container modal-body">
							<div class="row">
								<div class="col-sm-5">
									<span id="picture"></span> <!-- picture -->
								</div>
								<div class="panel col-sm-4">
									<div class="panel-heading row flex-head">
										<div class="col-sm-1">
											<img src="/fanimal/pic/주사기2.png" alt="Dog Icon"
												class="img-rounded" id="dog_icon">
										</div>
										<div class="col-sm-6">
											<h3 id='dogName'></h3>
										</div>

										<div class="">
											<button type="button" class="btn btn-default pull-right">수정하기</button>
										</div>
									
									</div>
									<div class="panel-body" style="padding: 5px;">
										<h4 class="card-title">처방전</h4>
			         					   </div>
			                                <div class="panel-body" style="padding: 5px;">
			                                    <h3 style="margin: 0px;"></h3>
			                                    
			                                       <table >
			                                        <tr>
			                                            <th>약이름</th>
			                                            <td id="pname"></td>
			                                            <th>성분</th>
			                                            <td id="component"></td>
			                                        </tr>
			                                        <tr>
			                                            <th>투약일수</th>
			                                            <td id="pdate"></td>
			                                            <th>용법</th>
			                                            <td id="usage"></td>
			                                           
			                                        </tr>
							                            <tr>
				                                            <th>목적</th>
				                                            <td id="purpose"></td>
				                                        </tr>
			                                        <tr>
			                                            <th>접종</th>
			                                            <td id="vaccination"></td>
			                                            <th>수술</th>
			                                            <td id="surgery"></td>
			                                        </tr>
			                                    </table>
			                    
			                                    <div class="comment">
											       <h4>의사 코멘트</h4>
				                                    <p id="explain">
				                                        
				                                    </p>
												</div>
												 <div class="comment-">
				                                    <h4>나의 코멘트</h4>
				                                    <p id="content" style="width: inherit;">
				                                    
				                                    </p>
				                                </div>
										<footer class="pull-right" id="datetune"></footer>
									</div>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" onclick='del1();'>삭제하기</button><!-- 삭제하기 파트 구현 -->
							<button type="button" class="btn btn-default"
								data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
				
				
				
			<!-- Modal 2 -->
			<div class="modal fade" id="contentPageModal2" tabindex="-1"
				role="dialog" aria-labelledby="contentPageModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="contentPageModalLabel2"></h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="container modal-body">
							<div class="row">
								<div class="col-sm-5">
									<span id="pic"></span> <!-- picture -->
								</div>
								<div class="panel col-sm-4">
									<div class="panel-heading row">
										<div class="col-sm-1">
											<img src="/fanimal/pic/산책2.png" alt="Dog Icon"
												class="img-rounded" id="dog_icon">
										</div>
										<div class="col-sm-5">
											<h4 id='dogname2'></h4>
										</div>
										<div class="col-sm-3">
											<button type="button" class="btn btn-default pull-right">수정하기</button>
										</div>
									</div>
									<div class="panel-body container-fluid">
										<h5>&nbsp;&nbsp;오늘의 일기</h5>
										<table class="table">
											<tbody>
												<tr>
													<td>일기제목 :   </td>
													<td id="title"></td>
												</tr>
												<tr>
													<td>산책 장소 :   </td>
													<td id="place"></td>
												</tr>
												<tr>
													<td>일기 내용 :   </td>
													<td id="content2"></td>
												</tr>
											</tbody>	
										</table>
											
												
									
										<hr style="height: 3px;">
										
										
										<footer class="pull-right" id="datetime"> </footer>
									</div>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" onclick="del2();">삭제하기</button><!-- 삭제하기 -->
							<button type="button" class="btn btn-default"
								data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			  </div>
			  
			  
			  
			  
	<!-- 진료확인서가 여러개 존재할 경우만 보여질수있도록 -> 페이지 이동할때 뒤에 pseq붙여서 이동하기-->
	<!-- 왜 ${plist.pSeq}가 들어가면 동작하지 않는지 질문*************** -->
	<c:if test="${count>1}">
    <div class="modal fade" id="whywhy" tabindex="-1" role="dialog" aria-labelledby="contentPageModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">처방전이 여러개 존재합니다. 보고싶은 처방전을 클릭하세요.</h4>
                </div>
                <div class="modal-body">
             	   <c:forEach items="${pslist}" var="dto">
                    <div class="list-group">
                        <a href="/fanimal/cdiary/add.do?pSeq=${dto.getPSeq()}&aSeq=${dto.getASeq()}" class="list-group-item" data-toggle="modal">${dto.getPSeq()}번째 :  ${dto.getPDate()}</a>
                    </div>
                    </c:forEach>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
        </div>
        </c:if>
        
        <!-- 진료확인서가 한개있는경우  -->
        <c:if test="${count==1}">
	<div class="modal fade" id="whywhy" tabindex="-1" role="dialog" aria-labelledby="contentPageModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">처방전이 한개 존재합니다. 처방전을 클릭하세요.</h4>
                </div>
                <div class="modal-body">
             	   <c:forEach items="${pslist}" var="dto">
                    <div class="list-group">
                        <a href="/fanimal/cdiary/add.do?pSeq=${dto.getPSeq()}&aSeq=${dto.getASeq()}" class="list-group-item" data-toggle="modal">${dto.getPDate()}</a>
                    </div>
                    </c:forEach>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
        </div>
		 </c:if>

		</section>
		<%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
	</main>

	

	<script>
	
	
    document.getElementById("addWDiary").onclick = function () {
    	url="/fanimal/wdiary/add.do?aSeq="+$("#hideASeq").val();
    	window.location.href = url;
    };
	
	let tempcdseq = null; //전역변수로 꺼내줘야지 밑에 자바스크립트에서도 사용이 가능- 전역변수랑 지역변수랑 지역변수가 이기 떄문에 안되었던거임
	
	
	
   //서버 > (데이터:JSON) > 클라이언트
   //케어일기
   function getDataToAjax(cdseq) {
    
 
      $.ajax({
         type: 'GET',
         url: '/fanimal/cdiary/listcdiaryajax.do',
         data: 'cdseq=' + cdseq,  //전송 할 데이터
         dataType: "json",
         success: function(result){ //listcdiaryajax.do로부터 데이터받기   
            
         
        	 tempcdseq = result.cdseq;//여기까지는 잘 찍힘
        	 
				$('#contentPageModalLabel').html('');
            	$('#contentPageModalLabel').append('<span>' + result.animalName +'</span>');
                  
         
            	$('#dogName').html('');
            	$('#dogName').append('<span>' + result.animalName +'케어일기' + '</span>');
                    
            	
         	 
         		$('#explain').html('');
            	$('#explain').append('<span id="memo">' + result.explain + '</span>');
                    
            	
            	
            	$('#picture').html('');
             	$('#picture').append('<img src="/fanimal/files/'+result.picture +'" id="puppy">');
             	
             	
        		$('#component').html('');
            	$('#component').append('<span>' + result.component + '</span>');
            	
            	
            	
        		$('#pname').html('');
            	$('#pname').append('<span>' + result.pname + '</span>');
            	
            	
            	$('#pdate').html('');
            	$('#pdate').append('<span>' + result.pdate + '일</span>');
            	
            	
        		$('#vaccination').html('');
            	$('#vaccination').append('<span>' + result.vaccination + '</span>');
            	
            	
            	
        		$('#usage').html('');
            	$('#usage').append('<span>' + result.usage + '</span>');
            	
            	
            	
        		$('#surgery').html('');
            	$('#surgery').append('<span>' + result.surgery + '</span>');
            	
            	
            	$('#content').html('');
            	$('#content').append('<span>' + result.content + '</span>');
            	
            	
            	$('#purpose').html('');
            	$('#purpose').append('<span>' + result.purpose + '</span>');
            	
            	

            	$('#datetune').html('');
            	$('#datetune').append('<span>' + result.datetune + '</span>');
             			 	
             
         },
         error: function(a,b,c) {
            console.log(a,b,c);
         }
      });
      
   };
   
   
   let tempwseq = null;
   
   //산책일기
   function getDataToAjax2(wseq){

	      $.ajax({
	         type: 'GET',
	         url: '/fanimal/cdiary/listwdiaryajax.do',
	         data: 'wseq=' + wseq,  //전송 할 데이터
	         dataType: "json",
	         success: function(result){ 
	            
					tempwseq =result.wseq;
					console.log(tempwseq);
	        			
					$('#contentPageModalLabel2').html('');
	            	$('#contentPageModalLabel2').append('<span>' + result.name +'</span>');
	                 
	         
	            	$('#dogname2').html('');
	            	$('#dogname2').append('<span>' + result.name +'산책일기' + '</span>');
	                    
	            	
	         	 
	         		$('#title').html('');
	            	$('#title').append('<span>'+ result.title + '</span>');
	            	
	        		$('#place').html('');
	            	$('#place').append('<span>'+ result.place + '</span>');
	            	
	        		$('#content2').html('');
	            	$('#content2').append('<span>'+ result.content + '</span>');
	            	
	            	$('#datetime').html('');
	            	$('#datetime').append('<span>'+ result.datetime + '</span>');
	            	
	            	
	               	$('#pic').html('');
	             	$('#pic').append('<img src="/fanimal/files/'+result.pic +'" id="puppy">');

	         },
	         error: function(a,b,c) {
	            console.log(a,b,c);
	         }
	      });
	      
	   };
	   
	   
	   //케어일기 삭제
	   function del1() {
	        if (!confirm("정말 삭제하시겠습니까?")) {
	            alert("취소(아니오)를 누르셨습니다.");
	        	
	        } else {
	            alert("삭제되었습니다..");
	            location.href = '/fanimal/cdiary/del.do?cdseq='+ tempcdseq;
	        }
	    }
	   
	   
	   
	   //산책일가 삭제
	   function del2() {
	        if (!confirm("정말 삭제하시겠습니까?")) {
	            alert("취소(아니오)를 누르셨습니다.");
	        	
	        } else {
	            alert("삭제되었습니다..");
	            location.href = '/fanimal/wdiary/del.do?wseq='+tempwseq;
	        }
	    }
	   
   </script>
</body>
</html>




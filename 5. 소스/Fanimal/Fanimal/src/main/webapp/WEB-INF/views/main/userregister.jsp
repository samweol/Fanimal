<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
	#homelogo {
	        margin: 0 auto;
	        text-align: center;
	        background-image: url('/fanimal/asset/images/logo.png');
	        background-size: 500px;
	        background-repeat: no-repeat;
	        width: 400px;
	        height: 300px;
	        background-position: -55px -100px;
	    }
	    
	.col {
		display: flex;
		margin-bottom: 10px;
	}
	
	.cal {
		width: 150px;
		background-color: orange;
		border-radius: 10px;
		text-align: center;
		margin: 5px;
	}
</style>
<link rel="icon" type="image/x-icon" href="/fanimal/pic/dog.ico">
<title>Insert title here</title>
</head>
<body>
	<main>
		<div class="content">
			<a href="/fanimal/main/index.do"><div id="homelogo">
            
        	</div></a>
        	<div style="display: flex; justify-content: center; margin-left: 50px;">
	        	<form action="/fanimal/main/userregister.do" method="post" enctype="multipart/form-data" id="formData" style="display: flex; flex-direction: column;">
		        	<div style="display:flex; flex-direction: column; padding-bottom: 10px;">
			        	<div style="display: flex;">
			        		<div class="cal">아이디</div>
			        		<input type="text" id="id" name="id" style="margin-right: 5px;" required>
			        		<input type="button" value="중복검사" id="id-check">
			        	</div>	
		        		<div style="display:flex; justify-content: center;" id="id-check-result" class="error"></div>
		        	</div>		        	
		        	<div class="col">
		        		<div class="cal">비밀번호</div>
		        		<input type="password" name="pw" id="pw" required>
		        	</div>
		        	<div style="display:flex; flex-direction: column; padding-bottom: 10px;">
			        	<div style="display: flex;">
			        		<div class="cal">비밀번호 확인</div>
			        		<input type="password" id="pw-check" name="pwcheck" style="margin-right: 5px;" required>
			        	</div>	
		        		<div style="display:flex; justify-content: center;" id="is-same-pw" class="error"></div>
		        	</div>
		        	<div style="display:flex; flex-direction: column; padding-bottom: 10px;">
			        	<div style="display: flex;">
			        		<div class="cal">이름</div>
			        		<input type="text" id="name" name="name" style="margin-right: 5px;" required>
			        	</div>	
		        		<div style="display:flex; justify-content: center;" id="name-check-result" class="error"></div>
		        	</div>
		        	<div style="display:flex; flex-direction: column; padding-bottom: 10px;">
			        	<div style="display: flex;">
			        		<div class="cal">닉네임</div>
			        		<input type="text" id="nickname" name="nickname" style="margin-right: 5px;" required>
			        		<input type="button" value="중복검사" id="nickname-check">
			        	</div>	
		        		<div style="display:flex; justify-content: center;" id="nickname-check-result" class="error"></div>
		        	</div>	
		        	<div class="col">
		        		<div class="cal">전화번호</div>
		        		<input type="text" name="tel1" style="width: 70px;" required maxlength='3'> - 
		        		<input type="text" name="tel2" style="width: 70px;" required maxlength='4'> - 
		        		<input type="text" name="tel3" style="width: 70px;" required maxlength='4'>
		        	</div>
		        	<div class="col">
		        		<div class="cal">주소</div>
		        		<input type="text" id="sample5_address" placeholder="주소" style="margin-right: 5px;" name="address" required>
						<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
						<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
		        	</div>
		        	<div class="col">
		        		<div class="cal">생일</div>
		        		<input type="date" name="birth" required>
		        	</div>
		        	<div class="col">
		        		<div class="cal">이미지</div>
		        		<input type="file" name="img" accept="image/*">
		        	</div>
		        	<div>
		        		<input type="submit" id="register-btn" value="회원가입하기" style="width: 450px;" disabled>
		        	</div>
	        	</form>
        	</div>
		</div>
	</main>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0002fbb41a9a8a44cc42b3350fe8932b&libraries=services"></script>
	<script>
	    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	        mapOption = {
	            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
	            level: 5 // 지도의 확대 레벨
	        };
	
	    //지도를 미리 생성
	    var map = new daum.maps.Map(mapContainer, mapOption);
	    //주소-좌표 변환 객체를 생성
	    var geocoder = new daum.maps.services.Geocoder();
	    //마커를 미리 생성
	    var marker = new daum.maps.Marker({
	        position: new daum.maps.LatLng(37.537187, 127.005476),
	        map: map
	    });
	
	
	    function sample5_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                var addr = data.address; // 최종 주소 변수
	
	                // 주소 정보를 해당 필드에 넣는다.
	                document.getElementById("sample5_address").value = addr;
	                // 주소로 상세 정보를 검색
	                geocoder.addressSearch(data.address, function(results, status) {
	                    // 정상적으로 검색이 완료됐으면
	                    if (status === daum.maps.services.Status.OK) {
	
	                        var result = results[0]; //첫번째 결과의 값을 활용
	
	                        // 해당 주소에 대한 좌표를 받아서
	                        var coords = new daum.maps.LatLng(result.y, result.x);
	                        const xcoor = `<input type="hidden" value="\${result.y}" name="xcoor">`;
	                        const ycoor = `<input type="hidden" value="\${result.x}" name="ycoor">`;
	                        $('#formData').append(xcoor);
	                        $('#formData').append(ycoor);
	                    }
	                });
	            }
	        }).open();
	    }
	    
	    let isValidId = false;
	    let isValidNickname = false;
	    
	    /* 아이디 중복 검사 */
	    $('#id-check').click(function() {
	    	$.ajax({
	    		url: '/fanimal/main/idcheck.do',
	    		type: 'POST',
	    		data: "id=" + $('#id').val(),
	    		dataType: 'json',
	    		success: function(result) {
	    			if(result.result == "1") {
	    				//아이디가 존재하는 경우
	    				$('#id-check-result').text('중복된 아이디가 존재합니다.');
	    				$('#id-check-result').css('color', 'red');
	    				isValidId = false;
	    			} else {
	    				//아이디가 존재하지 않아 사용가능한 경우
	    				$('#id-check-result').text('사용 가능한 아이디입니다.');
	    				$('#id-check-result').css('color', 'green');
	    				isValidId = true;
	    			}
	    		},
	    		error: function(a, b, c) {
	    			console.log(a, b, c)
	    		}
	    	});
	    });
	    
	    $('#nickname-check').click(function() {
	    	$.ajax({
	    		url: '/fanimal/main/nicknamecheck.do',
	    		type: 'POST',
	    		data: "nickname=" + $('#nickname').val(),
	    		dataType: 'json',
	    		success: function(result) {
	    			if(result.result == "1") {
	    				//닉네임이 존재하는 경우
	    				$('#nickname-check-result').text('중복된 닉네임이 존재합니다.');
	    				$('#nickname-check-result').css('color', 'red');
	    				isValidNickname = false;
	    			} else {
	    				//아이디가 존재하지 않아 사용가능한 경우
	    				$('#nickname-check-result').text('사용 가능한 닉네임입니다.');
	    				$('#nickname-check-result').css('color', 'green');
	    				isValidNickname = true;
	    			}
	    			
	    			//아이디와 닉네임 중복검사를 해야지만 버튼 활성화
	    			if (isValidId && isValidNickname) {
                        $('#register-btn').removeAttr('disabled');
                    } else {
                        $('#register-btn').attr('disabled', true);
                    }
	    		},
	    		error: function(a, b, c) {
	    			console.log(a, b, c)
	    		}
	    	});
	    });
	    
	    //회원가입 전체 유효성 검사
	    $('form').submit(function(){
	    	let pw = $('#pw').val();
            let pwCheck = $('#pw-check').val();
            let name = $('#name').val();
	    	
	    	//console.log(pw);
	    	//console.log(pwCheck);
	    	
	    	//아이디에 한글이 있는지없는지 검사
	    	if (!/^[A-Za-z]{1}[A-Za-z0-9]{3,15}$/.test($('#id').val())) {
                $('#id-check-result').css('color', 'red');
                $('#id-check-result').text("아이디는 영대소문자, 숫자를 포함한 4 ~ 16자로 입력해주세요.");
                return false;
            }
	    	
	    	if (!/^[A-Za-z0-9]{7,15}$/.test(pw)) {
                $('#pw-check-result').text("비밀번호는 영대소문자, 숫자를 포함한 8 ~ 16자로 입력해주세요.");
                return false;
            }
	    	
	    	if (pw != pwCheck) {
	    		$('#is-same-pw').css('color', 'red');
                $('#is-same-pw').text('입력하신 비밀번호와 일치하지 않습니다.');
                return false;
            }

            if (!/^[가-힣]{2,6}$/.test($('#nickname').val())) {
                $('#nickname-check-result').css('color', 'red');
                $('#nickname-check-result').text('닉네임은 2~6자 이내 한글로 입력해주세요.');
                return false
            }

            if (!/^[가-힣]{2,6}$/.test($('#name').val())) {
            	$('#name-check-result').css('color', 'red');
                $('#name-check-result').text('이름은 2~6자 이내 한글로 입력해주세요.');
                return false;
            }
            
            $("#register-btn").submit();
	    });
	</script>
</body>
</html>
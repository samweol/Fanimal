<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="/fanimal/asset/css/user/addanimal.css">
<script src="/jsp/example/js/string.js"></script>
<title>Insert title here</title>
</head>
<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
	            <div id="content">
	               
	               	<div id="tablebox">
	               	<form method="POST" action="/fanimal/user/mypage/addanimal.do" enctype="multipart/form-data">       		               	
	                <table class="table animal" id="required">
	                	
	                	<tr>
	                		<td colspan="2"><h3>반려동물 정보</h3></td>
	                	</tr>
	                	
	                	<tr>
	                		<td><div>이름</div></td>
	                		<td><input type="text" name="name" id="name" class="form-control" minlength="1" maxlength="5" size="10" required></td>
	                	</tr>
	                	<tr>
	                		<td><div>분류</div></td>
	                		<td>
	                			<select name="type" id="type" class="form-control" onclick="del()" onchange="animalChange(this)" required>
	                				<option value="" disabled selected>===선택===</option>
	                				<c:forEach items="${typelist}" var="tdto">	                				
	                				<option value="${tdto.tseq}">${tdto.type}</option>
	                				</c:forEach>
	                			</select>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td><div>품종</div></td>
	                		<td>
	                			<select name="kind" id="kind" class="form-control" required>
	                				<option value="" disabled selected>====선택====</option>	                				
	                			</select>
	                		</td>
	                	</tr>
	                	
	                	<tr>
	                		<td><div>성별</div></td>
	                		<td>	                			
	                		    <label><input type="radio" name="gender" id="gender" value="m" checked> 수컷</label>
      							<label><input type="radio" name="gender" id="gender" value="f" > 암컷</label>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td><div>나이</div></td>
	                		<td><input type="number" class="form-control" name="age" id="age" min="1" value="1"></td>
	                	</tr>
	                	<tr>
	                		<td><div>생일</div></td>
	                		<td>
	                			<div id="birthBox">
	                			 <div id="bir_wrap">
                    
			                        <!-- BIRTH_YY -->
			                        <div id="bir_yy">
			                            <span class="box">
			                                <input type="text" name="yyyy" id="yyyy" class="int form-control" maxlength="4" placeholder="년(4자)"
			                                	onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required>
			                            </span>
			                        </div>
			
			                        <!-- BIRTH_MM -->
			                        <div id="bir_mm">
			                            <span class="box">
			                                <select name="mm" id="mm" class="form-control" required>
			                                    <option value="" disabled selected>월</option>
			                                    <option value="01">1</option>
			                                    <option value="02">2</option>
			                                    <option value="03">3</option>
			                                    <option value="04">4</option>
			                                    <option value="05">5</option>
			                                    <option value="06">6</option>
			                                    <option value="07">7</option>
			                                    <option value="08">8</option>
			                                    <option value="09">9</option>                                    
			                                    <option value="10">10</option>
			                                    <option value="11">11</option>
			                                    <option value="12">12</option>
			                                </select>
			                            </span>
			                        </div>
			
			                        <!-- BIRTH_DD -->
			                        <div id="bir_dd">
			                            <span class="box">
			                                <input type="text" class="form-control" name="dd" id="dd" class="int" maxlength="2" placeholder="일"
			                                	onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required>
			                            </span>
			                        </div>                 
					                    
	                		</td>
	                	</tr>
	                	
	                	
	                	
	                	
	                	<!-- <tr>
	                		<td colspan="2"><h3>선택사항</h3></td>	                		
	                	</tr> -->
	                		                	
	                	
	                	<tr>
	                		<!-- <td><div>등록번호</div></td>
	                		<td><input type="text" class="form-control" id="animalNum" name="animalNum"
	                				onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required>
	                		</td> -->
	                	</tr>
	                	<tr>
	                		<td><div>중성화</div></td>
	                		<td>	                			              			
	                		    <label><input type="radio" name="neuter" value="y"><div> 여(o)</div></label>
      							<label><input type="radio" name="neuter" value="n" checked><div> 부(x)</div></label>
	                		<td>
	                	</tr>
	                	<tr>
	                		<td><div>몸무게</div></td>
	                		<td><div><input type="text" class="form-control" name="weight" id="weight"
	                					onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required> kg</div></td>
	                	</tr>
	                	<tr>
	                		<td><div>보유질환</div></td>
	                		<td>
	                			<select name="disease" id="disease" class="form-control" required>
	                				<option value="" disabled selected>=======선택=======</option>
	                				<c:forEach items="${dislist}" var="ddto">                               
                                    <option value="${ddto.dseq}">${ddto.dname}</option>
                                    </c:forEach>                                   
                                </select>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td><div>사망여부</div></td>
	                		<td>
	                			<label><input type="radio" name="die" value="y"><div> 여(o)</div></label>
      							<label><input type="radio" name="die" value="n" checked><div> 부(x)</div></label>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td>사진 추가</td>
	                		<td>	
	                		
		                		<div id="filelist"></div>		                							                		
								<!-- <input type="button" value="첨부 파일 추가하기" class="btn btn-secondary" id="btnfile"> -->
								<div id="picbox"><input type="file" name="attach" onchange="viewimg(event);"><span onclick="del2();">&times;</span></div>
								<div id="image_container"></div>
	                		</td>
	                	</tr>
	                </table>
	                <div id="subbtn"><button type="submit" class="btn btn-warning">추가하기</button></div>
	                </form>
	                </div>	                	                    
	            </div>
	        </section>
	        <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
        </main>
<script>

let no = 2;	

$('#btnfile').click(function() {
	
	let temp = String.format('<div><input type="file" name="attach{0}"><span onclick="del();">&times;</span></div>', no);
	
	$('#filelist').append(temp);
	
	no++;
	
});

function del2() {
	//alert(this);
	//alert(event.target);
	
	$(event.target).parent().remove();
	$('#image_container').children().remove();
	
	let temp = String.format('<div id="picbox"><input type="file" name="attach" onchange="viewimg(event);"><span onclick="del2();">&times;</span></div>');
	
	$('#filelist').append(temp);
	
}



function del() {
	//alert("hi");
	$('#kind').empty();
	
}


function animalChange(e) {
	/* var dog = ["말티즈", "푸들", "셰퍼드", "불독"];
	var cat = ["수원", "평택", "용인"];
	var rabbit = ["천안", "세종", "논산"];
	var target = document.getElementById("kind");
	if(e.value == "1") var d = dog;
	else if(e.value == "2") var d = cat;
	else if(e.value == "3") var d = rabbit;
	target.options.length = 0;
	
	for (x in d) {
		var opt = document.createElement("option");
		opt.value = d[x];
		opt.innerHTML = d[x];
		target.appendChild(opt);
	} */
	
	
	
	
	//fanimal/ajax/anikind.do?tseq=1	
	$.ajax({
		type: 'GET',
		url: '/fanimal/ajax/anikind.do',
		data: 'tseq=' + $(event.target).val(),
		dataType: 'json',
		success: function(result) {	
					
			$('#kind').append('<option value="" disabled selected>====선택====</option>');
			
			$(result).each(function(index, item) {
				/* <option value="1">말티즈</option> */
				
				$('#kind').append('<option value="' + item.kseq + '">' + item.kind + '</option>')
							
				
			});
								
		},
		error: function(a,b,c) {
			console.log(a,b,c);
		}
	});
	
	
}




function viewimg(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
      var img = document.createElement("img");
      img.setAttribute("src", event.target.result);
      document.querySelector("div#image_container").appendChild(img);
    };

    reader.readAsDataURL(event.target.files[0]);
  }




</script>       
</body>
</html>










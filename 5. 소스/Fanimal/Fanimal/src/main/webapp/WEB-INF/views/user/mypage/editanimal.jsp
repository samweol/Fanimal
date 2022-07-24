<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="/fanimal/asset/css/user/editanimal.css">
<script src="/jsp/example/js/string.js"></script>
<title>Insert title here</title>
<style>

</style>
</head>
<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
		<section>
            <div id="content">					
				
					<div id="tablebox">
	               	<form method="POST" action="/fanimal/user/mypage/editanimal.do" enctype="multipart/form-data">       		               	
	                <table class="table animal" id="required">
	                	
	                	<tr>
	                		<td colspan="2"><h3>반려동물 정보 수정</h3></td>
	                	</tr>
	                	
	                	<tr>
	                		<td><div>이름</div></td>
	                		<td><input type="text" name="name" id="name" value="${dto.name}" class="form-control" minlength="1" maxlength="5" size="10" required></td>
	                	</tr>
	                	<tr>
	                		<td><div>분류</div></td>
	                		<td>
	                			<%-- <select name="type" id="type" class="form-control" onclick="del()" onchange="animalChange(this)" required>
	                				<option value="" disabled selected>===선택===</option>
	                				<c:forEach items="${typelist}" var="tdto">	                				
	                				<option value="${tdto.tseq}" ${tdto.tseq == dto.tseq ? 'selected="selected"' : ''}>${tdto.type}</option>
	                				</c:forEach>
	                			</select> --%>
	                			${dto.type}
	                		</td>
	                	</tr>
	                	<tr>
	                		<td><div>품종</div></td>
	                		<td>
	                			<!-- <select name="kind" id="kind" class="form-control" required>
	                				<option value="" disabled selected>====선택====</option>	                				
	                			</select> -->
	                			${dto.kind}
	                		</td>
	                	</tr>
	                	
	                	<tr>
	                		<td><div>성별</div></td>
	                		<td>
	                			<c:if test="${dto.gender == 'm'}">
	                		    <label><input type="radio" name="gender" id="gender" value="m" checked> 수컷</label>
      							<label><input type="radio" name="gender" id="gender" value="f" > 암컷</label>
      							</c:if>
      							<c:if test="${dto.gender == 'f'}">
	                		    <label><input type="radio" name="gender" id="gender" value="m" > 수컷</label>
      							<label><input type="radio" name="gender" id="gender" value="f" checked> 암컷</label>
      							</c:if>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td><div>나이</div></td>
	                		<td><input type="number" value="${dto.age}" class="form-control" name="age" id="age" min="1" value="1"></td>
	                	</tr>
	                	<tr>
	                		<td><div>생일</div></td>
	                		<td>
	                			<div id="birthBox">
	                			 <div id="bir_wrap">
                    
			                        <!-- BIRTH_YY -->
			                        <div id="bir_yy">
			                            <span class="box">
			                                <input type="text" name="yyyy" id="yyyy" value="${dto.yy}" class="int form-control" maxlength="4" placeholder="년(4자)"
			                                	onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required>
			                            </span>
			                        </div>
			
			                        <!-- BIRTH_MM -->
			                        <div id="bir_mm">
			                            <span class="box">
			                                <span class="box">
			                                <input type="text" class="form-control" name="mm" id="mm" value="${dto.mm}" class="int" maxlength="2" placeholder="월"
			                                	onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required>
			                            </span>
			                            </span>
			                        </div>
			
			                        <!-- BIRTH_DD -->
			                        <div id="bir_dd">
			                            <span class="box">
			                                <input type="text" class="form-control" name="dd" id="dd" value="${dto.dd}" class="int" maxlength="2" placeholder="일"
			                                	onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required>
			                            </span>
			                        </div>                 
					                    
	                		</td>
	                	</tr>               	   	             	                	                             	
	                	
	                	<tr>	                		
	                	</tr>
	                	<tr>
	                		<td><div>중성화</div></td>
	                		<td>
	                			<c:if test="${dto.neutral == 'y'}">
	                		    <label><input type="radio" name="neuter" value="y" checked><div> 여(o)</div></label>
      							<label><input type="radio" name="neuter" value="n"><div> 부(x)</div></label>
      							</c:if>
      							<c:if test="${dto.neutral == 'n'}">
	                		    <label><input type="radio" name="neuter" value="y"><div> 여(o)</div></label>
      							<label><input type="radio" name="neuter" value="n" checked><div> 부(x)</div></label>
      							</c:if>
	                		<td>
	                	</tr>
	                	<tr>
	                		<td><div>몸무게</div></td>
	                		<td><div><input type="text" class="form-control" name="weight" id="weight" value="${dto.weight}"
	                					onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required> kg</div></td>
	                	</tr>
	                	<tr>
	                		<td><div>보유질환</div></td>
	                		<td>
	                			<select name="disease" id="disease" class="form-control" required>
	                				<option value="" disabled selected>=======선택=======</option>
	                				<c:forEach items="${dislist}" var="ddto">                               
                                    <option value="${ddto.dseq}"  ${ddto.dseq == dto.dseq ? 'selected="selected"' : ''}>${ddto.dname}</option>
                                    </c:forEach>                                   
                                </select>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td><div>사망여부</div></td>
	                		<td>
	                			<c:if test="${dto.state == 'y'}">
	                			<label><input type="radio" name="die" value="y" checked><div> 여(o)</div></label>
      							<label><input type="radio" name="die" value="n" ><div> 부(x)</div></label>
      							</c:if>
      							<c:if test="${dto.state == 'n'}">
	                			<label><input type="radio" name="die" value="y" ><div> 여(o)</div></label>
      							<label><input type="radio" name="die" value="n" checked><div> 부(x)</div></label>
      							</c:if>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td>사진 추가</td>
	                		<td>	
	                			
		                		<div id="filelist"></div>		                							                		
								<!-- <input type="button" value="첨부 파일 추가하기" class="btn btn-secondary" id="btnfile"> -->
								<div id="picbox"><input type="file" name="attach" onchange="viewimg(event);"><span onclick="del();">&times;</span></div>
								<div id="image_container"></div>
								
	                		</td>
	                	</tr>
	                </table>
	                <input type="hidden" name="uaseq" value="${uaseq}">
	                <div id="subbtn"><button type="submit" class="btn btn-warning">수정하기</button></div>
	                </form>
	                </div>

            </div>
        </section>
       <footer>

       </footer>
    </main>    
    <script>
    
    let no = 2;	

    $('#btnfile').click(function() {
    	
    	let temp = String.format('<div><input type="file" name="attach{0}"><span onclick="del();">&times;</span></div>', no);
    	
    	$('#filelist').append(temp);
    	
    	no++;
    	
    });

    function del() {
    	//alert(this);
    	//alert(event.target);
    	
    	$(event.target).parent().remove();
    	$('#image_container').children().remove();
    	
    	let temp = String.format('<div id="picbox"><input type="file" name="attach" onchange="viewimg(event);"><span onclick="del();">&times;</span></div>');
    	
    	$('#filelist').append(temp);
    	
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
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
        
        animation-name: mainlogo;
        animation-duration: 2s;
    }
    
    @-webkit-keyframes mainlogo {
        from {
            opacity: 0;
            transform: scale(3);
        }
        to {
            opacity: 1;
        }
    }
    
	.select {
		width: 200px;
		height: 200px;
		background-color: #D28A06;
		border-radius: 50%;
		display: flex;
		justify-content: center;
		align-items: center;
		margin: 10px;
		font-size: 25px;
		
		animation-name: fadein;
        animation-duration: 3s;
	}
	
	@-webkit-keyframes fadein {
        0% {
            opacity: 0;
        }
        50% {
            opacity: 0;
        }
        70% {
            opacity: 0.3;
        }
        80% {
            opacity: 0.5;
        }
        100% {
            opacity: 1;
        }
    }
	
	.select:hover {
		background-color: orange;
	}
	
	a {
		color: black;
		text-decoration: none;
	}
	
	a:hover {
		color: black;
		text-decoration: none;
	}
	
	
    
</style>
<link rel="icon" type="image/x-icon" href="/fanimal/pic/dog.ico">
<title>Insert title here</title>
</head>
<body>
	<main>
		<a href="/fanimal/main/index.do"><div id="homelogo"></div></a>
		<div style="display: flex; justify-content: center; margin: 40px;">
			<a href="/fanimal/main/userregister.do"><div class="select" id="user" >개인</div></a>
			<a href="/fanimal/main/companyregister.do"><div class="select" id="company">기업</div></a>
		</div>
	</main>
</body>
</html>
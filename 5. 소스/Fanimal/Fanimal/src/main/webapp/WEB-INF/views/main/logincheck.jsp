<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #homelogo {
        margin: 0 auto;
        margin-top: 150px;
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

    #loginselect {
        margin-top: 10px;
        
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

    #loginselect > div > a {
        color: black;
        text-decoration: none;
    }
</style>
<link rel="icon" type="image/x-icon" href="/fanimal/pic/dog.ico">
</head>
<body>
	 <main>
        <a href="/fanimal/main/index.do"><div id="homelogo">
            
        </div></a>
        <div id="loginselect" style="display: flex; justify-content: center;">
            <div id="userlogin" style="padding: 10px;">
                <a href="/fanimal/main/userlogin.do"><img src="/fanimal/asset/images/pic.png" style="width: 80px;">
                <div style="text-align: center;">회원</div></a>
            </div>
            <div id="companylogin" style="padding: 10px;">
                <a href="/fanimal/main/companylogin.do"><img src="/fanimal/asset/images/pic.png" style="width: 80px;">
                <div style="text-align: center;">기업</div></a>
            </div>
            <div id="adminlogin" style="padding: 10px;">
                <a href="/fanimal/main/adminlogin.do"><img src="/fanimal/asset/images/pic.png" style="width: 80px;">
                <div style="text-align: center;">관리자</div></a>
            </div>
        </div>
    </main>
</body>
</html>
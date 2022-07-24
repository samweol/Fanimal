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
</head>
<body>
	<main>
		<%@ include file ="/WEB-INF/views/inc/companyheader.jsp"%>
		<section>
	            <div id="content">
	            	<div style="margin: 0 auto; padding-top: 20px; padding-bottom: 20px; width: 300px; text-align: center; font-size: 35px;">예약 차트 보기</div>
        			<div style="margin:0 auto; width:400px; height:400px;">
        				<canvas id="myChart" width="400" height="400"></canvas>
        			</div>         
	            </div>
	        </section>
	        <%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
        </main>
        <script>
    	
        let month = [];
        let count = [];
        
        <c:forEach items="${month}" var="m">
        	month.push('${m}');
        </c:forEach>
        
        <c:forEach items="${count}" var="c">
    		count.push('${c}');
    	</c:forEach>
        
    	const ctx = document.getElementById('myChart').getContext('2d');
    	const myChart = new Chart(ctx, {
    	    type: 'bar',
    	    data: {
    	        labels: month,
    	        datasets: [{
    	            label: '# of Votes',
    	            data: count,
    	            backgroundColor: [
    	                'rgba(255, 99, 132, 1)',
    	                'rgba(54, 162, 235, 1)',
    	                'rgba(255, 206, 86, 1)',
    	                'rgba(75, 192, 192, 1)',
    	                'rgba(153, 102, 255, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)'
    	            ],
    	            borderColor: [
    	                'rgba(255, 99, 132, 1)',
    	                'rgba(54, 162, 235, 1)',
    	                'rgba(255, 206, 86, 1)',
    	                'rgba(75, 192, 192, 1)',
    	                'rgba(153, 102, 255, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)',
    	                'rgba(255, 159, 64, 1)'
    	            ],
    	            borderWidth: 1
    	        }]
    	    },
    	    options: {
    	        scales: {
    	            y: {
    	                beginAtZero: true
    	            }
    	        }
    	    }
    	});
    	</script>
</body>
</html>
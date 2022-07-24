<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<%@ include file = "/WEB-INF/views/inc/asset.jsp"%>
<link href='/fanimal/asset/css/user/calendar.css' rel='stylesheet' />
<link href='/fanimal/asset/lib/main.css' rel='stylesheet' />
<script src='/fanimal/asset/lib/main.js'></script>

<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      initialDate: '${today}',
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        /* var title = prompt('일정 추가');
        if (title) {
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })
        } */
        calendar.unselect()
      },
      eventClick: function(arg) {
        if (confirm('정말 삭제하시겠습니까?')) {
        	//alert(arg.event);	
          arg.event.remove()
        }
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: [
    	  <c:forEach items="${clist}" var="cdto">
    	  {
            title: '${cdto.memo}',
            start: '${cdto.datetime}',
            end: '${cdto.end}'
          },
    	  </c:forEach>
    	  
    	  <c:forEach items="${hlist}" var="hdto">
    	  {
	    	  title: '${hdto.hname}',
	          start: '${hdto.resdate}',
	          backgroundColor: '#23C82E',
	          borderColor: '#23C82E'
    	  },
    	  </c:forEach>
        
      ]
    });

    calendar.render();
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
</head>
<body>
<main>
<%@ include file ="/WEB-INF/views/inc/userheader.jsp"%>
	<section>
        <div id='content' style='padding-top: 20px;'>
 			 <div id='calendar' style="background-color: white; padding: 5px;"></div>
 			 
 			 
		
		<div>
		<form method="GET" action="/fanimal/user/mypage/addcalendar.do" style="padding-top: 20px; padding-bottom: 40px; background-color: antiquewhite;">
		<table id="addtbl">
			<tr>
			<th>일정 추가</th>
			<th>시작 날짜</th>
			<th>종료 날짜</th>
			<th></th>
						
			</tr>
			<tr>
				<td><input type="text"  name="memo" id="memo" class="form-control" placeholder="일정 내용"  maxlength="15" required></td>
				<td><input type="date" name="start" id="start" class="form-control" required></td>
				<td><input type="date" name="end" id="end" class="form-control" required></td>
				<td><button type="submit" class="btn btn-success">일정 추가</button></td>
			</tr>			
		</table>
		</form>
		</div>
		</div>
		
		
	</section>
	<%@ include file ="/WEB-INF/views/inc/footer.jsp"%>
</main>	
</body>
</html>

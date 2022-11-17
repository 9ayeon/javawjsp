<!-- 2번라인: 디렉티브(페이지지시자) -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%																	
	String sw = request.getParameter("sw") == null ? "" : request.getParameter("sw");
%> <!-- 스위치가 널값이거야? <조건?맞는것에대한결과값(true):이렇게처리할거야(false) = 삼항연산자>널값을 공백처리. 스위치변수에는 널값을 공백으로 처리하기때문에 널값이 올수없다.-->
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>main.jsp</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<style>
		body {
			width: 1000px;
			margin: 0px auto; /* 가운데정렬 상하, 좌우 */
		}
		#header {
			background-color: skyblue;
			text-align: center;
			height: 80px;
		}
		#footer {
			background-color: #ccc;
			text-align: center;
			height: 75px;
		}
		#content {
			background-color: #fff;
			text-align: center;
		}
	</style>
</head>
<body>
<div class="container">
	<!-- 헤더영역('메뉴/로고' 를 표시한다.) -->
  <div id="header">
  	<br/>
  	<%@ include file="menu.jsp" %>
  </div>
  <!-- 본문영역(바디) -->
  <div id="content">
  <br/>
<%if(sw.equals("guest")) {%> <!-- 만약 스위치 게스트면, 인크루드 게스트jsp부르고, 엘스면 메인화면그대로.  -->
		<%@ include file="guest.jsp" %>
<%}else if(sw.equals("board")) {%>
		<%@ include file="board.jsp" %>
<%}else if(sw.equals("pds")) {%>
		<%@ include file="pds.jsp" %>
<%}else if(sw.equals("photo")) {%>
 		<!--<%@ include file="photo.jsp" %> --> <!-- 완성된거, 인크루드지시자는 파일file -->
		<jsp:include page="photo.jsp"></jsp:include> <!-- 잘라진거,뭘써도 상관없음 JSTL사용 인크루드 액션사용시 페이지page-->
<%} else { %>
		<h2>이곳은 메인 화면 입니다</h2>
		<hr/>  
		<p><img src="../../images/1.jpg" width="600px" /></p>
<%} %>
  	<br/>
  </div>
  <!-- 푸터영역(Copyright나 주소, 소속, 작성자 등을 기술한다. -->
  <div id="footer">
  	<%@ include file="footer.jsp" %>
  </div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%																	
	String sw = request.getParameter("sw") == null ? "" : request.getParameter("sw");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>home.jsp</title>
  <%@ include file="../../include/bs4.jsp" %>
	<style>
		body {
			width: 1000px;
			margin: 0px auto;
		}
		#header {
			background-color: skyblue;
			text-align: center;
			height: 80px;
			color: black;
		}
		#header a, #header :hover {
			text-decoration: none;
			color: black;
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
<div class="container text-center">
	<!-- 헤더영역('메뉴/로고' 를 표시한다.) -->
  <div id="header">
  	<br/>
  	<%@ include file="menu.jsp" %>
  </div>
  <!-- 본문영역(바디) -->
  <div id="content">
  <br/>
<%if(sw.equals("input")) {%>
		<jsp:include page="input.jsp"></jsp:include>
<%}else if(sw.equals("list")) {%>
		<jsp:include page="list.jsp"></jsp:include>
<%} else { %>
		<h2>아메리카노 한잔에 5000원...</h2>
		<hr/>  
		<p><img src="../../images/1.jpg" width="600px" /></p>
<%} %>
  	<br/>
  </div>
  <div id="footer">
  	<%@ include file="footer.jsp" %>
  </div>
</div>
</body>
</html>
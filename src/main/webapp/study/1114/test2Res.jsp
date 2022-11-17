<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8"); //한글깨지는경우
	
	String mid = request.getParameter("mid");
	String name = request.getParameter("name");
	String hostIp = request.getParameter("hostIp");
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>test2Res.jsp</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container">
  <p>이곳은 관리자 페이지 화면입니다.</p>
  <p>전송된 아이디 : <%=mid %> </p>
  <p>전송된 성명 : <%=name %> </p>
  <hr/>
  <p><img src="../../images/2.jpg" width="200px" /></p>
  <hr/>
  접속 전송 방식 : <%=request.getMethod() %><br/> <!-- 헤더에 들어있는 정보들 -->
  접속 URL : <%=request.getRequestURL() %><br/>
  접속 URI : <%=request.getRequestURI() %><br/>
  접속 서버이름 : <%=request.getServerName() %><br/>
  접속 포트번호 : <%=request.getServerPort() %><br/>
  요청 파라메터 길이 : <%=request.getContentLength() %><br/> <!--  없으면 -1값넘어옴 -->
  현재 ContextPath : <%=request.getContextPath() %><br/>
  현재 사용중인 프로토콜 : <%=request.getProtocol() %><br/> <!-- 1.1버전 -->
  접속자 IP : <%=hostIp %><br/>
  <hr/>
  <p>
  	<!-- <a href="logout.jsp">로그아웃</a> --> <!-- res에서 호출하는 로그아웃.같은 위치에는 경로를 적지않는다.  -->
  	<a href="<%=request.getContextPath()%>/j1114_Logout?name=<%=name%>">로그아웃</a><!-- 앞에서넘겨온 jsp변수로 받으려면 표현식사용 -->
  	 <!--주소옆에 변수넘길때 ?사용 /패키지명_클래스명에 자바변수를 넘기겠다. ,res에서 호출하는 로그아웃.  -->
  </p>
  
</div>
<p><br/></p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid"); //널이면?공백처리, 뒤에걸가져올게
	String name = request.getParameter("name") == null ? "" : request.getParameter("name");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>test1Res.jsp(최종목적지)(중간경로,.거쳐가는곳으로수정)</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container">
  <H2>이곳은 관리자 인증 후 도착한 곳입니다.</H2>
  <p>아이디 : <%=mid %> </p>
  <p>성명 : <%=name %> </p>
  <p><a href="test1.jsp" class="btn btn-info">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>
<jsp:forward page="test1ResOk.jsp"></jsp:forward>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>form.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<!-- <form name="myform" method="post" action="el1.jsp"> -->
	<form name="myform" method="post" action="${pageContext.request.contextPath}/study/el_jstl/El2">
	  <h2>자료 전송 연습</h2>
	  <div>
	  	성명 : <input type="text" name="name" value="홍길동" class="form-control" />
	  </div>
	  <div> 취미 : 
	  	<input type="checkbox" name="hobby" value="음악감상" checked/>음악감상
	  	<input type="checkbox" name="hobby" value="영화감상"/>영화감상
	  	<input type="checkbox" name="hobby" value="그림"/>그림
	  	<input type="checkbox" name="hobby" value="도예"/>도예
	  	<input type="checkbox" name="hobby" value="게임"/>게임
	  </div>
	  <div>
	  	<input type="submit" value="전송" class="btn btn-outline-info form-control" />
	  </div>
  </form>
</div>
<p><br/></p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>test4.jsp</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>컨트롤러가 2개 이상의 URL 제어 연습</h2>
  <p>1개의 폼안에서 POST/GET 방식의 제어</p>
  <hr/>
  <!-- <form name="myform" method="post" action="<%=request.getContextPath()%>/j1114_Test4"> --%> <!-- 메소드 입력안하면 기본이 get -->
  <form name="myform" method="post" action="<%=request.getContextPath()%>/j1114_T4"> <!-- 메소드 입력안하면 기본이 get -->
  	<p>
  		<input type="submit" value="전송(submit:post방식)" class="btn btn-success" /> <!-- hidden값 넘길땐 무조건 post, 겟방식은 null값나옴 -->
  		<input type="button" value="전송(location:get방식)" onclick="location.href='<%=request.getContextPath()%>/j1114_Test4';" class="btn btn-primary" />
  	</p>
  	<input type="hidden" name="name" value="홍길동" /> <!-- 변수에 담아놓기, hidden 중요 -->
  </form>
</div>
<p><br/></p>
</body>
</html>
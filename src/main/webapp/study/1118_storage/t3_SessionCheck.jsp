<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>t3_SessionCheck.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>세션 확인</h2>
  <br/>
  <div class="table border">
	  <div class="row m-3">
	  	<div class="col">세션 아이디</div>
	  	<div class="col">${sMid}</div>
  	</div>
	  <div class="row m-3">
	  	<div class="col">세션 닉네임</div>
	  	<div class="col">${sNickName}</div>
	  </div>
	  <div class="row m-3">
	  	<div class="col">세션 나이</div>
	  	<div class="col">${sAge}</div>
	  </div>
	  <div class="row m-3">
	  	<div class="col">세션 성명</div>
	  	<div class="col">${sName}</div>
	  </div>
  </div>
  <div>
  	<a href="t3_SessionMain.jsp" class="btn btn-secondary form-control">돌아가기</a>
  </div>
</div>
<p><br/></p>
</body>
</html>
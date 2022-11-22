<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>colorButton.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<div class="w3-container">
		<h2>W3 - Button Borders</h2>
		  <button class="w3-btn w3-white w3-border">Button</button>
		  <button class="w3-btn w3-indigo w3-border">Button</button>
		  <button class="w3-btn w3-white w3-border w3-round-large">Button</button>
		  <button class="w3-btn w3-white w3-border w3-border-lime w3-round-large">Button</button>
</div>
<div class="container">
		<h2>bs4 - Button Borders</h2>
		  <button class="btn w3-deep-purple w3-border w3-hover-white">Button</button>
		  <button class="btn w3-indigo w3-border">Button</button>
		  <button class="btn w3-white w3-border w3-round-large">Button</button>
		  <button class="btn w3-white w3-border w3-border-lime w3-round-large w3-hover-lime">Button</button>
			<hr/><br/>
			<div style="width:600px;height:200px;" class="w3-lime">
				이곳은 내용영역입니다.
			</div>
</div>
<p>
</div>
<p><br/></p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("utf-8"); %>

<!-- 앞에서 전송된 값들을 변수에 담아보자 -->
<c:set var="name" value="${param.name}"/> <!-- 파라메타...머지...<!-- el태그는 서블릿에서, 저장소에서 꺼낼떄?넣을때?쓰는거라 여기서 못씀 근데쓰네.. -->
<c:set var="gender" value="${param.gender}"/>
<c:set var="age" value="${param.age}"/>
<c:set var="job" value="${param.job}"/>
<c:set var="address" value="${param.address}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>testF1Ok.jsp</title>
  <jsp:include page="../../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>전송된 자료 결과 보기2</h2>
  <div>
  	<table class="table table-bordered">
		<tr>
			<th>성명</th> <!-- vo를 빼는이유 : 이name변수는 윗단에서 불러온걸로 출력함 vo(서블릿,저장소)에서 불러오는거X -->
			<td>${name}</td><%--  el표기법,vo표기된이름으로 --%>
		</tr>
		<tr>
			<th>성별</th>
			<td>${gender}</td> <!--  jstl과 el은 잘맞아~ 서블릿에서 정의한게 아니고 위에서 정의한거라는데 먼소리지 -->
		</tr>
		<tr>
			<th>나이</th>
			<td>${age + 1}</td> <!--  초기값(20)에 +1, = 결과는 21이 나온다.정수타입 연산가능 -->
		</tr>
		<tr>
			<th>직업</th>
			<td>${job}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${address}</td>
		</tr>
	</table>
  </div>
</div>
<p><br/></p>
</body>
</html>
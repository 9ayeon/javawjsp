<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//널이면 공백 아니면 문자형식으로넣어
	String mid = session.getAttribute("sMid") == null ? "" : (String) session.getAttribute("sMid");
	
%>

<!DOCTYPE html><!-- 이곳은 뷰 -->
<html>
<head>
  <meta charset="UTF-8">
  <title>t5_LoginMember.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container" align="center">
  <h2>회원 전용방</h2>
  <p><font color="blue">${sMid}</font>님 로그인 중이십니다.</p>
	<hr/>
	<p>
		방문카운트(session) : <br/>
		전체 총 방문카운트(application) : <font color='red'><b>${aVisitCnt}</b></font>
	</p>
	<p><img src="${pageContext.request.contextPath}/images/3.jpg" width="300px" /></p>
	<hr/>
	<div class="row">
	<% if(mid.equals("admin")) {%> <!-- 어드민이 맞으면 아래를 보여주고  -->
		<div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage/t5_LoginDelete.jsp" class="btn btn-secondary form-control">전체 방문카운트 초기화</a></div>
	<%}  %><!--  아니면 안보여줘 -->
		<div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage/t5_LogOut.jsp" class="btn btn-secondary form-control">로그아웃</a></div>
	</div>
	<hr/>
</div>
<p><br/></p>
</body>
</html>
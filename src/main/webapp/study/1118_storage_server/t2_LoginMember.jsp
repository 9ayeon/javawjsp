<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mid = session.getAttribute("sMid") == null ? "" : (String) session.getAttribute("sMid");
	
	int visitCnt = 0;
	
	if(application.getAttribute("aVisitCnt") == null) {
		application.setAttribute("aVisitCnt", 1); // 첫 접속(초기치) 1로 줌
	}
	else {
	
		visitCnt = (int) application.getAttribute("aVisitCnt") + 1;
		application.setAttribute("aVisitCnt", visitCnt);
	}

	int sVisitCnt = 1;
	
	if(session.getAttribute("sVisitCnt") == null) {
		session.setAttribute("sVisitCnt", -1); // 첫 접속(초기치) 1로 줌
	}
	else {
		sVisitCnt = (int) session.getAttribute("sVisitCnt") + 1;
		session.setAttribute("sVisitCnt", session);
	}
	session.setAttribute("sVisitCnt", sVisitCnt);
	
%>
<!DOCTYPE html><!-- 이곳은 뷰 -->
<html>
<head>
  <meta charset="UTF-8">
  <title>t2_LoginMember.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container" align="center">
  <h2>회원 전용방</h2>
  <p><font color="blue">${sMid}</font>님 로그인 중이십니다.</p>
	<hr/>
	<p><img src="${pageContext.request.contextPath}/images/3.jpg" width="300px" /></p>
	<hr/>
	<p>
		방문카운트(session) : <font color='blue'><b>${sVisitCnt}</b></font><br/>
		전체 총 방문카운트(application) : <font color='red'><b>${aVisitCnt}</b></font>
	</p>
	<div class="row">
	<% if(mid.equals("admin")) {%>
		<div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage_server/t2_cntDelete.jsp" class="btn btn-secondary form-control">전체 방문카운트 초기화</a></div>
	<%}  %><!--  아니면 안보여줘 -->
	<div class="row">
		<div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage/t2_LoginDelete.jsp" class="btn btn-secondary form-control">쿠키id삭제</a></div>
		<div class="col"><a href="${pageContext.request.contextPath}/study/storage/T2_LogOut" class="btn btn-secondary form-control">로그아웃</a></div>
	</div>
	<hr/>
</div>
</div>
<p><br/></p>
</body>
</html>
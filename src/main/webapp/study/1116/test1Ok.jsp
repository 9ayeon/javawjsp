<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- test1Ok.jsp(중간경로) -->
<%@ include file="../../include/bs4.jsp" %>
<%
	request.setCharacterEncoding("utf-8");

	String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid"); //널이면?공백처리, 뒤에걸가져올게
	String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
	String name = request.getParameter("name") == null ? "" : request.getParameter("name");
%>
<p><br/></p>
<div class="container">
<p>아이디 : <%=mid%></p> <!-- 표현식 -->
<p>비밀번호 : <%=pwd%></p> 
<p>성명 : <%=name%></p> 
<p><a href="test1.jsp" class="btn btn-info">돌아가기</a></p>
</div>
<% if(mid.equals("admin") && pwd.equals("1234")) { %>
		<jsp:forward page="test1Res.jsp">
			<jsp:param value="안녕" name="flag"/>
		</jsp:forward>
<% } else { %>
		<jsp:forward page="test1.jsp"></jsp:forward><!-- 어드민이 아니면 거꾸로, 메인홈으로 가라 -->
<% } %>

<!-- 20번라인 주석: res갈때가지고갈것.?(받는쪽에서 보는거) 밸류-값 네임-변수  -->
 <!-- 무조건 멈추지않고 바로 res로 간다.(forword)직렬화 -->
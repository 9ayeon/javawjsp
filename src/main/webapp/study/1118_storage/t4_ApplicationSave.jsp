<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t4_ApplicationSave.jsp -->
<%
	String mid = (request.getParameter("mid") == null || request.getParameter("mid") == "" ) ? "9ayeon" : request.getParameter("mid");
	String nickName = (request.getParameter("nickName") == null || request.getParameter("nickName") == "" )? "가연맨" : request.getParameter("nickName");
	int age = (request.getParameter("age") == null || request.getParameter("age") == "" ) ? 27 : Integer.parseInt(request.getParameter("age"));
	String name = (request.getParameter("name") == null || request.getParameter("name") == "" ) ? "양가연" : request.getParameter("name");

	application.setAttribute("sMid", mid);
	application.setAttribute("sNickName", nickName);
	application.setAttribute("sAge", age);
	application.setAttribute("sName", name);
%>
<script>
	alert("${sName}님 Application 저장 완료입니다요");
	location.href = "t4_ApplicationMain.jsp";
</script>
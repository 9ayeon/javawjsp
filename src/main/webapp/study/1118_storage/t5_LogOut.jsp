<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t5_LogOut.jsp -->
<%
	String mid = (String) session.getAttribute("sMid");
	// 나갈때는 세션을 닫아야하ㄴ~~~~~~~~~~~다
	session.invalidate();
	
%>
<script>
	alert("<%=mid%>님 로그아웃 되셨습니다.");
	location.href = "t5_Login.jsp";
</script>
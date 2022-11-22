<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t3_SessionDeleteNick.jsp -->
<%
	session.removeAttribute("sNickName");
%>
<script>
	alert("${sName}님의 닉네임 세션 삭제 완료입니다요");
	location.href = "t3_SessionMain.jsp";
</script>
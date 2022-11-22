<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t3_SessionDeleteAll.jsp -->
<%
	// 지우기 전에, 임시네임 변수 지정해주기(말풍선에 띄우기위해)
	String imsiName = (String) session.getAttribute("sName"); // 강제타입변환
	// el사용위해 임시네임 변수를 저장소에 담아주기
	pageContext.setAttribute("imsiName", imsiName);
	
	session.invalidate(); // 현재 저장된 모든 세션 삭제
%>
<script>
	alert("${imsiName}님의 모든 세션 삭제 완료입니다요");
	location.href = "t3_SessionMain.jsp";
</script>
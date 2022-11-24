<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<%-- <%
	String msg = (String) request.getAttribute("msg");
	String url = (String) request.getAttribute("url");
%> --%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>message.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <script>
  	'use strict'; 
  	let msg = "${msg}"; /* 서버프로그램이 먼저 시작해야하니까 */
  	let url = "${url}";
  	
  	if(msg == "guInputOk") msg = "방명록에 글이 등록되었습니다.";
  	else if(msg == "guInputNo") msg = "방명록에 글을 쓰지 못했습니다.";
  	else if(msg == "adminLoginOk") msg = "관리자 인증 성공";
  	else if(msg == "adminLoginNo") msg = "관리자 인증 실패";
  	else if(msg == "adminLogoutOk") msg = "관리자 로그아웃 성공";
  	else if(msg == "guDeleteOk") msg = "방명록의 글이 삭제 되었습니다.";
  	else if(msg == "guDeleteNo") msg = "방명록 삭제 실패";
  	
  	alert(msg);
  	if(url != "") location.href = url; //url이 비어있지않을때만
  </script>
</head>
<body>

</body>
</html>
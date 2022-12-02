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
  	let val = "${val}";
  	
  	if(msg == "guInputOk") msg = "방명록에 글이 등록되었습니다.";
  	else if(msg == "guInputNo") msg = "방명록에 글을 쓰지 못했습니다.";
  	else if(msg == "adminLoginOk") msg = "관리자 인증 성공";
  	else if(msg == "adminLoginNo") msg = "관리자 인증 실패";
  	else if(msg == "adminLogoutOk") msg = "관리자 로그아웃 성공";
  	else if(msg == "guDeleteOk") msg = "방명록의 글이 삭제 되었습니다.";
  	else if(msg == "guDeleteNo") msg = "방명록 삭제 실패";
  	else if(msg == "loginOk") msg = val + " 님 로그인 성공";
  	else if(msg == "loginNo") msg = "로그인 실패";
  	else if(msg == "memLogoutOk") msg = val + " 님 로그아웃 되었습니다.";
  	else if(msg == "idCheckNo") msg = "중복된 아이디 입니다.";
  	else if(msg == "nickCheckNo") msg = "중복된 닉네임 입니다.";
  	else if(msg == "memJoinOk") msg = "회원에 가입 되셨습니다.";
  	else if(msg == "memJoinNo") msg = "회원가입이 되지않았습니다.";
  	else if(msg == "passwordNo") msg = "비밀번호를 확인하세요.";
  	else if(msg == "passwordOk") msg = "비밀번호를 변경했습니다.";
  	else if(msg == "passwordOkNo") msg = "비밀번호 변경을 실패했습니다.";
  	else if(msg == "passwordYes") msg = "회원정보 수정 페이지로 이동합니다.";
  	else if(msg == "memUpdateOk") msg = "회원정보 수정이 완료되었습니다.";
  	else if(msg == "memUpdateNo") msg = "회원정보 수정에 실패하였습니다.";
  	else if(msg == "levelCheckOk") msg = "회원 등급이 변경되었습니다.";
  	else if(msg == "memberDelOk") msg = "정상적으로 회원 탈퇴가 되셨습니다.";
  	else if(msg == "adMemberDelOk") msg = "탈퇴신청한 회원의 정보를 삭제했습니다.";
  	else if(msg == "boInputOk") msg = "게시글이 성공적으로 입력되었습니다.";
  	else if(msg == "boInputNo") msg = "게시글이 입력 취소되었습니다.";
  	else if(msg == "boDeleteOk") msg = "게시글이 삭제되었습니다.";
  	else if(msg == "boDeleteNo") msg = "게시글이 삭제 되지 않았습니다.";
  	else if(msg == "userCheckNo") msg = "본인의 게시글이 아닌 글은 삭제할 수 없습니다. 해커세요?";
  	else if(msg == "boUpdateOk") msg = "게시글이 정상적으로 수정 되었습니다.";
  	else if(msg == "boUpdateNo") msg = "게시글이 수정되지 않았습니다.";
  	
  	alert(msg);
  	if(url != "") location.href = url; //url이 비어있지않을때만
  </script>
</head>
<body>

</body>
</html>
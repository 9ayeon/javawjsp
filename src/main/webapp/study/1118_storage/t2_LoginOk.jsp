<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_LoginOk.jsp -->
<%
	String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
	String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");

	// admin과 ygy232만 통과시키기
	if((mid.equals("admin") && pwd.equals("1234")) || (mid.equals("ygy232") && pwd.equals("1234"))) {
		// 쿠키 생성 후, 쿠키 저장
		Cookie cookie = new Cookie("cMid", mid);
		cookie.setMaxAge(60*5); // 쿠키의 만료시간을 5분으로 설정
		response.addCookie(cookie); // mid쿠키저장. 저장된 쿠키 이름:cMid
		
		out.print("<script>");
		out.print("alert('"+mid+"님 로그인 되셨습니다.');");
		out.print("location.href='t2_LoginMember.jsp';"); // admin과 ygy232가 아닐 경우 뒤로가기
		out.print("</script>");
	}
	else {
		out.print("<script>");
		out.print("alert('아이디와 비밀번호를 확인해주세요.');");
		out.print("history.back();"); // admin과 ygy232가 아닐 경우 뒤로가기
		out.print("</script>");
	}
%>
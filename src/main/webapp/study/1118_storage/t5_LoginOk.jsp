<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t5_LoginOk.jsp -->
<%
	String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
	String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");

	if((mid.equals("admin") && pwd.equals("1234")) || (mid.equals("9ayeon") && pwd.equals("1234"))) {
		// 로그인이 성공했을때
		session.setAttribute("sMid", mid); //나중에필요하면 더만드셈
		
		// 변수 주기(방문카운트)
		int visitCnt = 0;
		
		if(application.getAttribute("aVisitCnt") == null) { // 없을때.. 널이면..1
			application.setAttribute("aVisitCnt", 1); // 첫 접속(초기치) 1로 줌
		}
		else {
		// 방문할때마다 1씩 증가 (로그인할때마다)
			visitCnt = (int) application.getAttribute("aVisitCnt") + 1;
			application.setAttribute("aVisitCnt", visitCnt);
		}
		//(세션변수는 현재 브라우저만이니까 전역,전체변수인 어플리케이션변수로 세팅)
		
		
		
		// 로그인 성공시
		out.print("<script>");
		out.print("alert('"+mid+"님 로그인 되셨습니다.');");
		out.print("location.href='t5_LoginMember.jsp';"); 
		out.print("</script>");
	}
	else {
		out.print("<script>");
		out.print("alert('아이디와 비밀번호를 확인해주세요.');");
		out.print("history.back();");
		out.print("</script>");
	}
%>
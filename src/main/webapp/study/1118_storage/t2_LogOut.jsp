<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_LogOut.jsp -->
<%
/* 	Cookie[] cookies = request.getCookies(); // 클라이언트에 요청해서 쿠키 가져오기
	
	String mid = ""; //아래 alert에 쓰려고 선언
	
		// 쿠키의 유효성검사
	if(cookies != null) {
		for(int i=0; i<cookies.length; i++) {// 반복문으로
 			//if(cookies[i].getName().equals("cMid")) { // 쿠키의 아이디를 삭제
				mid = cookies[i].getValue(); // mid에 쿠키값담기
				pageContext.setAttribute("mid", mid); //저장소에 넣기
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				break; //빠져나오기
			//} */
		/* }
	} */
	String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
  pageContext.setAttribute("mid", mid);
%>

<script>
	alert("${mid}님 로그아웃 되셨습니다.")
	location.href = "t2_Login.jsp";
</script>
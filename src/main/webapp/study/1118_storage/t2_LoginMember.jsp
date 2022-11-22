<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	Cookie[] cookies = request.getCookies(); // 클라이언트에 요청해서 가져오기
	String mid = "";//전역변수 설정(아래에다 사용해야되서)
	
	// 쿠키의 유효성검사
	if(cookies != null) {
		for(int i=0; i<cookies.length; i++) {// 반복문으로
			if(cookies[i].getName().equals("cMid")) {
				mid = cookies[i].getValue();// cMid에 들어있는 값을 담는다. (admin or ygy232)
				pageContext.setAttribute("mid", mid); // 저장소에 mid를 mid로 저장한다.
				break; // 빠져나온다.
			}
		}
	}
	
	String imsiMid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
	if(mid.equals("")) pageContext.setAttribute("mid", imsiMid);
	// = 쿠키에 담겨있는 cMid의 값을 mid라는 변수명으로 저장소에 담아서 가져온 거~~
%>
<!DOCTYPE html><!-- 이곳은 뷰 -->
<html>
<head>
  <meta charset="UTF-8">
  <title>t2_LoginMember.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container" align="center">
  <h2>회원 전용방</h2>
  <p><font color="blue">${mid}</font>님 로그인 중이십니다.</p>
	<hr/>
	<p><img src="${pageContext.request.contextPath}/images/3.jpg" width="300px" /></p>
	<hr/>
	<div class="row">
		<div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage/t2_LoginDelete.jsp" class="btn btn-secondary form-control">쿠키의 아이디 삭제</a></div>
		<div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage/t2_LogOut.jsp?mid=${mid}" class="btn btn-secondary form-control">로그아웃</a></div>
	</div>
	<hr/>
</div>
<p><br/></p>
</body>
</html>
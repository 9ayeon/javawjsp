<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>t1_CookiesCheck.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
  <H2>쿠키 확인</H2>
  <hr/>
  <!-- request로 접속한 클라이언트에 값을 요청하고 읽어오겠다, request요청도 내장객체임 -->
<!-- 테이블적용  상상해서 프린트에 적용  -->
<%
	Cookie[] cookies = request.getCookies();
	
	out.println("<table class='table table-hover text-center'>");
	out.println("<tr class='table-dark'><th>번호</th><th>저장된 쿠키명</th><th>저장된 쿠키값</th></tr>");
	for(int i=0; i<cookies.length; i++) { // 저장된 쿠키의 갯수만큼
		out.print("<tr>");
		String strName = cookies[i].getName(); // 쿠키명(저장시켜놓은 쿠키변수명) 가져오기
		String strValue = cookies[i].getValue(); // 쿠키값(저장시켜놓은 쿠키값) 가져오기
		out.print("<td>" + (i+1) + "</td>"); // 번호, 0부터나오면 이상하니까~ +1~~
		out.print("<td>" + strName + "</td>"); // 쿠키명
		out.print("<td>" + strValue + "</td>"); // 쿠키값
		out.print("</tr>");
	}
	out.println("</table>");
%><!-- 이거 원래 위에 올려야하는거임 공부하려고 여기서 하는거/ 쿠키가 여러개가 오기때문에 배열로 만듦, 배열이기때문에 cookies임. 배열이 아니면 s가 붙지않음. jsp의 내장객체임.(임포트가붙지않음)-->
<hr/>
<div>
	<a href="t1_CookiesMain.jsp" class="btn btn-outline-warning form-control">돌아가기</a>
</div>
</div>
<p><br/></p>
</body>
</html>
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
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>t2_Login.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
  <form name="myform" method="post" action="t2_LoginOk.jsp" > <!-- ok에서 로그인값받는다? -->
  	<table class="table table-bordered text-center">
  		<tr>
  			<td colspan="2"><font size="5">로 그 인</font></td>
  		</tr>
  		<tr>
  			<th class="bg-info text-white">아이디</th> <!-- el표기법은 서버 저장소에 넣어야만 사용이 가능하다.12번라인 -->
  			<td><input type="text"	name="mid" value="${mid}" autofocus required class="form-control" /></td>
  		</tr>
  		<tr>
  			<th class="bg-info text-white">비밀번호</th>
  			<td><input type="password"	name="pwd"  required class="form-control" /></td>
  		</tr>
  		<tr>
 				<td colspan="2">
  				<input type="submit" value="로그인" class="btn btn-outline-info"/> &nbsp;&nbsp;
  				<input type="reset"	value="다시입력" class="btn btn-outline-secondary" />
  			</td>
  		</tr>
  	</table>
  </form>
</div>
<p><br/></p>
</body>
</html>
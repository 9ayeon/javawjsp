<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	Cookie[] cookies = request.getCookies(); 
	String mid = "";
	
	// 쿠키의 유효성검사
	if(cookies != null) {
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cMid")) {
				mid = cookies[i].getValue();// 
				pageContext.setAttribute("mid", mid); 
				break; 
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
  <form name="myform" method="post" action="${pageContext.request.contextPath}/study/storage/T2_LoginOk" > <!-- 절대경로로 자바 -->
  	<table class="table table-bordered text-center">
  		<tr>
  			<td colspan="2"><font size="5">로 그 인</font></td>
  		</tr>
  		<tr>
  			<th class="bg-info text-white">아이디</th>
  			<td><input type="text"	name="mid" value="<%=mid%>" autofocus required class="form-control" /></td>
  		</tr>
  		<tr>
  			<th class="bg-info text-white">비밀번호</th>
  			<td><input type="password"	name="pwd"  required class="form-control" /></td>
  		</tr>
  		<tr>
  			<td colspan="2"><input type="checkbox" name="checkbox" value="아이디저장" checked />&nbsp;아이디저장</td>
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
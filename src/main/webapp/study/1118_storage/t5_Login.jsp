<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>t5_Login.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
  <form name="myform" method="post" action="t5_LoginOk.jsp" > <!-- ok에서 로그인값받는다? -->
  	<table class="table table-bordered text-center">
  		<tr>
  			<td colspan="2"><font size="5">로 그 인</font></td>
  		</tr>
  		<tr>
  			<th class="bg-info text-white">아이디</th> <!-- el표기법은 서버 저장소에 넣어야만 사용이 가능하다.12번라인 -->
  			<td><input type="text"	name="mid" value="9ayeon" autofocus required class="form-control" /></td>
  		</tr>
  		<tr>
  			<th class="bg-info text-white">비밀번호</th>
  			<td><input type="password"	name="pwd" value="1234" required class="form-control" /></td>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memLogin.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <script>
  
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<div class="modal-dialog">
		<div class="modal-content">
		 <div class="container">
		   <form name="myform" method="post" action="${ctp}/memLoginOk.mem" class="was-validated"> <!--  Ok터는 컨트롤러 타고 데이터에 전송해주는거 -->
			  	<H2>회원 로그인</H2>
			  	<p>회원 아이디와 비밀번호를 입력해주세요</p>
			  	<div class="form-group">
			      <label for="mid">회원 아이디 :</label>
			      <input type="text" class="form-control" id="mid" value="${mid}" placeholder="아이디 입력하세요." name="mid" required autofocus />
			      <div class="valid-feedback">아이디 입력 확인</div>
			      <div class="invalid-feedback">아이디 입력은 필수입니다.</div>
			    </div>
			  	<div class="form-group">
			      <label for="pwd">비밀번호 :</label>
			      <input type="password" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요." name="pwd" required />
			      <div class="valid-feedback">비밀번호 입력 확인</div>
			      <div class="invalid-feedback">비밀번호 입력은 필수입니다.</div>
			    </div>
			    <div class="form-group">
				    <button type="submit" class="btn btn-warning">로그인</button>
				    <button type="button" onclick="location.href='${ctp}/memJoin.mem';" class="btn btn-warning">회원가입</button>
				    <button type="reset" class="btn btn-outline-secondary">다시입력</button>
				    <button type="button" onclick="location.href='${ctp}/';" class="btn btn-outline-secondary">돌아가기</button>
			  	</div>
			  	<div class="row" style="font-size:12px">
			  		<span class="col"><input type="checkbox" name="idCheck" checked />아이디 저장</span>
			  		<span class="col">
			  			[<a href="#">아이디 찾기</a>] /
			  			[<a href="#">비밀번호 찾기</a>]
			  		</span>
		  	</div>
	 	 	</form>
	  </div>
 	 </div>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>
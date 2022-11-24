<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>guInput.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
  <form name="myform" method="post" action="${ctp}/guInputOk.gu" class="was-validated"> <!--  Ok터는 컨트롤러 타고 데이터에 전송해주는거 -->
  	<H2>방 명 록 글 쓰 기</H2>
  	<br/>
  	<div class="form-group">
      <label for="name">성명</label>
      <input type="text" class="form-control" id="name" placeholder="이름을 입력하세요." name="name" required />
      <div class="valid-feedback">이름 입력 확인</div>
      <div class="invalid-feedback">이름 입력은 필수입니다.</div>
    </div>
  	<div class="form-group">
      <label for="email">E-mail</label>
      <input type="text" class="form-control" id="email" placeholder="이메일주소를 입력하세요." name="email" />
      <!-- <div class="valid-feedback">이메일 입력은 선택사항입니다.</div>
      <div class="invalid-feedback">이메일 입력은 선택사항입니다.</div> -->
    </div>
  	<div class="form-group">
      <label for="homePage">Homepage</label>
      <input type="text" class="form-control" id="homePage" value="http://" placeholder="홈페이지 주소를 입력하세요." name="homePage" />
      <!-- <div class="valid-feedback">홈페이지 입력은 선택사항입니다.</div>
      <div class="invalid-feedback">홈페이지 입력은 선택사항입니다.</div> -->
    </div>
  	<div class="form-group">
      <label for="content">방문소감</label>
      <textarea rows="5" class="form-control" id="content" name="content" required ></textarea>
      <div class="valid-feedback">입력 확인</div>
      <div class="invalid-feedback">방문소감 입력은 필수입니다.</div>
    </div>
    <div class="form-group">
	    <button type="reset" class="btn btn-outline-secondary">다시입력</button>
	    <button type="submit" class="btn btn-secondary">등록하기</button>
	    <button type="button" onclick="location.href='${ctp}/guList.gu';" class="btn btn-outline-secondary">돌아가기</button>
  	</div>
  	<!-- 히든태그사용해서 ip넘기기. (히든은 /form 위에서 작성해서 사용하자) -->
  	<input type="hidden" name="hostIp" value="<%=request.getRemoteAddr()%>"/>
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>
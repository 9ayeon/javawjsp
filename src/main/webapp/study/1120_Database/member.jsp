<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><!-- 이곳은 뷰 -->
<html>
<head>
  <meta charset="UTF-8">
  <title>member.jsp</title>
  <jsp:include page="../../include/bs4.jsp"></jsp:include>
  <script>
  'use strict';
  
  function searchMid(){ // 30번행 
	  let mid = prompt("찾고자 하는 아이디를 입력하세요.")
	  
	  location.href="${pageContext.request.contextPath}/database/SearchMid?mid="+mid; // get방식
  }
  </script>
</head>
<body>
<p><br/></p>
<div class="container" align="center">
  <h2>회원 전용방</h2>
  <p><font color="blue">${sName}</font>님 로그인 중이십니다.</p>
	<hr/>
	<p>
		현재 보유중인 포인트 : <font color='blue'><b>${point}</b></font><br/><!-- 리퀘스트저장소에 담은 것 (point) --> 
		최종 방문일자 : <font color='red'><b>${sLastDate}</b></font> <!-- 세션에 담아온 데이터,  -->
	</p>
	<p><img src="${pageContext.request.contextPath}/images/3.jpg" width="300px" /></p>
	<hr/>
	<div><a href="javascript:searchMid()" class="btn btn-outline-danger form-control m-3">회원 개별 조회</a></div>
	<div><a href="${pageContext.request.contextPath}/database/MemberList" class="btn btn-outline-danger form-control m-3">회원 전체 조회</a></div>
	<hr/>
	<div class="row">
		<div class="col"><a href="${pageContext.request.contextPath}/database/LogOut" class="btn btn-secondary form-control">로그아웃</a></div>
	</div>
	<hr/>
</div>
<p><br/></p>
</body>
</html>
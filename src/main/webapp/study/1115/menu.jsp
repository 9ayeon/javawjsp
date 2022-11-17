<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 메인 안떠나고 방명록,게시판,자료실갤러리를 메인에서 전부 부르기(화면내용만 바뀜),매개변수처리해야함 -->

	<!-- <div class="text-center"> 메뉴 가운데시
		<a href="main.jsp">홈으로</a> |구분기호 |로 줌
		<a href="guest.jsp">방명록</a> |
		<a href="board.jsp">게시판</a> |
		<a href="pds.jsp">자료실</a> |포스팅데이터시스템
		<a href="photo.jsp">갤러리</a>
	</div> -->
	<div class="text-center"> <!-- 매개변수 스위치로 주고 메인화면에서 모두 처리(메인바디부분만 바뀜)  -->
		<a href="main.jsp">홈으로</a> |
		<a href="main.jsp?sw=guest">방명록</a> |
		<a href="main.jsp?sw=board">게시판</a> |
		<a href="main.jsp?sw=pds">자료실</a> |
		<a href="main.jsp?sw=photo">갤러리</a>
	</div>

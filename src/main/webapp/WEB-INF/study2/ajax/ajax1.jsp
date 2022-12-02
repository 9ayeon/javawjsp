<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ajax1.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <script>
  	'use strict';
  	
  	function idCheck() {
  		/* let mid = document.getElementById("mid").value;
  		let mid = document.myform.mid.value; */
  		let mid = $("#mid").val();
  		
  		if(mid.trim() == "") {
  			alert("아이디를 입력하세요.");
  			$("#mid").focus();
  			return false;
  		}
  		
  		location.href = "${ctp}/idSearchTest?mid="+mid; // 디렉터리패턴,서블릿생성해야함. 아이디서치테스트에 mid를 보내야하니까 물음표두ㅣ에 
  	}
  	
  	function idCheck2() {
  		let mid = $("#mid").val();
  		
  		if(mid.trim() == "") {
  			alert("아이디를 입력하세요.");
  			$("#mid").focus();
  			return false;
  		}
  		
  		let query = {
  				mid: mid} 
  		// 서브단에있는변수/
  				/* idx: idx,
  				address: address */
  		
  		$.ajax({
  			url : "${ctp}/idSearchTest2", //전송 받는 곳의 주소
  			type : "get", //겟방식 // 문자인 경우 작따,큰따(변수) 에이잭스 묶어서 연산자 쉼표씀ㅁㅁㅁㅁㅁㅁㅁ
  			data : query, //넘길게 여러일경우 쉼표이용해서 idx: idx 이런식으로 하고, 바깥으로 변수줘서 let query부분보샘
  			// contextType : "application/json", // 전송방식, ajax의 전송방식의 기본이 json이라 생략가능.
  			//charset : "utf-8", //이것도 위에서 주고있어서 생략가능, 맥은 생략하지마쇼
  			success : function(res) { //ajax 정상처리시 들어가는 곳, 받고자하는 결과값이나변수를 괄호안에넣ㅇ자
  				alert("서버에서 아이디검색을 성공적으로 마치고 돌아왔습니다. 검색된 성명은 " + res);
  				$("#demo").html(res);
  			},
  			error : function() { // ajax 에러시 
  				alert("전송 실패");
  			} // 최종적으로 url, success, error는 생략불가하고, type은 기존get, data는 옮길게없다면 사용생략이가능.
  		});
  	}
  	function idCheck3() {
  		let mid = $("#mid").val();
  		
  		if(mid.trim() == "") {
  			alert("아이디를 입력하세요.");
  			$("#mid").focus();
  			return false;
  		}
  		
  		
  		$.ajax({
  			type : "post", 
  			url : "${ctp}/idSearchTest3",
  			data : {mid: mid}, // 키(서버에서받아주는변수)/밸류 
  			success : function(res) { 
  				$("#demo").html(res);
  				let str = res.split("/");
  				$("#tMid").html(str[0]); //val은 폼태그에붙임
  				$("#name").html(str[1]);
  				$("#nickName").html(str[2]);
  				$("#gender").html(str[3]);
  				$("#point").html(str[4]);
  			},
  			error : function() {  
  				alert("전송 실패");
  			} 
  		});
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
  <h2>AJax연습</h2>
  <hr/>
  <form name="myform">
  	아이디 : <input type="text" name="mid" id="mid" /> &nbsp;
  	<input type="button" value="아이디일반검색1" onclick="idCheck()" class="btn btn-info" /> &nbsp;
  	<input type="button" value="아이디검색2" onclick="idCheck2()" class="btn btn-info" /> &nbsp;
  	<input type="button" value="아이디검색3" onclick="idCheck3()" class="btn btn-info" /> &nbsp;
  	<br/>
  	<div>출력결과 : <span id="demo">${name}</span></div>
  	<hr/>
  	<p>
	  	<div>아이디 : <span id="tMid"></span></div>
	  	<div>성명 : <span id="name"></span></div>
	  	<div>닉네임 : <span id="nickName"></span></div>
	  	<div>성별 : <span id="gender"></span></div>
	  	<div>포인트 : <span id="point"></span></div>
  	</p>
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>
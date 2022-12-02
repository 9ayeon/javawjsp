<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>userList.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<script>
	'use strict';
	
	function userSearch(mid) {
		$.ajax({
			type : "post",
			url  : "${ctp}/userSearch.st",
			data : {mid: mid},
			success: function(res) {
				let str = res.split("/");
  				$("#mid").val(str[1]);//0번은업잔아....//    val은 폼태그에붙임
  				$("#name").val(str[2]);
  				$("#age").val(str[3]);
  				$("#address").val(str[4]); //오케이션리로드지우고,,,이거하니가,,똑같아졋잔아,,,3이랑,,,val로바꺼...html아니야,,//여기다올리면되잔아.....!!!근데...지우래..ㅋ..오케이션리로드지워,,갱신하는거라,,,,필요업대,,).유저서치커맨드에서얘끼한거,,, 근데가져와서뿌려야해,,,, 어디서냐면,,, 유저정보에내용넣어,,,,방법은여러가지지만,,,id?idx?사용했으니까,,
			},
			error : function() {
				alert("전송 실패");
			}
		});
	}
	
	function userDel(mid) {
		$.ajax({
			type : "post",
			url  : "${ctp}/userDel.st", //삭제하고돌아와라,,,
			data : {mid: mid},
			success: function(res) {
				if(res == "1") { // res 문자열 1이 넘어오면 삭제.
					alert("삭제완료");
  				location.reload();
				}
				else {
					alert("삭제실패");
				}
			},
			error : function() {
				alert("전송 실패");
			}
		});
	}
</script>
<p><br/></p>
<div class="container">
  <H2>AJax 연습</H2>
  <hr/>
  <form>
  	<table class="table table-bordered">
  		<tr>
  			<td colspan="2" class="text-center"><h3>User '가입/수정' 하기 폼</h3></td>
  		</tr>
  		<tr>
  			<th>아이디</th>
  			<td><input type="text" name="mid" id="mid" class="form-control"/></td>
  		</tr>
  		<tr>
  			<th>성명</th>
  			<td><input type="text" name="name" id="name" class="form-control"/></td>
  		</tr>
  		<tr>
  			<th>나이</th>
  			<td><input type="text" name="age" id="age" class="form-control"/></td>
  		</tr>
  		<tr>
  			<th>주소</th>
  			<td><input type="text" name="address" id="address" class="form-control"/></td>
  		</tr>
  		<tr>
  			<td colspan="2" class="text-center">
  				<input type="button" value="유저등록" onclick="userInput()" class="btn btn-info" />
  				<input type="reset" value="다시입력" class="btn btn-outline-info" />
  				<input type="button" value="User수정" onclick="userUpdate()" class="btn btn-outline-info" />
  				<input type="button" value="전체보기" onclick="location.href='${ctp}/userList.st';" class="btn btn-outline-info" />
				</td>
  		</tr>
  	</table>
  </form>
  <hr/>
  <h3>전체 리스트</h3>
  <br/>
  <table class="table table-hover text-center">
  	<tr class="table-dark text-dark">
  		<th>번호</th><th>아이디</th><th>성명</th><th>나이</th><th>주소</th><th>비고</th>
  	</tr>
  	<c:forEach var="vo" items="${vos}"> <!-- 반복문 돌리기 -->
	  	<tr>
	  		<td>${vo.idx}</td>
	  		<td>${vo.mid}</td>
	  		<td>${vo.name}</td>
	  		<td>${vo.age}</td>
	  		<td>${vo.address}</td>
	  		<td>
	  			<a href="javascript:userSearch('${vo.mid}')" class="btn btn-warning">개별조회</a>
	  			<a href="javascript:userDel('${vo.mid}')" class="btn btn-danger">삭제</a>
	  		</td>
	  	</tr>
  	</c:forEach>
  	<tr><td colspan="6" class="m-0 p-0"></td></tr>
  </table>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>
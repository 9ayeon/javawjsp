<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memList.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <script>
  	'use strict';
  	
  	function midSearch()	{
  		let mid = myform.mid.value;
  		if(mid.trim() == "") {
  			alert("아이디를 입력하세요.");
  			myform.mid.focus();
  		}
  		else {
  			myform.submit();
  		}
  	}
  	
  	function delCheck(idx) {
  		let ans = confirm("탈퇴처리 시키겠습니까?");
  		if(ans) location.href='${ctp}/adMemberDel.ad?pag=${pag}&idx='+idx;
  	}
  	
  	function searCheck(e) {
    	let ans = confirm("등급을 수정하시겠습니까?");
    	if(!ans) return false;
    	
    	let items = e.value.split("/");
    	
    	let query = {
    			idx : items[1],
    			level : items[0]
    	}
    	
    	$.ajax({
    		type  : "post",
    		url   : "${ctp}/adMemberLevelAjax.ad",
    		data  : query,
    		success:function(res) {
  				alert("등급 수정완료!");
    			location.reload();
    		},
    		error : function() {
    			alert("전송 오류~~");
    		}
    	});
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <H2>전체 회원 리스트</H2>
  <br/>
  <form name="myform" method="post" action="${ctp}/adMemberSearch.ad">
  	<div class="row mb-2">
  		<div class="col form-inline">
  			<input type="text" name="mid" class="form-control" autofocus />&nbsp;
  			<input type="button" value="아이디개별검색" onclick="midSearch();" class="btn btn-outline-info" />	
  		</div>
  		<div class="col text-right"><button type="button" onclick="location.href='${ctp}/adMemList.ad';" class="btn btn-outline-info" >전체검색</button></div>
  	</div>
  </form>
  <table class="table table-hover text-center">
  	<tr class="table-dark text-dark">
  		<th>번호</th>
  		<th>아이디</th>
  		<th>닉네임</th>
  		<th>성명</th>
  		<th>가입일자</th>
  		<th>마지막접속일</th>
  		<th>등급</th>
  		<th>탈퇴유무</th>
  	</tr>
  	<c:forEach var="vo" items="${vos}" varStatus="st">
  		<tr>
  			<td>${vo.idx}</td>
  			<td><a href="${ctp}/adMemInfor.ad?mid=${vo.mid}&pag=${pag}">${vo.mid}</a></td>
  			<td>${vo.nickName}</td>
  			<td>${vo.name}<c:if test="${sLevel == 0 && vo.userInfor == '비공개'}"><font color='red'>(비공개)</font></c:if></td>
  			<td>${vo.startDate}</td>
  			<td>${vo.lastDate}</td>
  			<td>
  				<form name="levelForm" method="post" action="${ctp}/adMemberLevel.ad">
  					<!-- <select name="level" onchange="javascript:alert('회원정보를 변경하시려면, 등급변경 버튼을 클릭하세요.');"> -->
  					 <select name="level" onchange="searCheck(this)">
  						<option value="0" <c:if test="${vo.level==0}">selected</c:if>>관리자</option> <!-- 삼항연산자 사용해도됨 -->
  						<option value="1" <c:if test="${vo.level==1}">selected</c:if>>준회원</option> 
  						<option value="2" <c:if test="${vo.level==2}">selected</c:if>>정회원</option> 
  						<option value="3" <c:if test="${vo.level==3}">selected</c:if>>우수회원</option> 
  					</select>
  					<input type="submit" value="등급변경" class="btn btn-info btn-sm" />
  					<input type="hidden" name="idx" value="${vo.idx}" /> <!-- adMemberLevel에 idx와 level을 넘기면됨 -->
  				</form>
				</td>
  			<td>
  				<c:if test="${vo.userDel=='OK'}"><a href="javascript:delCheck(${vo.idx})"><font color="red">탈퇴신청</font></a></c:if>
  				<c:if test="${vo.userDel!='OK'}">활동중</c:if>
  			</td>
  		</tr>	
  	</c:forEach>
  	<tr><td colspan="8" class="m-0 p-0"></td></tr>
  </table>
</div>
<br/>
<!-- 블록 페이지 시작 -->
<div class="text-center">
  <ul class="pagination justify-content-center">
    <c:if test="${pag > 1}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=1">첫페이지</a></li>
    </c:if>
    <c:if test="${curBlock > 0}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=${(curBlock-1)*blockSize + 1}">이전블록</a></li>
    </c:if>
    <c:forEach var="i" begin="${(curBlock)*blockSize + 1}" end="${(curBlock)*blockSize + blockSize}" varStatus="st">
      <c:if test="${i <= totPage && i == pag}">
    		<li class="page-item active"><a class="page-link bg-secondary border-secondary" href="${ctp}/adMemList.ad?pag=${i}">${i}</a></li>
    	</c:if>
      <c:if test="${i <= totPage && i != pag}">
    		<li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=${i}">${i}</a></li>
    	</c:if>
    </c:forEach>
    <c:if test="${curBlock < lastBlock}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=${(curBlock+1)*blockSize + 1}">다음블록</a></li>
    </c:if>
    <c:if test="${pag < totPage}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=${totPage}">마지막페이지</a></li>
    </c:if>
  </ul>
</div>
<!-- 블록 페이지 끝 -->
<p><br/></p>
</body>
</html>
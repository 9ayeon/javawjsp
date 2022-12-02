<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>boContent.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <script>
  	'use strict';
  	
  	function goodCheck() {
  		$.ajax({
  			type : "post",
  			url  : "${ctp}/boGood.bo",
  			data : {idx : ${vo.idx}}, // idx는 숫자니까 이렇게줌. 문자는따옴표.
  			success:function(res) {
  				if(res == "0") alert("이미 좋아요 버튼을 클릭하셨습니다.");
  				location.reload();
  			},
  			error: function()	{
  				alert("전송 오류");
  			}
  		});
  	}
    function goodCheckPlus() {
    	$.ajax({
    		type  : "post",
    		url   : "${ctp}/boGoodPlusMinus.bo",
    		data  : {
    			idx : ${vo.idx},
    			goodCnt : 1
    		},
    		success:function() {
    			location.reload();
    		}
    	});
    }
    
    function goodCheckMinus() {
    	$.ajax({
    		type  : "post",
    		url   : "${ctp}/boGoodPlusMinus.bo",
    		data  : {
    			idx : ${vo.idx},
    			goodCnt : -1
    		},
    		success:function() {
    			location.reload();
    		}
    	});
    }
    
    // 게시글 삭제처리
    function boDelCheck() {
    	let ans = confirm("이 게시물을 삭제하시겠습니까?");
    	if(ans) location.href = "${ctp}/boDeleteOk.bo?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}&mid=${vo.mid}";
    }
    
    // 댓글 달기
    function replyCheck() {
    	let content = $("#content").val(); //컨탠트에들어있는 내용 담기
    	if(content.trim() == "") {
    		alert("댓글을 입력하세요.");
    		$("#content").focus();
    		return false;
    	}
    	let query = {
    			boardIdx : ${vo.idx},
    			mid      : '${sMid}',
    			nickName : '${sNickName}',
    			content  : content,
    			hostIp   : '${pageContext.request.remoteAddr}'
    	}
    	
    	
    	$.ajax({
    		type : "post",
    		url  : "${ctp}/boReplyInput.bo",
    		data : query,
    		success:function(res){
    			if(res == "1") {
    				alert("댓글이 입력되었습니다.");
    				location.reload();
    			}
    			else {
    				alert("댓글이 입력되지 않았습니다.");
    			}
    		},
  			error : function() {
  				alert("전송 오류");
    		}
    	});
    }
    
    // 댓글 삭제하기
    function replyDelCheck(idx) {
    	let ans = confirm("댓글을 삭제하시겠습니까?");
    	if(!ans) return false;
    	
    	$.ajax({
    		type : "post",
    		url  : "${ctp}/boReplyDeleteOk.bo",
    		data : {idx : idx},
    		success:function(res) {
    			if(res == "1") {
    				alert("댓글이 삭제되었습니다.");
    				location.reload();
    			}
    			else{
    				alert("댓글이 삭제되지 않았습니다.");
    			}
    		},
    		error : function()	{
    			alert("전송 오류");
    		}
    	});
    	
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
  <h2 class="text-center">글 내 용 보 기</h2>
  <br/>
  <table class="table table-borderless">
	  <tr>
	  	<td class="text-right">hostIp :${vo.hostIp}</td>
	  </tr>
  </table>
  <table class="table table-bordered">
  	<tr>
  		<th>글쓴이</th>
  		<td>${vo.nickName}</td>
  		<th>날짜</th>
  		<td>${fn:substring(vo.wDate,0,fn:length(vo.wDate)-2)}</td><%-- 맨뒤에 fn:length / 19 --%>
  	</tr>
  	<tr>
  		<th>글제목</th>
  		<td colspan="3">${vo.title}</td>
  	</tr>
  	<tr>
  		<th>이메일</th>
  		<td>${vo.email}</td>
  		<th>조회수</th>
  		<td>${vo.readNum}</td>
  	</tr>
  	<tr>
  		<th>홈페이지</th>
  		<td>${vo.homePage}</td>
  		<th>좋아용ㅋㅋ</th>
  		<td><a href="javascript:goodCheck()">
            <c:if test="${sSw == '1'}"><font color="red">❤</font></c:if>
            <c:if test="${sSw != '1'}">❤</c:if>
          </a>
          ${vo.good} ,
          <a href="javascript:goodCheckPlus()">👍</a>
          <a href="javascript:goodCheckMinus()">👎</a>
     </td>
  	</tr>
  	<tr>
  		<th>글내용</th>
  		<td colspan="3" style="height:220px">${fn:replace(vo.content, newLine, "<br/>")}</td>
  	</tr>
  	<tr>
  		<td colspan="4" class="text-center">
  		<c:if test="${flag == 'search'}"><input type="button" value="돌아가기" onclick="location.href='${ctp}/boSearch.bo?search=${search}&searchString=${searchString}&pageSize=${pageSize}&pag=${pag}';" class="btn btn-outline-info"/></c:if>
  		<c:if test="${flag != 'search'}">
  		<input type="button" value="돌아가기" onclick="location.href='${ctp}/boList.bo?&pageSize=${pageSize}&pag=${pag}';" class="btn btn-outline-info"/>
  			<c:if test="${sMid == vo.mid || sLevel == 0}"> <%-- 현재 로그인 세션아이디가 vo아이디와 같을시 내 게시물 --%>
	  			<input type="button" value="수정하기" onclick="location.href='${ctp}/boUpdate.bo?idx=${vo.idx}&pageSize=${pageSize}&pag=${pag}';" class="btn btn-outline-warning"/>
	  			<input type="button" value="삭제하기" onclick="boDelCheck()" class="btn btn-outline-danger"/>
  			</c:if>
  		</c:if>	
  		</td>
  	</tr>
  </table>
	<c:if test="${flag != 'search'}">
		<!-- 이전글/다음글 처리 -->  
		<table class="table table-borderless">
			<tr>
				<td> <%-- 이전글은 나보다 작은 idx숫자중에 제일 큰값, 다음글은 나보다 큰 숫자중에 가장 작은 값을 가져온다. --%>
				<c:if test="${preVo.preIdx != 0}">
					👈 <a href="${ctp}/boContent.bo?idx=${preVo.preIdx}&pageSize=${pageSize}&pag=${pag}">이전글 : ${preVo.preTitle}</a><br/> 
				</c:if>
				<c:if test="${nextVo.nextIdx != 0}">
					👉 <a href="${ctp}/boContent.bo?idx=${nextVo.nextIdx}&pageSize=${pageSize}&pag=${pag}">다음글 : ${nextVo.nextTitle}</a>
				</c:if>
				</td>
			</tr>
		</table>
	</c:if>
</div>
<br/>
<!-- 댓글 리스트보여주기  -->
<div class="container">
	<table class="table table-hover text-center">
		<tr>
			<th>댓글쥔</th>
			<th>댓글내용</th>
			<th>작성일자</th>
			<th>접속IP</th>
			<c:if test="${sMid == replyVo.mid  || sLevel == 0}">
				<th>삭제하기</th>
			</c:if>
		</tr>
		<c:forEach var="replyVo" items="${replyVos}">
			<tr>
				<td>${replyVo.nickName}
				</td>
				<td>${replyVo.content}</td>
				<td>${replyVo.wDate}</td>
				<td>${replyVo.hostIp}</td>
				<td>
					<c:if test="${sMid == replyVo.mid  || sLevel == 0}">
						<a href="javascript:replyDelCheck(${replyVo.idx})" title="삭제하기">❌</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
<!-- 댓글 입력창 -->
	<%-- <form name="replyForm" method="post" action="${ctp}/boReplyInput.bo"> --%>
		<form name="replyForm">
		<table class="table text-center">
			<tr>
				<td style="width:85%" class="text-left">
					댓글내용 :
					<textarea rows="4" name="content" id="content" class="form-control"></textarea>
				</td>
				<td style="width:15%">
					<br/>
					<p>작성 : ${sNickName}</p>
					<p>
						<input type="button" value="댓글달기" onclick="replyCheck()" class="btn btn-outline-warning btn-sm"/>
					</p>
				</td>
			</tr>
		</table>
	</form>
<%-- 	<input type="hidden" name="boardIdx" value="${vo.idx}"/> <!-- 원본글의 idx,댓글의 idx중 원본글의 idx를 넘긴다. -->
	<input type="hidden" name="hostIp" value="${pageContext.request.remoteAddr}"/> <!-- 원본글의 idx,댓글의 idx중 원본글의 idx를 넘긴다. -->
	<input type="hidden" name="mid" value="${sMid}"/> <!-- 원본글의 idx,댓글의 idx중 원본글의 idx를 넘긴다. -->
	<input type="hidden" name="nickName" value="${sNickName}"/> <!-- 원본글의 idx,댓글의 idx중 원본글의 idx를 넘긴다. --> --%>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>
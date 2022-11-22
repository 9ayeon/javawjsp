<%@page import="study.j1116.Test2VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	 //vo를 담아온 변수이름(선언할때 이름은 객체명과 달라도됨) 적기
	Test2VO vo = (Test2VO) request.getAttribute("vo");//담으면서 바로 생성, 강제타입변환
%> <!-- 위로 자바코드, 아래로 프론트코드, mvcone? mvc1패턴 서버코드가 분리(자바와 프론트를 분리) -->
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>test2Ok4_2.jsp(저장소에서 헤더로 싣고온 것 출력하는 곳)</title>
  <%@ include file="../../include/bs4.jsp" %>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>이곳은 test2Ok4_2.jsp 입니다.</h2>
  <p>성명 : ${vo.name}</p> <!-- EL 꺽새퍼센트 없이처리 mvc1 ? mvcone ? 스파게티코드를 한쪽으로 몰았따? = 서버와 프론트에서 사용하는 코드를 위아래로 분리 -->
  <p>학번 : ${vo.hakbun}</p>
  <p>국어 : ${vo.kor}</p>
  <p>영어 : ${vo.eng}</p>
  <p>수학 : ${vo.mat}</p>
  <p>사회 : ${vo.soc}</p>
  <p>과학 : ${vo.sci}</p>
  <p>총점 : ${vo.tot}</p>
  <p>평균 : ${vo.avg}</p>
  <p>학점 : ${vo.grade}</p>
  <p><hr/></p>
  <%-- <p><a href="<%=request.getContextPath() %>/study/1116/test2.jsp" class="btn btn-info">돌아가기</a></p> --%>
  <p><a href="${pageContext.request.contextPath}/study/1116/test2.jsp" class="btn btn-info">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>
<%@page import="j1116h_gayeon.Hw1115VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Hw1115VO vo = (Hw1115VO) request.getAttribute("vo");
%> 

<h2><b>상 품 조 회</b></h2>
<br/>
<hr/>
  <p>구매자 : ${vo.guest}</p>
  <p>상품분류 : ${vo.classI}</p>
  <p>상품명 : ${vo.strProducts}</p>
  <p>가격 : ${vo.strPrice}</p>
  <p>수량 : ${vo.strSu}</p>
  <p>총 수량과 금액 : ${vo.strProduct}</p>
  <p>1수량배열 : ${vo.price}</p>
  <p>1각가격배열 : ${vo.su}</p>
  <p>1배열합계금액 : ${vo.tot}</p>
  <p>cnt : ${vo.cnt}</p>
  <p><a href="${pageContext.request.contextPath}/study/1116h/home.jsp" class="btn btn-info">돌아가기</a></p>
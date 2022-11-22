<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_CookiesSave.jsp / jsp에서 순수jsp만 할거면 다 지우고 타이틀만 주석처리 / jsp에서 서블릿코드사용-->
<%
	String mid = "hkd1234"; // 쿠키확인시 만들어진 순서대로 옹ㄹ라가있음(세션이맨위)
	Cookie cookieMid = new Cookie("cMid", mid); // 쿠키변수명앞엔 c를 붙인다.(헷갈림방지)//(쿠키가 가져가는,저장하는 변수명, 값이 들어있는(아이디같은거 저장되있는)("hkd1234"을 기본값으로 따옴표로 줘도 저장되긴함) 변수명)// (request와 같음)쿠키는 생성시 이름과 값을 줘야한다. (기본생성자가x)
	cookieMid.setMaxAge(60*60*24); // 쿠키mid의 생명주기(만료시간 : 단위 '초' 자바에서는 1000분의1초지만 여기서는 그냥 1초, 1일(60*60*24))
	
	String pwd = "1234";
	Cookie cookiePwd = new Cookie("cPwd", pwd); // 쿠키변수명앞엔 c를 붙인다.(헷갈림방지)//(쿠키가 가져가는,저장하는 변수명, 값이 들어있는(아이디같은거 저장되있는)("hkd1234"을 기본값으로 따옴표로 줘도 저장되긴함) 변수명)// (request와 같음)쿠키는 생성시 이름과 값을 줘야한다. (기본생성자가x)
	cookiePwd.setMaxAge(60*60*24); // 쿠키pwd의 생명주기(만료시간 : 단위 '초' 자바에서는 1000분의1초지만 여기서는 그냥 1초, 1일(60*60*24))
	
	String job = "학생";
	Cookie cookieJob = new Cookie("cJob", job); // 쿠키변수명앞엔 c를 붙인다.(헷갈림방지)//(쿠키가 가져가는,저장하는 변수명, 값이 들어있는(아이디같은거 저장되있는)("hkd1234"을 기본값으로 따옴표로 줘도 저장되긴함) 변수명)// (request와 같음)쿠키는 생성시 이름과 값을 줘야한다. (기본생성자가x)
	cookieJob.setMaxAge(60*60*24); // 쿠키job의 생명주기(만료시간 : 단위 '초' 자바에서는 1000분의1초지만 여기서는 그냥 1초, 1일(60*60*24))
	
	// 쿠키를 클라이언트에 저장하기(즉, 사용자 컴퓨터에 저장된다.)
	response.addCookie(cookieMid); // 만료시간을 지정한 mid쿠키가 저장된다.(1일동안 살아있다)
	response.addCookie(cookiePwd);
	response.addCookie(cookieJob);
%>
<!--  jsp에서 쿠키저장됐다는 알림후 메인으로 보내기 -->
<script>
	alert("쿠키에 저장 완료")
	location.href = "t1_CookiesMain.jsp";
</script>
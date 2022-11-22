<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_CookiesDeletePwd.jsp -->
<% 
	Cookie[] cookies = request.getCookies(); // 클라이언트에 요청해서 가져오기
	
	
		// 쿠키의 유효성검사
	if(cookies != null) {
		for(int i=0; i<cookies.length; i++) {// 반복문으로
			if(cookies[i].getName().equals("cPwd")) { // 쿠키의i번째의 cPwd와 이름이 같은것을
				cookies[i].setMaxAge(0); // 쿠키의 만료시간을 0으로 설정하여 쿠키를 제거한다.
				response.addCookie(cookies[i]); // 컴퓨터에 있는 것도 자동적으로 지워진다
			}
		}
	}
	// 쿠키를 만들었다 지우는데 정보를 살려줄것들은 지우면안되니까 지우고싶은것만 지우는경우도 많다.
	// 만약, 패스워드만 지우고 싶은 경우. 저장해놓은 쿠키변수의 이름을 찾아낸다.
	// 읽어온 값의(지울?) 네임을 꺼내서 cPwd와 이름이 같은지 비교함.(반복문안에서 6-7번라인)

%>
<script>
	alert("비밀번호 쿠키 삭제 완료")
	location.href = "t1_CookiesMain.jsp";
</script>
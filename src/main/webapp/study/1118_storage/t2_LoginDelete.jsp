<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_LoginDelete.jsp -->
<% 
	Cookie[] cookies = request.getCookies(); // 클라이언트에 요청해서 쿠키 가져오기
	
	String mid = ""; //아래 alert에 쓰려고 선언
	
		// 쿠키의 유효성검사
	if(cookies != null) {
		for(int i=0; i<cookies.length; i++) {// 반복문으로
			if(cookies[i].getName().equals("cMid")) { // 쿠키의 아이디를 삭제
				mid = cookies[i].getValue(); // mid에 쿠키값담기
				pageContext.setAttribute("mid", mid); //저장소에 넣기(다른 페이지에서도 출력되야 하니까 request)
				cookies[i].setMaxAge(0); // 여기까지 하면 컴퓨터에 기억은 되고, 사실상 삭제는 됨//쿠키의 만료시간을 0으로 설정하여 쿠키를 제거한다. (컴퓨터에 적용 전,)
				response.addCookie(cookies[i]); //쿠키의 상태 출력 이 선언을 하면 내 컴퓨터, 출력화면에서도 지워지고 완전히 지워진다 //컴퓨터에 있는 것도 자동적으로 지워진다
				break; //빠져나오기
			}
		}
	}
	// 쿠키를 만들었다 지우는데 정보를 살려줄것들은 지우면안되니까 지우고싶은것만 지우는경우도 많다.
	// 만약, 패스워드만 지우고 싶은 경우. 저장해놓은 쿠키변수의 이름을 찾아낸다.
	// 읽어온 값의(지울?) 네임을 꺼내서 cPwd와 이름이 같은지 비교함.(반복문안에서 6-7번라인)

%>
<script>
	alert("${mid}님 아이디 쿠키 삭제 완료");
	location.href = "t2_LoginMember.jsp?mid=${mid}"; //아이디변수로넘긴다
</script>
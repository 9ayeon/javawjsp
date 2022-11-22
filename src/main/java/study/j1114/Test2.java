package study.j1114;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1114_Test2")
public class Test2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("1.이곳은 /j1114_Test2 입니다.");
//		System.out.println("2.이곳은 /j1114_Test2 입니다.");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		/*
		//String mid = "";
		
		if(request.getParameter("mid") != null) { //널이 아니면, 읽어온값을 넘기면되고. 널이 넘어오면 mid.는 공백을넣어라
			String mid = request.getParameter("mid");
		}
		else {
			String mid = "";
		}
		*/
		
		System.out.println("전송방식 : " + request.getMethod());
		
		// 삼항연산자 사용, 스트링값에 넘어온 값이 널과 같으면 아이디값에 공백이들어간다. 아니면 그냥값을넘긴다?ㅠㅠㅠ
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		String hostIp = request.getParameter("hostIp");
		// String pwd = request.getParameter("pwd");
		
		System.out.println("접속 IP : " + hostIp);
		
		mid = mid.trim(); //입력되는 공백 자르기
		
		
		
		PrintWriter out = response.getWriter();
		
		if(mid.equals("admin") && pwd.equals("1234")) { //두가지를 만족해야만 성공
			out.println("<script>");
			out.println("alert('관리자 인증 성공.');");
			out.println("location.href='"+request.getContextPath()+"/study/1114/test2Res.jsp?mid="+mid+"&name="+name+"&hostIp="+hostIp+"';"); //물음표뒤에 변수명 , 주소와 값을 
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('관리자 인증 실패.');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
}

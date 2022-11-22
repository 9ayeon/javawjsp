package study.storage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/study/storage/T2_LoginOk")
public class T2_LoginOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
		String checkbox = request.getParameter("checkbox");

		PrintWriter out = response.getWriter();
		Cookie cookie = new Cookie("cMid", mid);

		
		ServletContext application = request.getServletContext(); 
	  HttpSession session = request.getSession(); 
	  session.setAttribute("sMid", mid); 
	  
	  
	
		  
		 
		if (checkbox != null) {
			cookie.setPath("/");
			cookie.setMaxAge(60 * 5);
			response.addCookie(cookie);
		} else {
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		if ((mid.equals("admin")) && (pwd.equals("1234")) || (mid.equals("9ayeon")) && (pwd.equals("1234"))) {
			cookie.setPath("/"); // 웹 어플리케이션에서 모든 URL에서 전송가능하도록 설정.
			out.print("<script>");
			out.print("alert('" + mid + "님 로그인 되셨습니다.');");
			out.print("location.href='" + request.getContextPath() + "/study/1118_storage_server/t2_LoginMember.jsp';");
			out.print("</script>");
		} else {
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			out.print("<script>");
			out.print("alert('아이디와 비밀번호를 확인해주세요.');");
			out.print("history.back();");
			out.print("</script>");
		}
	}
}

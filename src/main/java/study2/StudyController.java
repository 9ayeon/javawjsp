package study2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.st")
public class StudyController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudyInterface command = null;
		String viewPage = "/WEB-INF/study2";
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		// 세션이 끊겼다면 작업의 진행을 중지 시키고 홈으로 전송 시켜준다.
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")== null ? 99 : (int) session.getAttribute("sLevel");
		
		if(level >= 4) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/"); // 우리사이트의루트로?....????...여튼,,,홈으로,,
			dispatcher.forward(request, response);
		}
		else if(com.equals("/pass")) {
			viewPage += "/password/pass.jsp";
		}
		else if(com.equals("/passOk1")) {
			command = new PassOkCommand();
			command.execute(request, response);
			viewPage += "/password/pass.jsp";
		}
		else if(com.equals("/passOk2")) {
			command = new PassOk2Command();
			command.execute(request, response);
			viewPage += "/password/passOk2.jsp";
		}
		else if(com.equals("/ajax1")) {
			viewPage += "/ajax/ajax1.jsp";
		}
		else if(com.equals("/userList")) {
			command = new UserListCommand();
			command.execute(request, response);
			viewPage += "/ajax/userList.jsp";
		}
		else if(com.equals("/userSearch")) {
			command = new UserSearchCommand();
			command.execute(request, response); // 일만하고오렴
			return;
		}
		else if(com.equals("/userDel")) {
			command = new UserDelCommand();
			command.execute(request, response); // 일만하고오렴
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}

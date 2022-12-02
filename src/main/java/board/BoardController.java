package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemJoinOkCommand;

@SuppressWarnings("serial")
@WebServlet("*.bo")
public class BoardController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardInterface command = null;
		String viewPage = "/WEB-INF/board";
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		// 세션이 끊겼다면 작업의 진행을 중지 시키고 홈으로 전송 시켜준다.
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")== null ? 99 : (int) session.getAttribute("sLevel");
		
		if(level >= 4) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/"); // 우리사이트의루트로?....????...여튼,,,홈으로,,
			dispatcher.forward(request, response);
		}
		else if(com.equals("/boList")) {
			command = new BoListCommand();
			command.execute(request, response);
			viewPage += "/boList.jsp";
		}
		else if(com.equals("/boInput")) {
			command = new BoInputCommand();
			command.execute(request, response);
			viewPage += "/boInput.jsp";
		}
		else if(com.equals("/boInputOk")) {
			command = new BoInputOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/boContent")) {
			command = new BoContentCommand();
			command.execute(request, response);
			viewPage += "/boContent.jsp";
		}
		else if(com.equals("/boGood")) {
			command = new BoGoodCommand();
			command.execute(request, response);
			return; //돌아가기가 없으면 리턴
		}
		else if(com.equals("/boGoodPlusMinus")) {
			command = new BoGoodPlusMinusCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/boDeleteOk")) {
			command = new BoDeleteOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/boUpdate")) {
			command = new BoUpdateCommand();
			command.execute(request, response);
			viewPage += "/boUpdate.jsp";
		}
		else if(com.equals("/boUpdateOk")) {
			command = new BoUpdateOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/boSearch")) {
			command = new BoSearchCommand();
			command.execute(request, response);
			viewPage += "/boSearch.jsp";
		}
		else if(com.equals("/boReplyInput")) {
			command = new BoReplyInputCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/boReplyDeleteOk")) {
			command = new BoReplyDeleteOkCommand();
			command.execute(request, response);
			return;
		}
		
		/*
		 * else if(com.equals("/memJoinOk")) { command = new MemJoinOkCommand();
		 * command.execute(request, response); viewPage = "/include/message.jsp"; }
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
}

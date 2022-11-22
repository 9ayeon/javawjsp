package study2.mapping2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.calc")
public class CalcController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MappingInterface command = null; // 인터페이스 생성하기 위한 선언 (매핑인터페이스 타입으로 커맨드를 만든다?)
		// 인터페이스 생성이유 : 공통메소드 사용하기 위해서
		
		String viewPage = "/WEB-INF/study2/mapping2";
		
		String uri = request.getRequestURI();    //  /부터       . 앞까지 찾겠다
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf(".")); 
		
		if(com.equals("/Calc")) {
			viewPage += "/calc.jsp";
		}
		else if(com.equals("/CalcOk")) {
			command = new CalcOkCommand(); // 매핑인터페이스의 이름으로(인플리먼트,) 구현함 매핑인터페이스에속함?
			command.excute(request, response);
			viewPage += "/calcOk.jsp";
		}
		else if(com.equals("/JuList")) {
			command = new JuListCommand(); // 매핑인터페이스의 이름으로(인플리먼트,) 구현함 매핑인터페이스에속함?
			command.excute(request, response); // 주리스트커맨드에서 응답,요청
			viewPage += "/juList.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}

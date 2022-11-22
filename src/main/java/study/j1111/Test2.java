package study.j1111;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/T2") //WebServlet = jsp의 컨트롤러,
public class Test2 extends HttpServlet {
	//private static final long serialVersionUID = 1L; // 직렬화, 코드를 물흐르듯
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} //넘겨주고 넘겨받는다. request - 요청한다 response- 응답한다,받는다.

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);//포스트로 요청하면, 포스트에서 겟으로 넘겨서 겟이처리한다.=겟으로 넘기든 포스트로 넘기든 겟을 거친다
//		//메소드호출, doGet메소드를 호출한다.
//	}

}

package study.el_jstl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/study/el_jstl/El1")
public class El1 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse responser) throws ServletException, IOException {
		ElJstlVO vo = new ElJstlVO();
		// vo생성한 객체를 서블릿에서 불러와 셋해서 값을 담고 저장소에 담고 디스패처로 보낸다
		vo.setAtom(request.getParameter("atom"));
		vo.setName(request.getParameter("name"));
		vo.setSu1(Integer.parseInt(request.getParameter("su1")));
		vo.setSu2(Integer.parseInt(request.getParameter("su2")));
		
		request.setAttribute("vo", vo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1121_EL_JSTL/el1.jsp");
		dispatcher.forward(request, responser);
	}
}

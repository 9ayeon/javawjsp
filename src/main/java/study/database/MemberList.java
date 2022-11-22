package study.database;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/database/MemberList")
public class MemberList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 중간열람망, dao(액세스역할)에 보내서 dao에서 가져오는 서블릿인데, 
		// -서비스객체- 니까 앞에서 받아오는게 없어도 dao연동.
		JusorokDAO dao = new JusorokDAO();
		
		ArrayList<JusorokVO> vos = dao.getMemberList(); //한 건이 아니라 여러건이니까 어레이리스트사용, vo가 모여서 vos. 어레이리스트vo는 vos로 만든다 // 멤버서치는 아이디1개 가져오는거고 이거는 전체리스트
		
		// 담아온 vos를 request에 담는다
		request.setAttribute("vos", vos);
		// vos가 담긴 request를 dispatcher에 담아보낸다. (Dispatcher는 그냥 바로 주소적는다.(${page어쩌구}쓰루해도됨))
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1120_Database/memberList.jsp");
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1120_Database/memberMain");
		// dispatcher 보내기 (memberList로)
		dispatcher.forward(request, response);
	}
}

package study.database;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/database/MemberMain")
public class MemberMain extends HttpServlet{ // 여러번 가야할 페이지, 메인 서블릿 하나를 만들고 필요한 자료들(여러번 거쳐야할 페이지)을 담아서 사용한다. 
	@Override //멤버메인:엄마캥거루 엄마캥거루를 만들고 새끼캥거루 달고다니니까 엄마캥거루 계속 필요할때마다 소환하면 새끼캥거루까지 딸려옴(내가필요한새끼캥거루들을 엄마캥거루가갖고있으니까 엄마캥거루뱃속에있는 새끼캥거룰르 소환하려면 엄마캥거루만부르면된다)(
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션생성
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid"); //스트링형변환         //LoginOk 52번행에서 실어온 mid 받기(취소에염)

		// dao 객체생성. 연결
		JusorokDAO dao = new JusorokDAO();
		
		// 회원정보조회를 vo에 담는다.
		JusorokVO vo = dao.getMemberSearch(mid);// 메소드생성, mid 중복체크필수..
	
		// vo에서 가져온 것들을 request 저장소에 담는다
		request.setAttribute("point", vo.getPoint());
		System.out.println("point : "+ vo.getPoint());
		// request.setAttribute("lastDate", vo.getLastDate()); //LoginOk43행에 만들었으니 주석
		// dispatcher생성, member에 값을 갖고간다?
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1120_Database/member.jsp");
		// 디스패처 실어서 보낸다.
		dispatcher.forward(request, response);
	}
}

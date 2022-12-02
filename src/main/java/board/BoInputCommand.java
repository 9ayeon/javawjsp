package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDAO내꺼;
import member.MemberVO;

public class BoInputCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		
		MemberDAO dao = new MemberDAO(); //멤버에서가져오기
		
		MemberVO vo = dao.getLoginCheck(mid); // 로그인체크 아이디로 가져온다
		
		request.setAttribute("email", vo.getEmail());
		request.setAttribute("homePage", vo.getHomePage());
	}

}

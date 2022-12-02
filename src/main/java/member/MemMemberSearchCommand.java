package member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemMemberSearchCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		
		MemberDAO내꺼 dao = new MemberDAO내꺼();
		
		ArrayList<MemberVO> vos= dao.getMemberSearch(mid);
		
		request.setAttribute("vos", vos);

	}
}

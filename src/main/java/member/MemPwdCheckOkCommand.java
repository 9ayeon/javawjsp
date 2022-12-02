package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.SecurityUtil;

public class MemPwdCheckOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null? "" : request.getParameter("pwd");
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		MemberDAO내꺼 dao = new MemberDAO내꺼();
		
		MemberVO vo = dao.getLoginCheck(mid);
		
		if(!vo.getPwd().equals(pwd)) { // vo의 기존 비밀번호값과 입력값이 다르면
			request.setAttribute("msg", "passwordNo"); // 틀렸다는메세지
			request.setAttribute("url", request.getContextPath()+"/memPwdCheck.mem");
		}
		else {
			request.setAttribute("msg", "passwordYes"); 
			request.setAttribute("url", request.getContextPath()+"/memUpdate.mem");
		}
	}
}

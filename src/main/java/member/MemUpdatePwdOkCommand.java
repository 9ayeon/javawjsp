package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conn.SecurityUtil;

public class MemUpdatePwdOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sMid");
		String oldPwd = request.getParameter("oldPwd")==null? "" : request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd")==null? "" : request.getParameter("newPwd");
		
		SecurityUtil security = new SecurityUtil();
		oldPwd = security.encryptSHA256(oldPwd);
		newPwd = security.encryptSHA256(newPwd);
		
		MemberDAO내꺼 dao = new MemberDAO내꺼();
		
		MemberVO vo = dao.getLoginCheck(mid);
		
		if(!vo.getPwd().equals(oldPwd)) { // vo의 기존 비밀번호값과 oldpwd입력값이 다르면
			request.setAttribute("msg", "passwordNo"); // 틀렸다는메세지
			request.setAttribute("url", request.getContextPath()+"/memUpdatePwd.mem");
			return;
		}
		int res = dao.setmemUpdatePwdOk(mid, newPwd);
		
		if(res == 1) { //res가 정상처리라면
			request.setAttribute("msg", "passwordOk"); 
			request.setAttribute("url", request.getContextPath()+"/memMain.mem");
		}
		else {
			request.setAttribute("msg", "passwordOkNo"); 
			request.setAttribute("url", request.getContextPath()+"/memUpdatePwd.mem");
		}
	}
}

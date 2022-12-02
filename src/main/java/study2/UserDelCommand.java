package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.ajax.UserDAO;
import study2.ajax.UserVO;

public class UserDelCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		//아이디넘긴거받고
		UserDAO dao = new UserDAO();
		
		String res = dao.setUserDel(mid); //자료지우는거메소드만들고 // res가 1이면지워지고0이면안지워짐ㄴ거
		
		response.getWriter().write(res); //dao에서도 String으로 문자열변경해줌
		// 숫자 넘겨도 어차피 문자니까 상관없는데 가끔 헤더에 400번 에러가 나는데 문자숫자때문에 나는오류이다. 그래서 res를 스트링으로 위에서받는다.
	}

}

package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/database/JoinOk")
public class JoinOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");		
		
		// vo를 생성해서,,,,,,,
		JusorokVO vo = new JusorokVO();
		
		vo.setMid(mid); // vo에 값을 담어요..~^^
		vo.setPwd(pwd);
		vo.setName(name);
		
		// dao를,,,, 생성..
		JusorokDAO dao = new JusorokDAO();
		
		// 아이디 중복검사,,,,,,,,,,,,,,,,,,,,,,,,를,,하셍...
		
		// 중복체크 후 정상자료일 경우./. DB에 저장처리....
		// vo에 담은,, 데이터를,, res에 저장,,,,,,합니다,,,, res는 정수..
		int res = dao.setJoinOk(vo); // vo에 담긴걸,, dao(db)에 저장하세요...메소드생성...
		
		PrintWriter out = response.getWriter();
		
		if(res == 1) { // res가 1이면,,,정상처리,,,
			out.println("<script>"
								+ "alert('"+mid+" 님의 회원가입을 환영합니다.');"
								+ "location.href='"+request.getContextPath()+"/study/1120_Database/login.jsp';"
								+ "</script>");
		}
		else { // 아니면,,,,, 
			out.println("<script>");
			out.println("alert('회원가입을 완료하지 못했습니다. 다시 시도해주세요.');");
			out.println("location.href='"+request.getContextPath()+"/study/1120_Database/join.jsp';"); // history.back()을 넣으면,,,, 무한루프에 빠짐니다...ㅋ(거기도,,히스토리..빽이있기때문..ㅋ)
			out.println("</script>");
		}
		// vo에 담은 값을 주소록에 insert한다...~(dao134행)
	
	}
}

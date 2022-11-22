package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/database/LogOut")
public class LogOut extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// dao 연결
		JusorokDAO dao = new JusorokDAO();
		
		// db 연결 끊고오기
		dao.logout();
		
		// 세션객체 생성 (세션을 끊기위한, DAO에 설명해놓음)
		HttpSession session = request.getSession();
		// 세션 닫기 전에 이름가져오기 (로그아웃되셔ㅑㅆ습니다에 이름넣게)
		String name = (String) session.getAttribute("sName"); //형변환
		// 세션 닫기
		session.invalidate();
		
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('"+name+" 님 로그아웃 되셨습니다.');");
		out.println("location.href='"+request.getContextPath()+"/study/1120_Database/login.jsp';"); // location-get방식(앞에따당하고 리퀘스트겟컨텍스트패스)/보안폴더에 들어가있지않기때문에 컨트롤러로 보내는게 아님. jsp 직접 호출해서 보낸다.=경로 직접입력함.(다음부터는 보안폴더거침,컨트롤러사용)  history.back은 직렬화전용 서블릿거쳤을때 함부로 사용ㄴㄴ (전단계로 돌아가는건데, 여기서는 서블릿을 거쳤기 때문에 무한루프걸림)
		out.println("</script>");
	}
}

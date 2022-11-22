package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/database/SearchMid")
public class SearchMid extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		
		// dao생성
		JusorokDAO dao = new JusorokDAO();
		// mid데이터(dao에서가져온)vo에 담기
		JusorokVO vo = dao.getMemberSearch(mid);
		
		PrintWriter out = response.getWriter();
		
		// 검색에서 돌아와서 검사하기
		if(vo.getName() != null) { // vo에 담긴이름이 공백이아니면,
			// vo를  저장소에 담기
			request.setAttribute("vo", vo);
			// 저장소에 담은 vo를 dispatcher에 담아서 dispatcher를 경로로 보내기 (경로)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1120_Database/memberSearch.jsp");
			dispatcher.forward(request, response);
		}
		else {
			// 회원조회실패
			out.println("<script>");
			out.println("alert('아이디를 찾을 수 없습니다.');");
			out.println("location.href='"+request.getContextPath()+"/study/1120_Database/member.jsp';"); // location-get방식(앞에따당하고 리퀘스트겟컨텍스트패스)/보안폴더에 들어가있지않기때문에 컨트롤러로 보내는게 아님. jsp 직접 호출해서 보낸다.=경로 직접입력함.(다음부터는 보안폴더거침,컨트롤러사용)  history.back은 직렬화전용 서블릿거쳤을때 함부로 사용ㄴㄴ (전단계로 돌아가는건데, 여기서는 서블릿을 거쳤기 때문에 무한루프걸림)
			out.println("</script>");
		}
		
		
	}
}

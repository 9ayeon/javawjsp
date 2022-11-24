package guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuInputOkCommand implements GuestInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폼(input)에서 넘긴 값 vo에 담아서 dao에 넘기는 클래스
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		String email = request.getParameter("email") == null ? "" : request.getParameter("email");
		String homePage = request.getParameter("homePage") == null ? "" : request.getParameter("homePage");
		String content = request.getParameter("content") == null ? "" : request.getParameter("content");
		String hostIp = request.getParameter("hostIp") == null ? "" : request.getParameter("hostIp");
	
		
		// 성명에는 태그 사용금지 처리
		name = name.replace("<", "&lt;");
		name = name.replace(">", "&gt;");
		
		GuestDAO dao = new GuestDAO(); // dao생성
		GuestVO vo = new GuestVO(); // vo생성.
		String regEmail = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		
		
		vo.setName(name);
		if(email.matches(regEmail) == true) vo.setEmail(email);
		else vo.setEmail("이메일이 올바르지않습니다.");
		vo.setHomePage(homePage);
		vo.setContent(content);
		vo.setHostIp(hostIp); // vo에 담는다.
		
		// vo에 담긴 값 dao로 호출해서 res에 담는다.
		// dao에서 생성..
		int res = dao.setGuInput(vo); // res가 정상적이면 1을 보내고 정상적이지않으면 0을 보내게 만든다.
		
		if(res == 1) {
			request.setAttribute("msg", "guInputOk"); // 메세지와 url
			request.setAttribute("url", request.getContextPath()+"/guList.gu");
		}
		else { // 자료가 입력되지 않았을 경우
			request.setAttribute("msg", "guInputNo"); // 메세지와 url
			request.setAttribute("url", request.getContextPath()+"/guInput.gu");
			
		}
		// 스크립트를 사용하지 못하기 때문에 사용하도록 유도한다. (spring에서는 메세지컨트롤. 갔다오는것.직렬화?)
	
	}

}

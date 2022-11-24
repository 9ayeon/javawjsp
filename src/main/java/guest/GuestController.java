package guest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.gu") // 확장자패턴
public class GuestController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestInterface command = null; // 이거 공부좀
		String viewPage = "/WEB-INF/guest"; // 이 서블릿단에는 경로적지x 아래서 jsp로 넘길때는 이 뒤 경로적음/뷰페이지에 경로를추가할때는 += 사용
		
		String uri = request.getRequestURI(); // uri경로받고
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf(".")); // uri /부터 .앞까지 경로 com에 담기
		
		if(com.equals("/guList")) { // 초기페이지 , 방명록리스트 보여주는데 , 서비스객체가 할 일이 많다.(DB에서 데이터 가져오는 작업)(커맨드 객체 생성필요)
			command = new GuListCommand(); // 서비스객체 GuListCommand 객체클래스 생성.(게스트인터페이스구현)
			command.execute(request, response); // 앞에서 받아온거 넘겨줌. 컨트롤러로 경로에만 보내줌
			viewPage += "/guList.jsp";// 갔다 돌아오면 viewPage로 넣어준다
		}
		else if(com.equals("/guInput")) { // guInput(글쓰기) (방명록은 누구나(초기값x)들어올수있다.) 서비스객체가 할일이없다.
			// 누구나 들어와서 작성이 가능함 -> DB에 저장. 서비스객체 필요가 없다. (초기값x 글쓰는 화면은 데이터가져올게없음)
			viewPage += "/guInput.jsp";//
		}
		else if(com.equals("/guInputOk")) { // 
			command = new GuInputOkCommand(); // 서비스객체 GuInputOkCommand 객체클래스 생성.(게스트인터페이스구현) (폼에서 넘긴걸 vo에 담아서 dao에 넘기는 클래스, dao에서는 인서트로 넘긴다)
			command.execute(request, response); // 앞에서 받아온거 넘겨줌. 컨트롤러로 경로에만 보내줌
			viewPage = "/include/message.jsp"; // 메세지제이에스피만들어서보낸대 몰라./.jsp로 보내면 에러가 난다. 가져온 값이 정상적으로 나오지 않는다. 
			//inputok에서는 가져온값(방명록자료)들을 그대로 gulist(띄우는뷰)로 갖고 가야하는데 jsp로 보내면 
			//아무것도 가져가지않는다. 따라서 guList컨트롤러(gu확장자)로 보내야한다. 그리고 +=의 +를 지워야한다.
			//ctp사용도 불가이기에 직접 경로를 적어주어야 한다.)
		}
		else if(com.equals("/adminLogin")) { // 
			viewPage += "/adminLogin.jsp";//
		}
		else if(com.equals("/adminLoginOk")) { // 
			command = new AdminLoginOkCommand(); 
			command.execute(request, response); 
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/adminLogout")) { // 
			command = new AdminLogoutCommand(); 
			command.execute(request, response); 
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/guDelete")) { // 
			command = new GuDeleteCommand(); 
			command.execute(request, response); 
			viewPage = "/include/message.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		// 여기까지 초기화면 컨트롤러 기본동작
	}

}

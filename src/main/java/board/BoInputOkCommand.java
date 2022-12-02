package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoInputOkCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid"); // 컨트롤러에서 널값처리 했기때문에 여기선 안함
		String nickName = (String) session.getAttribute("sNickName"); // 인풋에서 sNickName으로 세션에서 받앗으니까여기서도
		
		String title = request.getParameter("title")== null ? "" : request.getParameter("title"); //이건 안받았으니까 널값처리함
		String email = request.getParameter("email")== null ? "" : request.getParameter("email"); //이건 안받았으니까 널값처리함
		String homePage = request.getParameter("homePage")== null ? "" : request.getParameter("homePage"); //이건 안받았으니까 널값처리함
		String content = request.getParameter("content")== null ? "" : request.getParameter("content"); //이건 안받았으니까 널값처리함
		String hostIp = request.getParameter("hostIp")== null ? "" : request.getParameter("hostIp"); //이건 안받았으니까 널값처리함
		
		BoardVO vo = new BoardVO();
		vo.setMid(mid);
		vo.setNickName(nickName);
		vo.setTitle(title);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setContent(content);
		vo.setHostIp(hostIp);
		// vo에 담는다. -> dao에 보낸다
		
		BoardDAO dao = new BoardDAO();
		int res = dao.setBoInputOk(vo); // dao에서 인트값 리절트로 받는다. (dao에 vo를 담아서 셋보드인풋오케이로 보냄?)
		
		// res가 1일때 정상처리
		if(res == 1) {
			request.setAttribute("msg", "boInputOk");
			request.setAttribute("url", request.getContextPath()+"/boList.bo");
		}
		else { // 정상처리 되지못했을때,글게시가 안됐을때, DB로 넘어가지않을떄?
			request.setAttribute("msg", "boInputNo");
			request.setAttribute("url", request.getContextPath()+"/boInput.bo");
		}
	}

}

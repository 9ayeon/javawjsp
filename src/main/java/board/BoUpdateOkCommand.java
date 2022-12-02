package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoUpdateOkCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int pag = request.getParameter("pag")==null ? 0 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 0 : Integer.parseInt(request.getParameter("pageSize"));
		String hostIp = request.getParameter("hostIp")== null ? "" : request.getParameter("hostIp"); //이건 안받았으니까 널값처리함
		
		//수정할목록, 위에는 넘어온것들
		String title = request.getParameter("title")== null ? "" : request.getParameter("title"); //이건 안받았으니까 널값처리함
		String email = request.getParameter("email")== null ? "" : request.getParameter("email"); //이건 안받았으니까 널값처리함
		String homePage = request.getParameter("homePage")== null ? "" : request.getParameter("homePage"); //이건 안받았으니까 널값처리함
		String content = request.getParameter("content")== null ? "" : request.getParameter("content"); //이건 안받았으니까 널값처리함
		
		// 수정할것들
		BoardVO vo = new BoardVO();
		vo.setIdx(idx); // res에 보내야해서 생성
		vo.setTitle(title);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setContent(content);
		vo.setHostIp(hostIp);
		
		BoardDAO dao = new BoardDAO();
		
		int res = dao.setBoUpdateOk(vo);
		
	// res가 1일때 정상처리
			if(res == 1) {
				request.setAttribute("msg", "boUpdateOk");
			}
			else { // 자료가 지워지지않았을때
				request.setAttribute("msg", "boUpdateNo");
			} //둘다 경로가 같으니까 아래로 빼서 url하나사용
			request.setAttribute("url", request.getContextPath()+"/boContent.bo?idx="+idx+"&pag="+pag+"&pageSize="+pageSize);
		
	}

}

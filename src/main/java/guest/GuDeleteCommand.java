package guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuDeleteCommand implements GuestInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx") =="" ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		GuestDAO dao = new GuestDAO();
		
		if(idx != 0) {
			int res = dao.setGuDelete(idx);
			if(res == 1) {
				request.setAttribute("msg", "guDeleteOk"); // 메세지와 url
			}
			else { // 자료가 입력되지 않았을 경우
				request.setAttribute("msg", "guDeleteNo"); // 메세지와 url
		}
			request.setAttribute("url", request.getContextPath()+"/guList.gu");
		}
	}

}

package study2.mapping2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.database.JusorokDAO;
import study.database.JusorokVO;

public class JuListCommand implements MappingInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JusorokDAO dao = new JusorokDAO(); // DB가져오기
		
		ArrayList<JusorokVO> vos = dao.getMemberList();
		
		request.setAttribute("vos", vos); // vos 담아만 놓으면 리턴하지않아도 보내짐(컨트롤러)?
		

	}

}

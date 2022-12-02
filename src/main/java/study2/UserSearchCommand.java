package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO내꺼;
import member.MemberVO;
import study2.ajax.UserDAO;
import study2.ajax.UserVO;

public class UserSearchCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//확장자패턴~_~_~_~_~_~_~_~_~
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		UserDAO dao = new UserDAO();
		
		UserVO vo = dao.getUserSearch(mid);
		
		// PrintWriter out = response.getWriter();
		
		int idx = 0;
		String name = "";
		int age = 0;
		String address = "";
		
		if(vo.getName() == null) { // 검색한 값이 vo의 겟네임에 들어있지않으면
			name = "찾는 자료가 없습니다.";
		}
		else {
			idx = vo.getIdx();
			mid = vo.getMid();
			name = vo.getName();
			age = vo.getAge();
			address = vo.getAddress();
		}
		
		String str = idx + "/" + mid + "/" + name + "/" + age + "/" + address;
		
		response.getWriter().write(str); // 비동기식으로 서버에 보내서 헤더에,,,,,,,str휴ㅓㅏ면에띄우는게없워.,,,,.......................................,,,,,,,,,,,,,,,,,,,,,,,,,,,,가져온건맞는데,,,,헤더에담고만있잔아..........아무동작도없이보여주지못하잔아...비동식이잔아..액션이없자나....아까는..액션을..데모에뿌렷자나......근데...여기는그런게업자나...안나와......근데데모에테이블을만들어서뿌릴순없잔아....엄청길자나..........근데...정말로그렇게해.,....근데우리는..그때문제고....ㅈ;ㅣ금은.....좋은방법은................지금상태에서부르는거야,,,자길다시한번불러..유저리스트 ㅅ크립트에서 로케이션리로드해..
		
	}

}

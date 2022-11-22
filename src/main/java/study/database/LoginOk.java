package study.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/database/LoginOk")
public class LoginOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
		
		// 데이터베이스와 연결, 연결객체 생성해서 mid와 pwd 보내야함
		// 아까 만들어둔 주소록 데이터베이스 소환~
		JusorokDAO dao = new JusorokDAO();
		
		// 데이터베이스 자료 검색(로그인 체크) / 중복처리는 셀프요
		// 로그인체크는 dao에서.(54번행) (id와 pw를 넘김) (넘겨 받아서 자료가있으면 로그인처리)
		// 넘겨받는값(이름(name))을 가져온다 (아래로수정)
		// String을 vo 객체로 가져올것. (vo 한덩어리를 전부 가져오는것) JusorokDAO 로그인체크처리부분참고
		JusorokVO vo = dao.loginCheck(mid, pwd);
		
		// 세션 사용하기 위한 선언
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		if(vo.getName() != null) {// vo에서 가져온 name이 null이 아니면 (로그인이 된거면 =정상처리라면)
			// 회원 인증 후 처리 (업무분석(1.자주 사용하는 자료를 세션에 담는다. 2.방문포인트를 10점씩 부가한다.(단, 1일 방문 포인트는 50점까지만 허용함)*숙제 3.최종방문날짜를 현재 날짜시간으로 변경한다. 
			// 저장소에 저장해야 저장해서 사용할 수 있음. (저장소는 세션을 사용. 리퀘스트는 1번만수행, 페이지컨텍스트는 현재페이지만, 애플리케이션은 계속서버끊길떄까지,)
			// 로그아웃시에는 세션과 연결을 끊어야한다. (DB와 연결할 필요X)(DAO는 DB연동이기때문에 거기서 할 필요X..복잡함,못함)
			// 최종방문일자를 방문할때마다 갱신해야함. sql 셀렉트 -> 업데이트
			// 1. 세션처리
			session.setAttribute("sMid", mid); //vo.getmid해도되지만, 위에 받아와서 안해도됨 // 저장소에 아이디 담기 ? 속성값정하기?
			session.setAttribute("sName", vo.getName()); // 저장소에 이름 담기 / 선택한 요소(element)의 속성(attribute) 값을 정합니다.
			session.setAttribute("sLastDate", vo.getLastDate().substring(0, 19)); // vo에서 가져온 데이터 값을 마지막숫자 2자리빼서 담기(편집해서 담기(날짜,시간-0번째부터 19번째의 앞자리까지)// 세션에 담음, 세션에 담겨있으니까 아무때나꺼내도됨. vo에서 가져온 최종방문일데이터
			
			// 2. 방문포인트 증가하기(10점씩, 1일 최대 50점), 3.최종방문일자 업데이트
			int visitPoint = vo.getPoint() + 10;
			//if문 사용해서 5번까지는 10점씩오르게하고 그 이후엔 0점오르게하기~~~~~~~~~!!!!!!!!!!!!!자정 지나면 또 50점...이게뭔데...
			
			// dao에 메소드생성
			dao.setVisitUpdate(visitPoint, mid); // mid도 같이 보내야 됨 일자업데이트는 가서~~
			
			
			
			// 로그인 인증처리끝나고 세션에 값 담은 후, 메세지 띄우지않고 서버에서 보내는법 - response.sendridirect, forword 방식. 
			// 메세지 없이 바로 보내기 - response.sendridirect 사용해야함
			// 메세지 띄우고 보내는 방법은 아래 else 구문이랑 같게 한다.
			out.println("<script>");
			out.println("alert('"+vo.getName()+"님 로그인 되었습니다.');");
			out.println("location.href='"+request.getContextPath()+"/database/MemberMain';"); // location-get방식(앞에따당하고 리퀘스트겟컨텍스트패스)/보안폴더에 들어가있지않기때문에 컨트롤러로 보내는게 아님. jsp 직접 호출해서 보낸다.=경로 직접입력함.(다음부터는 보안폴더거침,컨트롤러사용)  history.back은 직렬화전용 서블릿거쳤을때 함부로 사용ㄴㄴ (전단계로 돌아가는건데, 여기서는 서블릿을 거쳤기 때문에 무한루프걸림)
			out.println("</script>");   					
			// 회원전용방에서, 수정,조회 등 처리하고 나오는 길에 Db에서 계속 갱신해와야 하는데, 이걸 해주는 서블릿(db에서 가져와서, 응답받은거 el표기법으로 간단히 표시할수있음)을 만들어내야함.
			// member 부르는 컨트롤러(로케이션href로 서블릿으로 경로지정해도되지만, 다른방법으로 보내기)
			// 서블릿거치면 로그인알림메세지안떠서이거주석하고위에살림ㄴ response.sendRedirect(request.getContextPath()+"/database/MemberMain"); //서블릿클래스생성하고 그 클래스로 보내기 (mid,pwd실어서보내기)(취소 ㅋ)
			
		}
		else {
			// 회원 인증 실패시 처리
			out.println("<script>");
			out.println("alert('로그인 실패');");
			out.println("location.href='"+request.getContextPath()+"/study/1120_Database/login.jsp';"); // location-get방식(앞에따당하고 리퀘스트겟컨텍스트패스)/보안폴더에 들어가있지않기때문에 컨트롤러로 보내는게 아님. jsp 직접 호출해서 보낸다.=경로 직접입력함.(다음부터는 보안폴더거침,컨트롤러사용)  history.back은 직렬화전용 서블릿거쳤을때 함부로 사용ㄴㄴ (전단계로 돌아가는건데, 여기서는 서블릿을 거쳤기 때문에 무한루프걸림)
			out.println("</script>");
		}
	}
}

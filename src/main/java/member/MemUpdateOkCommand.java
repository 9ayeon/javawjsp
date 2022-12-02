package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemUpdateOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		String sNickName = (String) session.getAttribute("SNickName");
		
		String nickName = request.getParameter("nickName")==null ? "" : request.getParameter("nickName");
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		String gender = request.getParameter("gender")==null ? "" : request.getParameter("gender");
		String birthday = request.getParameter("birthday")==null ? "" : request.getParameter("birthday");
		String tel = request.getParameter("tel")==null ? "" : request.getParameter("tel");
		String address = request.getParameter("address")==null ? "" : request.getParameter("address");
		String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		String homePage = request.getParameter("homePage")==null ? "" : request.getParameter("homePage");
		String job = request.getParameter("job")==null ? "" : request.getParameter("job");
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		String userInfor = request.getParameter("userInfor")==null ? "" : request.getParameter("userInfor");
		
		// 취미 전송에 대한 처리
		String[] hobbys = request.getParameterValues("hobby");
		String hobby = ""; // 문자하나 누적
		if(hobbys.length != 0) { // 하비스가 길이가 0이 아닐때
			for(String strHobby : hobbys) {
				hobby += strHobby + "/"; //하비에 누적, /로 구분
			}
		}
		hobby = hobby.substring(0, hobby.lastIndexOf("/"));
		
		// 회원 사진이 업로드 되었는지의 여부 처리
		String fileSystemName = "";
		String photo = request.getParameter("photo");
		if(photo.equals("noimage")) {
			fileSystemName = "noimage.jpg";
		}
		else {
			fileSystemName = photo;
		}
		
		// DB에 저장시, 테이블설계에서 지정한 각 필드의 길이체크
		
		// 아이디와 닉네임을 다시한번 중복체크 해준다. (넘어온 값들을 체크하는거 - DB안에서)
		MemberDAO내꺼 dao = new MemberDAO내꺼();
		
		if(!nickName.equals(sNickName)) { // 넘어온닉네임과 세션닉네임이 같지 않을 때 중복체크.
			String tempNickName = dao.memNickCheck(nickName);
			if(!tempNickName.equals("")) { //공백이 아니면,  ---// == 참 1 . 1이 있는것 (닉네임 중복이면 1을 보냄.)
				request.setAttribute("msg", "nickCheckNo");
				request.setAttribute("url", request.getContextPath()+"/memJoin.mem");
				return; // 끝낸다.
			}
		}
		// 모든 체크가 완료되었다면 회원정보를 vo에 담아서 DB(DAO객체)로 넘겨준다.
		MemberVO vo = new MemberVO();
		vo.setMid(mid);
		vo.setNickName(nickName);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirthday(birthday);
		vo.setTel(tel);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setJob(job);
		vo.setHobby(hobby);
		vo.setPhoto(fileSystemName);
		vo.setContent(content);
		vo.setUserInfor(userInfor);
		
		int res = dao.setMemberUpdateOk(vo);
		
		// 정보가 변경되었으면 새로운 닉네임을 세션에 저장처리한다.
		session.setAttribute("sNickName", nickName);
		
		if(res == 1) {
			request.setAttribute("msg", "memUpdateOk");
			request.setAttribute("url", request.getContextPath()+"/memMain.mem");
		}
		else {
			request.setAttribute("msg", "memUpdateNo");
			request.setAttribute("url", request.getContextPath()+"/memUpdate.mem");
		}
	}

}

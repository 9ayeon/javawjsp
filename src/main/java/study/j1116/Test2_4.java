package study.j1116;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1116/Test2/4") //서버에서 저장소모델사용, 저장소객체가 헤더에 싣고 ok4로 넘어감
public class Test2_4 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		String hakbun = request.getParameter("hakbun") == null ? "" : request.getParameter("hakbun");
		int kor = request.getParameter("kor") == null ? 0 : Integer.parseInt(request.getParameter("kor"));
		int eng = request.getParameter("eng") == null ? 0 : Integer.parseInt(request.getParameter("eng"));
		int mat = request.getParameter("mat") == null ? 0 : Integer.parseInt(request.getParameter("mat"));
		int soc = request.getParameter("soc") == null ? 0 : Integer.parseInt(request.getParameter("soc"));
		int sci = request.getParameter("sci") == null ? 0 : Integer.parseInt(request.getParameter("sci"));
		
		
		int tot = kor + eng + mat + soc + sci;
		double avg = tot / 5.0;
		int intAvg = (int)(avg / 10.0);
		String grade;
		
		switch (intAvg) {
			case 10:
			case 9:
				grade = "A";
				break;
			case 8:
				grade = "B";
				break;
			case 7:
				grade = "C";
				break;
			case 6:
				grade = "D";
				break;

			default:
				grade = "F";
		}
		
		// VO에 계산한 값 저장하기
		//vo에 만든 생성자에 들어간 변수랑 순서가 같아야함.
		Test2VO vo = new Test2VO(name,hakbun,kor,eng,mat,soc,sci,tot,avg,grade); 
		System.out.println("vo : " + vo);
	
		
		// 위에 처리를 get방식으로 값을 넘기기
		// 샌드리다이렉트 - 포워드와 다르게 서버에서 값을 처리한뒤 넘긴다
		// 서버에서 get방식으로의 전송방법
		//response.sendRedirect(request.getContextPath()+"/study/1116/test2Ok3.jsp?hakbun="+hakbun+"&name="+name+"&kor="+kor+"&eng="+eng+"&mat="+mat+"&soc="+soc+"&sci="+sci+"&tot="+tot+"&avg="+avg+"&grade="+grade);
		//		name = URLEncoder.encode(name);
		//		vo.setName(name);
		//		Object strVo = (Object)vo;
		//		strVo = ((String) strVo).replace("[", "");
		//		strVo = ((String) strVo).replace("]", "");
		//		response.sendRedirect(request.getContextPath()+"/study/1116/test2Ok3.jsp?vo="+vo); //vo라는 객체에 vo변수담음 (get방식:url타고넘어가는거.. 타입string..)
		
		// 저장소(request)를 활용한 forwarding (직진해서간다) /저장소에 vo담기. (저장-set,가져올때-get)
		request.setAttribute("vo", vo); // (변수, 변수) - 뒤에 나오는 변수는 위에서 값을 저장한 처리가 끝난 vo와같은 객체 변수. 앞에 적어주는 변수는 뒤의 객체(vo)를 담아갈 변수
		// 저장소에 담은 것을 forwarding하기 (저장소에 담은것을 요청해서 싣고 가야함)
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1116/test2Ok4_2.jsp"); //포워딩하는 곳의 주소를 적어준다.(저장된 값을 받아줄 주소?)저장소에 담고, 갈 주소의 헤더에 싣고 디스패쳐로 작업? (변수에 담아서사용)
		//vo객체를 넘기기위해 request저장소에 담는다. 저장된 객체를 위주소로 옮기기위해 헤더에요청해서 ....몰라
		//dispatcher.forward(request, response); // 디스패쳐.포워드 이 두개의 동작이 위에 저장한것을 헤더에 싣는것(클라이언트에 보내기위해). 저장소에 담은 리퀘스트를 가지고 리스폰스.응답한다.  -Web으로 보내기 위한 WAS작업. 
		
		// 넘길 주소 변수에 담아서 사용 
		String viewPage = "/study/1116/test2Ok4_2.jsp";
		// 위에 주석처리한 두줄 한번에 처리.
		request.getRequestDispatcher(viewPage).forward(request, response);
		
	}
}

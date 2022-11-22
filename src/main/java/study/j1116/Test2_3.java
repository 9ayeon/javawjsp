package study.j1116;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1116/Test2/3")
public class Test2_3 extends HttpServlet{
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
		
		// VO에 값 저장하기
		Test2VO vo = new Test2VO(name,hakbun,kor,eng,mat,soc,sci,tot,avg,grade); 
		//vo에 만든 생성자에 들어간 변수랑 순서가 같아야함.
		System.out.println("vo : " + vo);
		
		// setter를 이용한 값의 저장(vo객체생성후)
//		vo.setName(name);
//		vo.setHakbun(hakbun);
//		vo.setKor(kor);
//		vo.setEng(eng);
//		vo.setMat(mat);
		
		// 위에 처리를 get방식으로 값을 넘기기
		// 샌드리다이렉트 - 포워드와 다르게 서버에서 값을 처리한뒤 넘긴다
		// 서버에서 get방식으로의 전송방법
		//response.sendRedirect(request.getContextPath()+"/study/1116/test2Ok3.jsp?hakbun="+hakbun+"&name="+name+"&kor="+kor+"&eng="+eng+"&mat="+mat+"&soc="+soc+"&sci="+sci+"&tot="+tot+"&avg="+avg+"&grade="+grade);
//		name = URLEncoder.encode(name);
//		vo.setName(name);
//		Object strVo = (Object)vo;
//		strVo = ((String) strVo).replace("[", "");
//		strVo = ((String) strVo).replace("]", "");
		response.sendRedirect(request.getContextPath()+"/study/1116/test2Ok3.jsp?vo="+vo); //vo라는 객체에 vo변수담음 (get방식:url타고넘어가는거.. 타입string..)
		
	}
}

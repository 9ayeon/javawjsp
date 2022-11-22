package study2.mapping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
/* @WebServlet("/mapping/Test3") */
@WebServlet("*.do")
public class Test3Controller extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer url = request.getRequestURL();
		System.out.println("url : " + url);
		
		String uri = request.getRequestURI(); 
		System.out.println("uri : " + uri); // 도메인 뒷부분이 뜬다. (컨텍스트 부분) 
		// 도메인을 생략한 uri로 작업한다.
		
		// uri 거르기? (공통적인 부분빼고, 
		//1확장자부분만 발췌,(서브스트링) 찾아오기(인덱스오브)
		//2뒤에서부터찾기(라스트인덱스오브, /뒤에 다 가져와라) 
		//가져와서 보내기)
		//String com = uri.substring(uri.lastIndexOf("/")); // uri를 /이 들어가는 부분 뒷부분을 모두 발췌.
		// 확장자 빼고 가져오기. (.do 빼기)
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		System.out.println("com : " + com);
		
//		PrintWriter out = response.getWriter();
		
		String viewPage = "/WEB-INF/study2/mapping";
		
		if(com.equals("/test3_1")) {
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/study2/mapping/test3_1.jsp");
			viewPage += "/test3_1.jsp";
		}
		else if(com.equals("/2")) {
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/study2/mapping/test3_2.jsp");
			viewPage += "/test3_2.jsp";
		}
		else if(com.equals("/test3_3")) {
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/study2/mapping/test3_3.jsp");
			viewPage += "/test3_3.jsp";
		}
		else if(com.equals("/Test4") || com.equals("/test4")) {
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/study2/mapping/test3_3.jsp");
			viewPage += "/test4.jsp";
		}
		else if(com.equals("/Test4Ok")) {
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/study2/mapping/test3_3.jsp");
			int su1 = request.getParameter("su1") == null ? 0 : Integer.parseInt(request.getParameter("su1"));
			int su2 = request.getParameter("su2") == null ? 0 : Integer.parseInt(request.getParameter("su2"));
			String opt = request.getParameter("opt") == null ? "" : request.getParameter("opt");
			
			Test4Calc t4 = new Test4Calc();
			int res = t4.getCalc(su1, su2, opt);
			
			request.setAttribute("su1", su1);
			request.setAttribute("su2", su2);
			request.setAttribute("opt", opt);
			request.setAttribute("res", res); // 나중에 이건 vo로 만들어서 vo한덩어리만 부르면됨
			
			
			viewPage += "/test4Ok.jsp"; // res를 담아서 test4Ok.jsp로 보냄
		}
		else {
			//out.println("<script>");
			//out.println("alert('잘못된 경로입니다.')");
			//out.println("location.href='"+request.getContextPath()+"/mapping/Test3';");
			// url을 타고가는 get방식은 사용할 수 없다.(컨트롤러 사용해야함) out.println("location.href='/WEB-INF/study2/mapping/test3.jsp'");
			//out.println("</script>");
			//System.out.println("주소를 잘못 입력하셨습니다.");
			viewPage += "/test3.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		/*
		 * RequestDispatcher dispatcher =
		 * request.getRequestDispatcher("/WEB-INF/study2/mapping/test3.jsp");
		 * dispatcher.forward(request, response);
		 */
	}
}

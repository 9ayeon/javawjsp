package study.j1114;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1114_HwOk")
public class HwOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String guest = request.getParameter("guest");
		
		String[] products = request.getParameterValues("product"); //배열로 받는다. 같은 이름 여러개니까
		
		String strProduct = ""; //누적할변수
		
		for(int i=0; i<products.length; i++) {
			if(products[i].trim() != "") { 
				strProduct += products[i].trim() + "/";
			}
		}
		String[] results = request.getParameterValues("result"); //배열로 받는다. 같은 이름 여러개니까
		
		String strResult = ""; //누적할변수
		
		for(int i=0; i<results.length; i++) {
			if(results[i].trim() != "") { 
				strResult += results[i].trim() + "/";
			}
		}
		String[] sus = request.getParameterValues("su"); //배열로 받는다. 같은 이름 여러개니까
		
		String strSu = ""; //누적할변수
		
		for(int i=0; i<sus.length; i++) {
			if(sus[i].trim() != "") { 
				strSu += sus[i].trim() + "/";
			}
		}
		
		PrintWriter out = response.getWriter();
		
		if(!strProduct.equals("")) { 
			strProduct = strProduct.substring(0, strProduct.length()-1);
		}
		else {
			out.println("<script>");
			out.println("alert('1개 이상의 상품은 등록하셔야 합니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		out.println("<p>"+guest+"님이 등록하신 상품은?</p>");
		out.println("<p>"+strProduct+"</p>");
		out.println("<script>");
		out.println("alert('상품 등록 완료!');");
		out.println("</script>");
		out.println("<p><a href='"+request.getContextPath()+"/study/1114/hw.jsp'>돌아가기</a></p>");
	}
}

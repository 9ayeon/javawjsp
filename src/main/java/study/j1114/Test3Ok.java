package study.j1114;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1114_Test3Ok")
public class Test3Ok extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String[] products = request.getParameterValues("product"); //배열로 받는다. 같은 이름 여러개니까
		
		String strProduct = ""; //누적할변수
		
		/*for(String product : products) { //향상된포문, 
			strProduct += product.trim() + "/"; //strProduct에 공백제거한 값 누적, 배열마다 /추가(/기준으로누적)
		}*/
		//일반포문
		for(int i=0; i<products.length; i++) {
			if(products[i].trim() != "") { //비어있찌않으면
				strProduct += products[i].trim() + "/";
			}
		}
		
		PrintWriter out = response.getWriter();
		
		if(!strProduct.equals("")) { //공백이 아닐시에만.
			strProduct = strProduct.substring(0, strProduct.length()-1); //마지막슬래쉬빼줌
		}
		else {
			out.println("<script>");
			out.println("alert('1개 이상의 상품은 등록하셔야 합니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
		out.println("<p>등록하신 상품은?</p>");
		out.println("<p>"+strProduct+"</p>");
		out.println("<script>");
		out.println("alert('상품 등록 완료!');");
		out.println("</script>");
		out.println("<p><a href='"+request.getContextPath()+"/study/1114/test3.jsp'>돌아가기</a></p>");
	}
}

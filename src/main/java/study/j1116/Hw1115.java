package study.j1116;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/j1116h/Hw1115")
public class Hw1115 extends HttpServlet{
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    
    String guest = request.getParameter("guest");
    String classI = request.getParameter("classification");
    String[] products = request.getParameterValues("product");
    String[] prices = request.getParameterValues("price");
    String[] strSu = request.getParameterValues("su");
    String strProduct = "";
    String strProducts = "";
    String strPrice = "";
    
    int cnt = products.length;
    int pTot = 0;
    for(int i=0; i<cnt; i++) {
        int price = Integer.parseInt(prices[i]);
        int su = Integer.parseInt(strSu[i]);
        int tot = price * su;
        pTot += tot;
        }
    
    for(String product : products) {
    	strProducts += product + "/";
    }
    for(String price : prices) {
    	strPrice += price ;
    }
//    for(int i=0; i<products.length; i++) {
//      if(products[i].trim() != "") {
//      	strProducts += products[i].trim();
//      }
//    }
    
//    for(int i=0; i<products.length; i++) {
//        if(products[i].trim() != "") {
//            strProduct += products[i].trim() + " " +
//            su[i] + "잔" + " 각 " + price[i] + "원  총 " +
//            price[i] * su[i] + "원<br/>";
//        }
//    }

    
    PrintWriter out = response.getWriter();
    
    if(!strProduct.equals("")) { 
        strProduct = strProduct.substring(0, strProduct.length()-1);
    }
    else {
        out.println("<script>");
        out.println("alert(‎'1개 이상의 상품은 등록하셔야 합니다.');");
        out.println("history.back()");
        out.println("</script>");
    }
		
	  out.println("<p>"+guest+"님이 등록하신 메뉴는?</p>");
	  out.println("<p>"+classI+"메뉴의</p>"); out.println("<p>"+strProduct+"</p>");
	  out.println("<p>총 메뉴합계 : "+pTot+"원</p>"); out.println("<script>");
	  out.println("alert(‎'상품 등록 완료!');"); out.println("</script>");
	  out.println("<p><a href='"+request.getContextPath()+
	  "/study/1116h/home.jsp'>돌아가기</a></p>");
		 
	  
		
		/*
		 * Hw1115VO vo = new Hw1115VO(guest, classI, products, strPrice, strSu,
		 * strProduct, strProducts); System.out.println("vo : " + vo);
		 */
		  
		/*
		 * request.setAttribute("vo", vo);
		 * 
		 * String viewPage = "/study/1116h/list.jsp";
		 * request.getRequestDispatcher(viewPage).forward(request, response);
		 */

  }
}

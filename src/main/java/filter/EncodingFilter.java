package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*") //들어오는 모든 경로
public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		//response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 수행할 내용 작성
		
		// 전부 통과시키기
		//System.out.println("이곳은 Filter통과 전");//거르기 전에 실행할곳
		chain.doFilter(request, response); //이상있으면 뒤로보내고, 없으면 통과
		//System.out.println("이곳은 Filter통과 후");//거ㅏ르고나서 실행할곳
		
	}
	
}

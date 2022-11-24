package guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GuestInterface {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException; 
	// execute에 정의해놓은 것들을 컨트롤러(서블릿)에서 상속받아 사용
	// 전송해주는 리턴타입이 아니기 때문에 void
}

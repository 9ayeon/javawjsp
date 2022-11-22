package study2.mapping2;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MappingInterface { // 구현객체/ 연결해주는 객체니까 중괄호없음?
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}

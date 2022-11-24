package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PassOkCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
	
		System.out.println("원본 자료 : ");
		System.out.println("mid : " + mid);
		System.out.println("pwd : " + pwd);
		System.out.println("idx : " + idx);
		System.out.println();
		
		
		if(idx == 1) { // 숫자를 넘겼음. 
			// 암호화를 위한 키(0x1234ABCD)(16진수)
			int key = 0x1234ABCD;
			int encPwd, decPwd; // 인코딩비번, 디코딩비번.
			encPwd = Integer.parseInt(pwd) ^ key; // 비트연산자 |, & , (어쩌구저쩌구해쉬?멀라 연산자 ^)
			System.out.println("인코딩된 비밀번호 : " + encPwd);
			System.out.println("앞에서 인코딩된 pwd를 DB에 저장처리한다.");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("DB에 저장된 pwd를 다시 불러와서 디코딩(복호화ㅏ) 처리한다.");
			decPwd = encPwd ^ key;
			System.out.println("디코딩된 비밀번호 : " + decPwd);
		}
		else {
			// 입력된 문자형식의 비밀번호를 1문자씩 숫자로 변환처리한다.
			long intPwd;
			String strPwd = "";
			for(int i=0; i<pwd.length(); i++) {
				intPwd = (long) pwd.charAt(i); // 받아온 숫자들을 하나하나 롱 형식의 정수로 담는다 문자형식의
				strPwd += intPwd; //입력한 숫자를 누적받아 아스키코드로 받는다
			}
			System.out.println("strPwd(아스키코드 문자로 변환된 비밀번호 : )" + strPwd);
			
			intPwd = Long.parseLong(strPwd);
			
			
			long key = 0x1234ABCD;
			long encPwd;
			
			encPwd = intPwd ^ key;
			strPwd = String.valueOf(encPwd);
			System.out.println("인코딩된 비밀번호(DB에 저장할 비밀번호) :" + strPwd) ;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
			// DB에 저장된 암호를 다시 복호화(디코딩) 시켜준다.
			long decPwd;
			intPwd = Long.parseLong(strPwd); // DB에 넣었던 strPwd(암호화된갑ㅆ)을 다시 불러와서 복호화 준비한다.
			decPwd = intPwd ^ key;
			System.out.println("디코딩된 비밀번호 : " + decPwd);
			
			// 복원된 비밀번호는 숫자이기에 문자로 변환후 2개씩 처리시켜야 한다.
			strPwd = String.valueOf(decPwd);
			
			String result = "";
			char ch;
			
			for(int i=0; i<strPwd.length(); i+=2) {
				ch = (char) Integer.parseInt(strPwd.substring(i, i+2));
				result += ch;
			}
			System.out.println();
			System.out.println("최종 변환된 비밀번호 : " + result);
		
		}
		System.out.println();
	}

}

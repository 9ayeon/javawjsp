package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetConn {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/javaworks";
	private String user = "root";
	private String password = "1234";
	
	private static GetConn instance = new GetConn();
	// 게터세터생성사용, 여는게 목적이니까 게터만 생성.
	
	// 객체 생성시에 DB연결
	// 외부에서 사용못하게 만듦 싱글톤
	private GetConn() { // DB에선 예외처리
		try {
			Class.forName(driver); // 드라이버 제작사 이름
			conn = DriverManager.getConnection(url, user, password);// connection
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패"); // 이 에러가 나면 라이브러리폴더에 mysql connector가 있는지 확인
		} catch (SQLException e) {
			System.out.println("Database 연동 실패"); // 이 에러가 나면 url, user, password 확인
		}
	}
	
	// 외부에서 사용하기 위한 메소드 (연결용)
	public Connection getConn() {
		return conn;
	}
	
	// 객체 소멸처리
	public void pstmtClose() {
		if(pstmt != null) { // pstmt가 비어있지않을경우에만	
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
	}
	
	public void rsClose() {
		if(rs != null) {// rs가 null이 아닐경우, 사용중이 아닐때
			try {
				rs.close();
				pstmtClose();
			} catch (SQLException e) {}
		}
	}

	public static GetConn getInstance() {
		return instance;
	}// 싱글톤으로 객체를 한번 열면서 생성 필요한것을 겟콘객체로 불러 사용 (인스턴스.클ㄹ래스명? 겟콘객체불러쓰기)
	
}

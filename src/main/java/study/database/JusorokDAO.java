package study.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class JusorokDAO {// 웹에서 일반클래스 쓸수없으므로 http상속(서블릿도해야함), response, request사용가능해짐.. 이런작업을 해서 세션을 날리거나 해야함, 굳이 여기서 세션객체 닫을필요X
	private Connection conn = null; //java.sql걸로 가져와야한다(mysqlㄴㄴ)
	private PreparedStatement pstmt = null; //java.sql걸로 가져와야한다(mysqlㄴㄴ)
	private ResultSet rs = null; //java.sql걸로 가져와야한다(mysqlㄴㄴ)
	
	private String sql = "";
	
	JusorokVO vo = null; // 생성할 필요없음(null), 선언만 해놓고(null로) 사용할때 불러옴?
	
	// jusorokDAO 만 부르면 DB와 연동되게 해야함
	// 기본생성자에 데이터베이스 연동하기 , (외부객체 불러오는거니까 예외처리해야함(try,catch))
	public JusorokDAO() {
		String url = "jdbc:mysql://localhost:3306/javaworks"; //바깥에 선언시 private필요함. 생성자 내부에서 그냥 타입만
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);// 프라이빗 선언한 conn변수에 담아서 드라이버와 커넥션객체 연결. 드라이버연동후 커넥션객체와 연결/변수선언하고오기 // 두번째 트라이캐치 예외처리해주기ㅏ
		} catch (ClassNotFoundException e) { // 드라이버찾는거니까 클래스낫파운드로(클래스가 없을경우)
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			System.out.println("Database 연동 실패");
		}
	}
	
	// 사용한 객체 반납
	public void pstmtClose() {
		if(pstmt != null) { //pstmt가 열려있다면 닫아라
			try {
				pstmt.close(); // 예외처리
			} catch (SQLException e) {}
		}
	}
	
	public void rsClose() {
		if(rs != null) { // rs가 비어있지않으면 닫아라
			try {
				rs.close(); // 예외처리 ,
				pstmtClose(); // pstmt가 rs를 사용했기때문에 rs를 닫아주면서 pstmt같이닫는다
			} catch (SQLException e) {}
		}
	}
	
	// 로그인 체크처리. LoginOk.java 26번행 //LoginOk.java 30번 행참고 / public String에서 vo변경
	public JusorokVO loginCheck(String mid, String pwd) {
		vo = new JusorokVO(); // 주소록vo 맨위에서 선언했으니까 값만담아줌 //네임에 공백? ./ 예외처리하기
		try {
			sql = "select * from jusorok where mid=? and pwd=?"; // name자리에 *은전체탐색?
			pstmt = conn.prepareStatement(sql);//pstmt생성해서 mid=? - ?부분에 뭐시기
			pstmt.setString(1, mid); // 첫번째 받을 문자 
			pstmt.setString(2, pwd); // 두번째 받는 문자
			rs = pstmt.executeQuery();// pstmt 실행해서 결과를 rs에 담아서 가져온다.
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name")); // vo name에 담음
				vo.setPoint(rs.getInt("point")); 
				vo.setLastDate(rs.getString("lastDate"));
			}
		} catch (SQLException e) { // sql구문으로 변경
			System.out.println("SQL 오류 : " + e.getMessage()); // sql구문 에러만 뜨게 한다.
		} finally {
			rsClose(); // 더이상 사용하지 않을, 닫을 객체, rs를 닫으면 sql자동닫힘
		}
		
		return vo; // 이름을 넘긴다 //수정, vo로넘김.
	}

	// 로그아웃 처리. LogOut.java
	public void logout() {
		// 최종방문날짜 업데이트 후 로그아웃
		// 종료 전에 DB에 처리해야 할 내용들을 기록하기
		
		
		
	}
	// 회원 정보 조회
	public JusorokVO getMemberSearch(String mid) {
		vo = new JusorokVO(); // 주소록vo 맨위에서 선언했으니까 값만담아줌 //네임에 공백? ./ 예외처리하기
		try {
			sql = "select * from jusorok where mid=?"; // name자리에 *은전체탐색?
			pstmt = conn.prepareStatement(sql);//pstmt생성해서 mid=? - ?부분에 뭐시기
			pstmt.setString(1, mid); // 첫번째 받을 문자 
			rs = pstmt.executeQuery();// pstmt 실행해서 결과를 rs에 담아서 가져온다.
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name")); // sql에서 명령으로 받아온 pstmt에 rs로 받아온 값들을 vo name에 담음
				vo.setPoint(rs.getInt("point")); 
				vo.setLastDate(rs.getString("lastDate"));
			}
		} catch (SQLException e) { // sql구문으로 변경
			System.out.println("SQL 오류 : " + e.getMessage()); // sql구문 에러만 뜨게 한다.
		} finally {
			rsClose(); // 더이상 사용하지 않을, 닫을 객체, rs를 닫으면 sql자동닫힘
		}
		
		return vo; // 이름을 넘긴다 //수정, vo로넘김.
	}
	
	// 방문포인터 증가와 최종방문일자 업데이트작업
	public void setVisitUpdate(int visitPoint, String mid) {
		// 업데이트하러 안보내도된대
		// 예외처리
		try {
			sql = "update jusorok set point=?, lastdate=now() where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, visitPoint);
			pstmt.setString(2, mid);
			pstmt.executeUpdate(); // 값 담아서 넘긴 후 가져올게 없으므로 rs말고 바로실행
			
		} catch (SQLException e) { // sql구문으로 변경
			System.out.println("SQL 오류 : " + e.getMessage()); // sql구문 에러만 뜨게 한다.
		} finally {
			pstmtClose(); // 더이상 사용하지 않을, 닫을 객체, rs를 닫으면 sql자동닫힘
		}
		
	}

	public int setJoinOk(JusorokVO vo) { // JoinOk.java의 35행
		int res = 0;
		// 1 = 정상
		try {
			sql = "insert into jusorok (mid, pwd, name) values (?, ?, ?)"; // vo에 담아온값. 물음표
			pstmt = conn.prepareStatement(sql); // sql 명령을 pstmt에 담는다
			pstmt.setString(1, vo.getMid()); // pstmt에 vo에서 받아온 이름(사용자가적은값)을 담는다
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.executeUpdate(); // pstmt를 업데이트한다.
			res = 1; // 이 과정 정상으로 완수하면 1이 리턴된다. (150행 return res)
		} catch (SQLException e) { // sql구문으로 변경
			System.out.println("SQL 오류 : " + e.getMessage()); // sql구문 에러만 뜨게 한다.
		} finally {
			pstmtClose(); // 더이상 사용하지 않을, 닫을 객체, rs를 닫으면 sql자동닫힘
		}
		return res;
	}
	
	// 전체 회원 조회하기 MemberList.java  21행
	public ArrayList<JusorokVO> getMemberList() {
		ArrayList<JusorokVO> vos = new ArrayList<>();
		try {
			sql = "select * from jusorok order by idx desc"; // 주소록 idx 내림차순으로
			pstmt = conn.prepareStatement(sql); // pstmt에 sql명령한 것을 담는다.
			rs = pstmt.executeQuery(); // sql명령 담은 pstmt를 executeQuery로 꺼내서 rs에 담는다
			
			while(rs.next()) {
				vo = new JusorokVO(); // vo생성
				vo.setIdx(rs.getInt("idx")); // 생성한 vo에 rs에 담아온 값들을 담는다.
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				
				vos.add(vo); //vos에 위에서 담아온 vo를 담는다
			}
			
		} catch (SQLException e) { // sql구문으로 변경
			System.out.println("SQL 오류 : " + e.getMessage()); // sql구문 에러만 뜨게 한다.
		} finally {
			rsClose(); // 더이상 사용하지 않을, 닫을 객체, rs를 닫으면 sql자동닫힘
		}
		return vos; // vos로 돌려보낸다. 작성해놓은것들~
	}
}

package guest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	GuestVO vo = null; // vo껍데기 생성
	
	private String url = "jdbc:mysql://localhost:3306/javaworks";
	private String user = "root";
	private String password = "1234";
	
	// 객체 생성시에 DB연결
	public GuestDAO() { // DB에선 예외처리
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 드라이버 제작사 이름
			conn = DriverManager.getConnection(url, user, password);// connection
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패"); // 이 에러가 나면 라이브러리폴더에 mysql connector가 있는지 확인
		} catch (SQLException e) {
			System.out.println("Database 연동 실패"); // 이 에러가 나면 url, user, password 확인
		}
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
	// 컨트롤러 만들러간다

	// 게시글 전체 리스트 읽어오기 
	public ArrayList<GuestVO> getGuestList(int startIndexNo, int pageSize) { // arraylist로 vo로 넘겨야함
		ArrayList<GuestVO> vos = new ArrayList<>();
		try {
			sql = "select * from guest order by idx desc limit ?,?"; //limit 추가해서 한계치정해줌 ?,? =시작인덱스번호, 가져올 건수// idx 내림차순(마지막에 쓴글 번호가 맨 위로)
			pstmt = conn.prepareStatement(sql); // sql 문에 물음표가 없으니까 여기까지
			pstmt.setInt(1, startIndexNo); // 1번째는 시작인덱스번호고
			pstmt.setInt(2, pageSize); //2번째는 가져올 건수이다. 
			rs = pstmt.executeQuery(); // rs에 내용 담기
			
			while(rs.next()) {
				vo = new GuestVO();//한건한건을 모두 vo에 담아야하므로
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setVisitDate(rs.getString("visitDate"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setContent(rs.getString("content")); // rs로 꺼내온 값들을 vo에 담아준다
				
				vos.add(vo); // vo에 담은 값들을 vos에 담아준다.
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 방명록 글올리기 처리
	public int setGuInput(GuestVO vo) {
		int res = 0; // 정상적이지 않은걸 변수에 줘야함. 예외처리를 할때 문제가 생기면 sql오류가 생긴다. 오류가 뜨면 예외처리 try로 넘어가지않기때문에 0에서 넘어가지않아야하므로 0값을준다)
		try {
			sql = "insert into guest values (default,?,?,?,default,?,?)"; // 입력받는 란에는 ?
			pstmt = conn.prepareStatement(sql);// 커넥션객체 연결. sql넣어서 생성
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getHomePage());
			pstmt.setString(4, vo.getHostIp());
			pstmt.setString(5, vo.getContent()); // sql문 들어간 커넥션객체 pstmt에 vo에서 담아온 값들을 넣어준다.
			pstmt.executeUpdate(); // pstmt를 업데이트올려준다
			res = 1; //정상처리. 1.
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		} 
		return res;// res가 정상적이면 1을 보내고 정상적이지않으면 0을 보내게 만든다.
	}

	// 방명록의 글 삭제 처리하기
	public int setGuDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from guest where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		} 
		return res;
	}

	// 방명록의 총 레코드 건수 구하기
	public int totRecCnt() {
		int totRecCnt = 0;
		try { //sql문은 예외처리해야한다
			sql = "select count(*) as cnt from guest"; //count(*)에 as로 cnt라는 별명을 지정.// count(*)자리가 필드1자리. 뒤에 ,2,3,4 있으면 2번3번4번이된다
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();// 물음표가 없으니 rs로 바로 넘어감
			
			rs.next();
			// 카운트 읽어온 것 담기
			// totRecCnt = rs.getInt(1);  // count(*)자리 필드 1번
			totRecCnt = rs.getInt("cnt");  // count(*)자리 필드 1번이 맞는데, vo로 가면 구분이 힘들다. sql문에서 변수를 준다. cnt라는 변수는 vo에도 준다
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			rsClose();
		} 
		return totRecCnt;
	}
}

package study2.ajax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;
import member.MemberVO;

public class UserDAO {
	// DB에있는 자료를 작업을 위해 가져오는 역할
	// 연결객체
	// 싱글톤을 이용한 DB연결 객체 연결하기
	// 타입 이름 = 클래스명.인스턴스getInstance();
	GetConn getConn = GetConn.getInstance();
	// 위에 연결은 데이터베이스 열기위한 선언
	
	private Connection conn = getConn.getConn();//프라이빗이니까, 클래스로 간다 //getconn을 여기에 할당하면 conn을 사용하면서 getconn사용
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	UserVO vo = null; // 임포트 걸어서 memberVO사용
	
	
	// user의 정보를 모두 가져오기 
	public ArrayList<UserVO> getUserList() {
		ArrayList<UserVO> vos = new ArrayList<>();
		try {
			sql = "select * from user order by idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new UserVO();
				
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setAddress(rs.getString("address"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	// user 개별 조회 검색
	public UserVO getUserSearch(String mid) {
		try {
			sql = "select * from user where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO(); // 자료가 있으면 생성
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vo;
	}
	
	// user 삭제
	public String setUserDel(String mid) {
		String res = "0"; //문자열로 변경해준다
		try {
			sql = "delete from user where mid = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
			res = "1"; //정상처리시 res가 1, 정상처리되지않을시 리턴으로 넘어가서 res는0이된다
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return res;
	}
	

	
}

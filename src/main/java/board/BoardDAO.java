package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;

public class BoardDAO {
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
	
	BoardVO vo = null;

	// 전체()레코드 건수 구하기
	public int totRecCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as cnt from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			totRecCnt = rs.getInt("cnt");//필드명이라 따옴표준다.
		} catch (SQLException e) {
			System.out.println("SQL 에러3 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}

	// 전체 게시글 가져오기
	public ArrayList<BoardVO> getBoList(int startIndexNo, int pageSize) {
		ArrayList<BoardVO> vos = new ArrayList<>();
		try {					//day_diff 새로생긴필드 vo에 담기
			// sql = "select *, datediff(now(), wDate) as day_diff from board order by idx desc limit ?,?";
			sql = "select *, datediff(now(), wDate) as day_diff,"
					+ " timestampdiff(hour, wDate, now()) as hour_diff"
					+ " from board order by idx desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery(); // 읽어오는거니까그냥여렇게
			
			while(rs.next()) { //자료ㅕ가있어서들어왔으니까vo생성해서담눈다...?
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setwDate(rs.getString("wDate"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setGood(rs.getInt("good"));
				vo.setMid(rs.getNString("mid"));
				
				vo.setDay_diff(rs.getInt("day_diff"));
				vo.setHour_diff(rs.getInt("hour_diff"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러123 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		
		return vos;
	}

	// 게시글 내용 DB에 저장하기
	public int setBoInputOk(BoardVO vo) {
		int res = 0;
		try {
			sql = "insert into board values (default,?,?,?,?,?,default,?,default,default,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNickName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getHomePage());
			pstmt.setString(5, vo.getContent());
			pstmt.setString(6, vo.getHostIp());
			pstmt.setString(7, vo.getMid()); // 디폴트빼고 물음표를 vo에서 넘김(입력받을애들)
			pstmt.executeUpdate(); // 저장한걸 DB에 업데이트시킨다.
			res = 1; //정상처리  = 1
		} catch (SQLException e) {
			System.out.println("SQL 에러2 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	// 개별 자료 검색
	public BoardVO getBoContentSearch(int idx) {
		try {
			sql = "select * from board where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);//물음표
			rs = pstmt.executeQuery();// 글을 눌러서 보니까 자료가 당연히 있음.
			
			rs.next();
			
			vo = new BoardVO();
			vo.setIdx(rs.getInt("idx"));
			vo.setNickName(rs.getString("nickName"));
			vo.setTitle(rs.getString("title"));
			vo.setEmail(rs.getString("email"));
			vo.setHomePage(rs.getString("homePage"));
			vo.setContent(rs.getString("content"));
			vo.setwDate(rs.getString("wDate"));
			vo.setHostIp(rs.getString("hostIp"));
			vo.setReadNum(rs.getInt("readNum"));
			vo.setGood(rs.getInt("good"));
			vo.setMid(rs.getNString("mid"));
			
		} catch (SQLException e) {
			System.out.println("SQL 에러4 : " + e.getMessage());
		} finally {
			getConn.rsClose(); // 가져가는거니까 내용이 있어야함. rs
		}
		return vo;
	}

	// 글 조회수 증가하기
	public void setReadNumPlus(int idx) {
		try {
			sql = "update board set readNum = readNum + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러5 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); 
		}
		
	}
	
	// 좋아요 횟수 증가처리
	public void setBoGood(int idx) {
		try {
			sql = "update board set good = good + 1 where idx = ?	";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러6 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); 
		}

	}
	
	//따봉을 이용한 좋아요 횟수 증가, 감소
	public void setGoodPlusMinus(int idx, int goodCnt) {
		try {
			sql = "update board set good = (good + ?) where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goodCnt);
			pstmt.setInt(2, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러7 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
	}

	// 게시글 삭제처리
	public int setBoDeleteOk(int idx) {
		int res = 0;
		try {
			sql = "delete from board where idx = ?";
			pstmt = conn.prepareStatement(sql); // sql실행하면서 pstmt로넘김
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러8 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	// 게시글 수정처리
	public int setBoUpdateOk(BoardVO vo) {
		int res = 0;
		try {
			sql = "update board set title=?, email=?, homePage=?, content=?, hostIp=? where idx = ?"; // idx가 일치할 경우
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getHomePage());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getHostIp());
			pstmt.setInt(6, vo.getIdx());
			pstmt.executeUpdate(); //실행
			//정상이면
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러9 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res; // 정상이 아니면 0이 올라감~
	}

	// 이전글/다음글에 필요한 내용 검색 처리
	public BoardVO getPreNextSearch(String str, int idx) {
		vo = new BoardVO();
		try {
			if(str.equals("pre")) {
				sql = "select * from board where idx < ? order by idx desc limit 1";// 해당 ?번째 글보다 작은 idx 하나만
			}
			else {
				sql = "select * from board where idx > ? limit 1";// 해당 ?번째 글보다 큰 idx 하나만
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(str.equals("pre") && rs.next()) {
				vo.setPreIdx(rs.getInt("idx"));
				vo.setPreTitle(rs.getString("title"));
			}
			else if(str.equals("next") && rs.next()) {
				vo.setNextIdx(rs.getInt("idx"));
				vo.setNextTitle(rs.getString("title"));
				
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러10 : " + e.getMessage());
		} finally {
			getConn.rsClose(); // 넘기는 값이 ㅇㅇㅣ
		}
		return vo;
	}

	public ArrayList<BoardVO> getBoContentSearch(String search, String searchString) {
		ArrayList<BoardVO> vos = new ArrayList<>();
		try {
			/* if(search.equals("title")) { */
				sql = "select * from board where "+search+" like ? order by idx desc"; // 나중에 올린게 먼저 나온다. title like은 완벽히 검색어를 전체다 안써도됨
			/*}
			else if(search.equals("nickName")) {
				sql = "select * from board where nickName like ? order by idx desc"; // 나중에 올린게 먼저 나온다. title like은 완벽히 제목검색어를 전체다 안써도됨
			}
			else {
				sql = "select * from board where content like ? order by idx desc"; // 나중에 올린게 먼저 나온다. title like은 완벽히 제목검색어를 전체다 안써도됨
			}*/
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchString+"%"); // %%분리왜하는지모름
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setwDate(rs.getString("wDate"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setGood(rs.getInt("good"));
				vo.setMid(rs.getNString("mid"));
				
				/*
				 * vo.setDay_diff(rs.getInt("day_diff"));
				 * vo.setHour_diff(rs.getInt("hour_diff"));
				 */
				
				vos.add(vo);
				
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러11 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	// 댓글 입력하기
	public String setReplyInputOk(BoardReplyVO replyVo) {
		String res = "0";
		try {
			sql = "insert into boardReply values (default,?,?,?,default,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyVo.getBoardIDx());
			pstmt.setString(2, replyVo.getMid());
			pstmt.setString(3, replyVo.getNickName());
			pstmt.setString(4, replyVo.getHostIp());
			pstmt.setString(5, replyVo.getContent());
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 에러12 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}
	
	// 입력한 댓ㅎ글 가져오기
	public ArrayList<BoardReplyVO> getBoReply(int idx) {
		ArrayList<BoardReplyVO> replyVos = new ArrayList<>();
		try {
			sql = "select * from boardReply where boardIdx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			//없을수도있고 ㅇ벗는게더많아 알에스넥스트호가실하게해래
			while(rs.next()) {
				BoardReplyVO replyVo = new BoardReplyVO();
				replyVo.setIdx(rs.getInt("idx")); //댓글의 idx
				replyVo.setBoardIDx(idx); //넘어온 부모의 아이디엑스 (위에있는거)
				replyVo.setMid(rs.getString("mid"));
				replyVo.setNickName(rs.getString("nickName"));
				replyVo.setwDate(rs.getString("wDate"));
				replyVo.setHostIp(rs.getString("hostIp"));
				replyVo.setContent(rs.getString("content"));
				
				replyVos.add(replyVo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러12 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return replyVos;
	}

	// 댓글 삭제하기
	public String setBoReplyDeleteOk(int idx) {
		String res = "0";
		try {
			sql = "delete from boardReply where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 에러13 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); //삭제만하는거니까 pstmt
		}
		return res;
	}
}

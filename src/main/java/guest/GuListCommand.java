package guest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuListCommand implements GuestInterface {
	/*   	페이징처리
	  1. 페이지(pag)를 결정한다. 처음 접속시의 페이지는 무조건 1Page이다. : pag = 1 (page는 안씀)
	  2. 한 페이지의 분량을 결정한다. : pageSize = 5(사용자가 결정한다.) 이곳에서는 한페이지 분량을 5로 했다. pageSize = 5
	  3. 총 레코드 건수를 구한다. totRecCnt => SQL함수중 count(*)를 사용한다.
	  4. 총 페이지 건수를 구한다. totPage => totRecCnt % pageSize 값이 0이면 '몫', 0이 아니면 '몫+1' (나머지연산자로 나머지구하기?)
	 	5. 현재 페이지의 시작 인덱스 번호를 구한다.startIndexNo => (pag - 1) * pageSize
	 	6. 현재 화면에 보여주는 시작번호를 구한다. curScrStartNo = totRecCnt - startIndexNo
	 	
	 */
	
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestDAO dao = new GuestDAO(); // DAO에게 일을 시켜야하므로 연동한다.
	
		// 1. 페이지(pag)를 결정한다.
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag")); //페이지가 널이면 1이 넘어오게?
		
		// 2. 한 페이지의 분량을 결정한다. // 이거 숙제 변수로받는거, 5,10.15.20,25 페이지 스크롤?
		int pageSize = 5;
		
		// 3. 총 레코드 건수를 구한다. (SQL로 해야하니까 dao사용해야함)
		int totRecCnt = dao.totRecCnt();
		//System.out.println("totRecCnt : " + totRecCnt);
		
		// 4. 총 페이지 건수를 구한다. // 나머지계산하니 0 이 나오면 몫, 아니면 몫에 +1
		int totPage = (totRecCnt % pageSize)== 0 ? totRecCnt / pageSize : (totRecCnt / pageSize) + 1;
		
		// 5. 현재 페이지의 시작 인덱스번호를 구한다.
		int startIndexNo = (pag - 1) * pageSize;
		
		// 6. 현재 화면에 보여주는 시작번호를 구한다.
		int curScrStartNo = totRecCnt - startIndexNo;
		
		// 블록페이징처리 (3단계) -> 블록의 시작번호를 0번부터 처리했다.
		// 1. 블록의 크기를 결정한다.(여기선 3으로 지정)
		int blockSize = 3;
		
		// 2. 현재 페이지가 위치하고 있는 블록 번호를 구한다. (예:1페이지는 0블록, 3페이지는 0블록, 5페이지는 1블록)
		// int curBlock = (pag % blockSize)==0 ? (pag % blockSize) - 1 : (pag % blockSize); // 현재페이지에서 
		int curBlock = (pag - 1) / blockSize;
		
		// 3. 마지막 블록을 구한다.                           0이면 -1              0이아니면
		//int lastBlock = (totPage % blockSize)==0 ? (totPage / blockSize) - 1 : (totPage / blockSize);
		int lastBlock = (totPage - 1) / blockSize;
		
		
		
		// ArrayList<GuestVO> vos = dao.getGuestList(); // dao 전체자료 가져오기 생성, 여러번 가져와야하니 arrayList, dao에 생성
		ArrayList<GuestVO> vos = dao.getGuestList(startIndexNo, pageSize); // 페이지의 시작인덱스번호를 한페이지의분량만큼 가져옴 전체 건수 가져오다가 이젠 5건만 가져온다(실제인덱스번호)
	
		request.setAttribute("vos", vos); // vos에 담아온 값들 저장소에 담아서 보낸다. 이거 안하면 뷰에 안올라옴..
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("pag", pag); // 이전페이지어쩌구 만들기위해 페이지수도넘긴다
		request.setAttribute("totPage", totPage); // 총페이지어쩌구 만들기위해 총페이지수도 넘김
		request.setAttribute("blockSize", blockSize); 
		request.setAttribute("curBlock", curBlock); 
		request.setAttribute("lastBlock", lastBlock); 
	}

}

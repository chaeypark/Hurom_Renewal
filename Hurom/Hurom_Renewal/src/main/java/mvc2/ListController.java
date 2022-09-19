
package mvc2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;

public class ListController extends HttpServlet{
	
	@Override
	//목록 3. doget 메서드 호출
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//목록4. DAO 생성
		CSMainBoardDAO dao = new CSMainBoardDAO();
		
		//목록5 뷰에 전달할 매개변수를 저장하기 위한 맵 생성 
		Map<String, Object> map = new HashMap<String,Object>();
		
		//목록6. 매개변수 받아오기
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		
		//목록7 매개변수로 전달된 검색어가 있으면 map컬렉션에 저장
		if (searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		//목록8
		//DAO의 selectCount()메서드를 통해 게시물 개수 확인
		int totalCount = dao.selectCount(map);
		
		//목록14
		//*** 페이지 처리 시작
		//ServletContext : Servlet간에 서로 값을 공유할 수 있도록
		//값(상태)를 저장하는 일종의 저장소
		ServletContext application = getServletContext();
		
		//목록15: 페이징을 위한 설정값 상수를 가져옴(web.xml에 저장되어있음)	
		int pageSize = 5;
		int blockPage = 5;
		
		//목록16: 현재 페이지 확인 후 요청 받은 페이지 번호가 있다면 해당 페이지로 변경
		int pageNum = 1; //페이지의 기본값
		String pageTemp = req.getParameter("pageNum"); 
		if (pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp); //요청받은 페이지로 변경
		
		
		//목록17 목록에 출력할 게시물 범위 계산 (공식이라고 생각하면됨)
		int start = (pageNum - 1) * pageSize +1; //첫 게시물 번호
		int end = pageNum * pageSize; //마지막 게시물 번호
		map.put("start", start);//시작값과
		map.put("end", end);//끝값을 넣음
		//*** 페이지 처리 끝
		
		//목록18. 게시물 목록 받아오기
		List<CSMainBoardDTO> boardLists = dao.selectListPage(map);
		
		//목록24. DB 연결종료(DAO에서 내용을 다 가져온 후 라서)
		dao.close(); //db연결 종료
		
		//목록25. 뷰에 전달할 매개변수 map컬렉션 추가
		//utils패키지의 BoardPage.java에서 생성한 pageinStr메서드 호출
		//목록15 16에서 선언한 변수들을 가져옴 + 연결해야하는 url주소도 넣어줌
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvc2/list.do");
		
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		//목록26. 페이징 처리가 끝났으니 뷰로 전달할 데이터를 request영역에 저장한 후에 List.jsp로 포워드
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		
		//목록27. 뷰(Customer_service.jsp)로 포워드
		req.getRequestDispatcher("/pages/Customer_service.jsp").forward(req, resp);
	}
}






package mypageCon;

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
import javax.servlet.http.HttpSession;

import common.JSFunction;
import membership.MemberDAO;
import membership.MemberDTO;
import mvc2.CSMainBoardDTO;
import mvc2.ComponentBoardDAO;
import mvc2.ComponentBoardDTO;
import mvc2.QNABoardDAO;
import mvc2.QNABoardDTO;
import utils.BoardPage;


@WebServlet("/mypageCon/mypage.do")
public class MypageController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {			
		
		HttpSession session = req.getSession();	
		if(session.getAttribute("memberid")!=null) { // 로그인 유지
			
			String id = (String)session.getAttribute("memberid");
			
			MypageDAO dao = new MypageDAO();
			MemberDTO dto = dao.userEdit(id);	
			dao.close();
			
			req.setAttribute("dto", dto);
			
			QNABoardDAO daoq = new QNABoardDAO();
			
			//목록5 뷰에 전달할 매개변수를 저장하기 위한 맵 생성
			Map<String, Object> map = new HashMap<String, Object>();
			
			//목록6 매개변수 받아오기
			String searchField = req.getParameter("searchField");
			String searchWord = req.getParameter("serchWord");
			
			//목록7 매개변수로 전달된 검색어가 있으면 map 컬렉션에 저장
			if(searchWord != null) {
				map.put("searchField", searchField);
				map.put("searchWord", searchWord);
			}
			
			//목록8 DAO의 selectCount()메서드를 통해 게시물 개수 확인
			int totalCount = daoq.selectCount(map);
			
			//목록14. 페이징 처리
			//getServletContext : servlet간에 서로 값을 공유 할수 있도록
			//값(상태)를 저장하는 일종의 저장소
			ServletContext application = getServletContext();
			
			//목록15. 페이징을 위한 설정값 상수를 가져옴(web.xml에 저장되어 있음)
			//int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
			//int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
			
			int pageSize = Integer.parseInt("5");
			int blockPage = Integer.parseInt("5");
			
			//목록16. 현재 페이지 확인 후 용청 받은 페이지 번호가 있다면 해당 페이지로 변경
			int pageNum = 1;//페이지의 기본값
			String pageTemp = req.getParameter("pageNum");
			if(pageTemp != null && !pageTemp.equals("")) 
				pageNum = Integer.parseInt(pageTemp);//요청받은 페이지로 변경
			
			//목록17. 목록에 출력할 게시물 범위 계산
			int start = (pageNum-1)*pageSize+1;// 첫 게시물의 번호
			int end = pageNum * pageSize; //마지막 게시물 번호
			map.put("start", start);
			map.put("end", end);
			//페이징 처리 끝
			
			//목록18. 게시물 목록받아오기
			List<QNABoardDTO> boardLists = daoq.selectListPage(map);
			
			//목록24. db 연결 종료
			daoq.close(); 
			
			//목록25 뷰에 전달할 매개변수 map 컬렉션 추가
			//util 패이지의 boardPage.java에서 생성한 pagingstr메서드 호출
			String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mypageCon/mypage.do");
			
			map.put("pagingImg", pagingImg);
			map.put("totalCount", totalCount);
			map.put("pageSize", pageSize);
			map.put("pageNum", pageNum);
				
			//목록26. 뷰로 전달할 데이터를 request영역에 저장한 후에 list.jsp로 포워드
			req.setAttribute("boardLists", boardLists);
			req.setAttribute("map", map);
			
			//목로27. 뷰(list.jsp)로 포워드
			//req.getRequestDispatcher("../mypage/mypage.jsp").forward(req, resp);
			
//================================================================================부품시작11
			
			ComponentBoardDAO daoc = new ComponentBoardDAO();
			
			//목록5 뷰에 전달할 매개변수를 저장하기 위한 맵 생성
			Map<String, Object> mapc = new HashMap<String, Object>();
			
			//목록6 매개변수 받아오기
			String searchFieldc = req.getParameter("searchField");
			String searchWordc = req.getParameter("serchWord");
			
			//목록7 매개변수로 전달된 검색어가 있으면 map 컬렉션에 저장
			if(searchWordc != null) {
				mapc.put("searchField", searchFieldc);
				mapc.put("searchWord", searchWordc);
			}
			
			//목록8 DAO의 selectCount()메서드를 통해 게시물 개수 확인
			int totalCountc = daoc.selectCount(mapc);
			
			//목록14. 페이징 처리
			//getServletContext : servlet간에 서로 값을 공유 할수 있도록
			//값(상태)를 저장하는 일종의 저장소
			ServletContext applicationc = getServletContext();
			
			//목록15. 페이징을 위한 설정값 상수를 가져옴(web.xml에 저장되어 있음)
			//int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
			//int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
			
			int pageSizec = Integer.parseInt("5");
			int blockPagec = Integer.parseInt("5");
			
			//목록16. 현재 페이지 확인 후 용청 받은 페이지 번호가 있다면 해당 페이지로 변경
			int pageNumc = 1;//페이지의 기본값
			String pageTempc = req.getParameter("pageNum");
			if(pageTempc != null && !pageTempc.equals("")) 
				pageNumc = Integer.parseInt(pageTempc);//요청받은 페이지로 변경
			
			//목록17. 목록에 출력할 게시물 범위 계산
			int startc = (pageNumc-1)*pageSizec+1;// 첫 게시물의 번호
			int endc = pageNumc * pageSizec; //마지막 게시물 번호
			mapc.put("start", startc);
			mapc.put("end", endc);
			//페이징 처리 끝
			
			//목록18. 게시물 목록받아오기
			List<ComponentBoardDTO> boardListsc = daoc.selectListPage(mapc);			
			System.out.println(boardListsc);
			System.out.println(boardLists);
			//목록24. db 연결 종료
			daoc.close(); 
			
			//목록25 뷰에 전달할 매개변수 map 컬렉션 추가
			//util 패이지의 boardPage.java에서 생성한 pagingstr메서드 호출
			String pagingImgc = BoardPage.pagingStr(totalCountc, pageSizec, blockPagec, pageNumc, "../mypageCon/mypage.do");
			
			mapc.put("pagingImg", pagingImgc);
			mapc.put("totalCount", totalCountc);
			mapc.put("pageSize", pageSizec);
			mapc.put("pageNum", pageNumc);
				
			//목록26. 뷰로 전달할 데이터를 request영역에 저장한 후에 list.jsp로 포워드
			req.setAttribute("boardListsc", boardListsc);
			req.setAttribute("map", mapc);
			
			//목로27. 뷰(list.jsp)로 포워드
			req.getRequestDispatcher("../mypage/mypage.jsp").forward(req, resp);
			
			
			
			
		}else {			
			JSFunction.alertLocation(resp, "마이콘 마이페이지는 로그인 후 사용가능합니다", "../pages/index.jsp");
		}
	}
	
	
	
	
}

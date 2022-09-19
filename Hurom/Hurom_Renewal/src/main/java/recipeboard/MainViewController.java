package recipeboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.BoardPage;
import common.JSFunction;
import recipeUpload.UserfileDAO;
import recipeUpload.UserfileDTO;


public class MainViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RecipeDAO dao = new RecipeDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		
		if (searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		int totalCount = dao.selectCount(map);
		
		//목록 14. 페이지 처리
		//ServletContext: Servlet간에 서로 값을 공유할 수 있도록
		//값(상태)를 저장하는 일종의 저장소
		ServletContext application = getServletContext();
		
		int pageSize = 12;  
		int blockPage = 5;
		
		int pageNum = 1;//페이지의 기본값
		
		String pageTemp = req.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp);
		}

		int start = (pageNum-1) * pageSize + 1; //첫 게시물 번호
		int end = pageNum * pageSize;
		
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeDTO> boardLists = dao.recipeList(map);
		
		dao.close();
		
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../recipe/main.do");
		
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		
		req.getRequestDispatcher("/pages/My_Recipe_Main.jsp").forward(req, resp);

	}
	//사진을 정렬할때 Filenum의 역순으로 해야한다 파일을 저장할때 가장 나중의 값을 먼저 입력하기 때문인데 사용자가 보통 맨위에 올라갈 파일을 먼저 업로드하므로 이를 생각하고 구현 할 필요가 있다. 
}
package mvc2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//상세보기 2. 어노테이션 사용해서 요청명과 서블릿을 매핑함
@WebServlet("/mvc2/view2.do") 
//서블릿상속받음
public class ViewController2 extends HttpServlet {
//뷰에서 할 일 > 결과를 화면에 보여줌 == 다오 생성하고 조회수 +1 보여줌
	
	@Override 
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		//상세보기3. 게시글 불러오기 (게시물 조회에 대한 요청이오면(리스트에서 클릭하면) > dao객체 생성)
		ComponentBoardDAO dao  = new ComponentBoardDAO(); //상세보기4. dao 라는 객체생성
		String idx = req.getParameter("idx"); //상세보기5. 게시글의 일련번호를 매개변수로 받아서	
		dao.updateVisitCount(idx);				//상세보기9. 해당하는 게시글에 대한 조회수 +1 
		ComponentBoardDTO dto = dao.selectView(idx); //상세보기16. 게시글의 내용을 가져옴
		dao.close();
		
		//상세보기17. 엔터치면 줄바뀜 먹히게세팅
		//HTML문서는 줄바꿈(\r\n)을 무시함. 그래서 HTML이 인식할 수 있도록 <br>태그로 변경
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>"));
		
		//상세보기18. 게시물(dto)저장 후 
		req.setAttribute("dto", dto);
		
		//상세보기19. 뷰로 포워드 시킴.
		req.getRequestDispatcher("/pages/ComponentRequest_view.jsp").forward(req, resp);
		
	}
}

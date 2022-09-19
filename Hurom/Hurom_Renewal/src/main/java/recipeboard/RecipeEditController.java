package recipeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JSFunction;

public class RecipeEditController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String memberid = (String) session.getAttribute("memberid");
		
		if (!(session.getAttribute("memberid") == null)) {
			String idx = req.getParameter("idx");
			
			RecipeDAO dao = new RecipeDAO();
			RecipeDTO dto = dao.selectView(idx);
			dao.close();

			if(memberid.equals(dto.getWriter_id())) {

				req.setAttribute("dto", dto);
				
				req.getRequestDispatcher("../pages/My_Recipe_Edit.jsp").forward(req, resp);
				
			} else {
				JSFunction.alertBack(resp, "작성자 본인만 수정할 수 있습니다.");
			}

		} else {
			JSFunction.alertBack(resp, "로그인 하셔야 글 수정이 가능합니다.");
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idx = req.getParameter("idx");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String product_name = req.getParameter("productName");
		
		RecipeDTO dto = new RecipeDTO();
		
		dto.setIdx(idx);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setProduct_name(product_name);
		
		RecipeDAO dao = new RecipeDAO();
		
		int result = dao.UpdateEdit(dto);
		dao.close();
		
		if (result == 1) {
			resp.sendRedirect("../recipeboard/view.do?idx=" + idx);
		}
	}
}

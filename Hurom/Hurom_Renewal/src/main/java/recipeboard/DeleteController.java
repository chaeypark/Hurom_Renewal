package recipeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JSFunction;
import fileupload.FileUtil;
import recipeUpload.UserfileDAO;
import recipeUpload.UserfileDTO;

public class DeleteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String memberid = (String) session.getAttribute("memberid");
		
		if (!(session.getAttribute("memberid") == null)) {
			
			String idx = req.getParameter("idx");
			
			RecipeDAO rdao = new RecipeDAO();
			RecipeDTO rdto = rdao.selectView(idx);
			
			if(memberid.equals(rdto.getWriter_id())) { // 본인이 맞는지 확인
				
				int result1 = rdao.deletePost(idx); // 게시물 글 삭제
				rdao.close();
				
				UserfileDAO udao = new UserfileDAO();
				List<UserfileDTO> list = udao.queryImage(idx);
				int result2 = udao.deletePost(idx);
				udao.close();
				
				if (result1+result2 > 1) {
					
					String[] newname = new String[list.size()];
					for(int i=0; i<list.size(); i++) {
						newname[i] = list.get(i).getNewname();
						
						FileUtil.deleteFile(req, "/Upload", newname[i]);
					}
					
					resp.sendRedirect("../recipe/main.do");
				}
				
			} else {
				JSFunction.alertBack(resp, "작성자 본인만 삭제할 수 있습니다.");
			}
			
		} else {
			JSFunction.alertBack(resp, "로그인 하셔야 글 삭제가 가능합니다.");
		}
		
	}
}
//as 및 부품신청 게시판 수정 pass controller
package mvc2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileupload.FileUtil;
import utils.JSFunction;
//삭제2 비밀번호 확인
//수정2
@WebServlet("/mvc2/pass2.do")
public class PassController2 extends HttpServlet {
	
	@Override
	
	//삭제3. 모드(edit인지 delete인지)확인하여 Pass.jsp로 포워드함
	//수정3
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//수정이냐 삭제냐만 다르고 그 전과정은 같음
		req.setAttribute("mode", req.getParameter("mode"));
		//비밀번호 확인 뷰어로 넘겨줌 
		req.getRequestDispatcher("/pages/ComponentRequest_pass.jsp").forward(req, resp);
		//ComponentBoard에 비밀번호가 일치하는지 아닌지 알아보기 위한 Pass.jsp 뷰생성
	}
	
	//삭제5. 비밀번호 입력폼에서 전송한 값을 받아서 처리함
	//수정5
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//삭제6. 매개변수를 받아서 변수에 저장
		//수정6
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		
		//삭제7. 비밀번호 확인하기위해 dao 객체생성
		//수정7
		ComponentBoardDAO dao = new ComponentBoardDAO();
		
		//삭제 13. dao에 confirmPassword()메소드를 통해 비밀번호가 일치하는지 확인
		//수정 13
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close();
		
		//삭제14. 비밀번호가 일치하면 ..
		//수정 14
		if(confirmed ) {
			
			//삭제15. 모드가 수정인지 삭제인지 확인
			//수정 15
			if(mode.equals("edit")) { //수정16. 수정모드
				//수정17.session 영역에
				HttpSession session = req.getSession();
				//수정18. 비밀번호 저장 후
				session.setAttribute("pass", pass);
				//수정19. 수정하기 페이지로 이동
				resp.sendRedirect("../mvc2/edit2.do?idx=" + idx);
				
			}else if(mode.equals("delete")) { //삭제16. 삭제모드
				
				//삭제17. 삭제를 선택하면 게시글에 첨부된 파일도 함께 삭제처리해야함.
				dao = new ComponentBoardDAO();
				
				//삭제20. 기존 정보를 보관
				ComponentBoardDTO dto = dao.selectView(idx);
				
				//삭제25. 게시물을 삭제
				int result = dao.deletePost(idx);
				dao.close();
				
				//삭제26. 게시물 삭제를 성공하면 첨부파일도 함께 삭제
				if(result ==1) {
					//삭제27. 저장된 이름을 가져옴
					String saveFileName = dto.getSfile();
					//삭제28. 첨부파일을 삭제
					FileUtil.deleteFile(req, "/Upload", saveFileName);
				}
				//삭제34 알림창표시  
				JSFunction.alertLocation(resp, "삭제되었습니다.", "../mvc2/listAS.do");
			}
			
			//삭제14-1. 비밀번호가 일치하지 않으면..
		}else {
			JSFunction.alertBack(resp, "비밀번호가 틀렸습니다.");
		}
		
	}

}
